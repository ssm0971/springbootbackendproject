package com.example.hope_dog.service.member;

import com.example.hope_dog.dto.member.MemberDTO;
import com.example.hope_dog.dto.member.MemberSessionDTO;
import com.example.hope_dog.mapper.member.MemberMapper;
import com.example.hope_dog.utils.PasswordUtil;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;

// 회원 관련 비즈니스 로직을 처리하는 서비스 클래스
@Service // 스프링의 서비스 계층 컴포넌트로 등록
@Transactional // 모든 메서드에 트랜잭션 처리 적용
@RequiredArgsConstructor // final 필드에 대한 생성자 자동 생성
@Slf4j // 로깅 기능 활성화
public class MemberService {

    // 필요한 의존성 주입
    private final MemberMapper memberMapper;          // 회원 데이터 처리를 위한 매퍼
    private final EmailService emailService;          // 이메일 발송 서비스
    private final PasswordEncoder passwordEncoder;     // 비밀번호 암호화 인코더

    // 애플리케이션 시작 시 자동으로 실행되어 기존 회원들의 비밀번호를 암호화
    @PostConstruct
    public void initializePasswords() {
        log.info("Initializing passwords for existing members...");
        encryptExistingPasswords();
        log.info("Password initialization completed.");
    }

    // 회원가입 처리 메서드
    public void join(MemberDTO memberDTO) {
        // 비밀번호 암호화 과정 로깅
        log.info("Original password before encryption: {}", memberDTO.getMemberPw());

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(memberDTO.getMemberPw());
        log.info("Password after encryption: {}", encodedPassword);

        // 암호화된 비밀번호 설정
        memberDTO.setMemberPw(encodedPassword);

        // DB에 회원정보 저장
        memberMapper.insertMember(memberDTO);
        log.info("Member successfully registered with encrypted password");
    }

    // 회원번호 조회 메서드
    public Long findMemberNo(String memberId, String memberPw) {
        return Optional.ofNullable(memberMapper.selectMemberNo(memberId, memberPw))
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원정보"));
    }

    // 로그인 처리 메서드
    public MemberSessionDTO login(String memberId, String memberPw) {
        log.info("Login attempt for ID: {}", memberId);

        // 회원 정보 조회
        MemberSessionDTO member = memberMapper.selectLoginInfo(memberId);

        // 회원이 존재하지 않는 경우
        if (member == null) {
            log.error("Member not found: {}", memberId);
            throw new IllegalStateException("존재하지 않는 회원정보입니다.");
        }

        // 비밀번호 검증
        if (!passwordEncoder.matches(memberPw, member.getMemberPw())) {
            log.error("Password mismatch for member: {}", memberId);
            throw new IllegalStateException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }

        log.info("Login successful for member: {}", memberId);
        return member;
    }

    // 기존 회원의 비밀번호를 암호화하는 메서드
    @Transactional
    public void encryptExistingPasswords() {
        log.info("Starting password encryption for existing members");

        // 모든 회원 조회
        List<MemberDTO> members = memberMapper.selectAllMembers();

        // 각 회원의 비밀번호 암호화 처리
        for (MemberDTO member : members) {
            // BCrypt로 암호화되지 않은 비밀번호만 처리
            if (!member.getMemberPw().startsWith("$2a$")) {
                String encodedPassword = passwordEncoder.encode(member.getMemberPw());
                log.info("Encrypting password for member: {}", member.getMemberId());
                memberMapper.updateAllPasswords(member.getMemberId(), encodedPassword);
            }
        }

        log.info("Completed password encryption for existing members");
    }

    // 닉네임 중복 체크 메서드
    public boolean checkNickname(String nickname) {
        if (nickname == null || nickname.trim().isEmpty()) {
            throw new IllegalArgumentException("닉네임은 필수 입력값입니다.");
        }
        return memberMapper.checkNickname(nickname) == 0; // 중복되지 않으면 true 반환
    }

    // 이메일 중복 체크 메서드
    public boolean checkEmail(String email) {
        int count = memberMapper.checkMemberEmail(email);
        return count == 0;  // 중복된 이메일이 없으면 true 반환
    }

    // 아이디 찾기 메서드
    public String findMemberId(String memberName, String memberPhoneNumber) {
        String memberId = memberMapper.findMemberId(memberName, memberPhoneNumber);
        if (memberId == null) {
            throw new IllegalArgumentException("해당하는 회원 정보를 찾을 수 없습니다.");
        }
        return memberId;
    }

    // 비밀번호 재설정 메서드
    public void resetPassword(String memberName, String memberId, String memberEmail) {
        // 회원 정보 확인
        MemberDTO member = memberMapper.findMemberByNameIdEmail(memberName, memberId, memberEmail);

        if (member == null) {
            throw new IllegalArgumentException("입력하신 정보와 일치하는 회원이 없습니다.");
        }

        // 임시 비밀번호 생성 및 암호화
        String tempPassword = PasswordUtil.generateTempPassword();
        member.setMemberPw(passwordEncoder.encode(tempPassword));
        memberMapper.updateMemberPassword(member);

        // 임시 비밀번호 이메일 발송
        emailService.sendTempPassword(memberEmail, tempPassword);
    }

    // 소셜 로그인 회원 조회 메서드
    public MemberDTO findByProviderAndProviderId(String provider, String providerId) {
        return memberMapper.findByProviderAndProviderId(provider, providerId);
    }

    // 이메일로 회원 조회 메서드
    public MemberDTO findByEmail(String memberEmail) {
        return memberMapper.findByEmail(memberEmail);
    }

    // 소셜 회원 등록/수정 메서드
    public MemberDTO registerSocialMember(MemberDTO memberDTO) {
        try {
            // 이메일로 기존 회원 조회
            MemberDTO existingMember = memberMapper.findByEmail(memberDTO.getMemberEmail());

            if (existingMember != null) {
                // 기존 회원이 있는 경우 소셜 정보 업데이트
                log.info("Existing member found. Updating social info for email: {}", memberDTO.getMemberEmail());
                existingMember.setProvider(memberDTO.getProvider());
                existingMember.setProviderId(memberDTO.getProviderId());
                existingMember.setMemberLoginStatus(memberDTO.getProvider().toUpperCase());
                memberMapper.updateMemberSocialInfo(existingMember);
                return existingMember;
            }

            // 신규 소셜 회원 가입 처리
            log.info("Creating new social member for email: {}", memberDTO.getMemberEmail());
            String provider = memberDTO.getProvider();
            memberDTO.setMemberPw(passwordEncoder.encode(provider + memberDTO.getProviderId()));
            memberDTO.setMemberStatus("1");
            memberDTO.setMemberLoginStatus(provider.toUpperCase());
            memberDTO.setMemberTwoFactorEnabled("N");

            memberMapper.insertMember(memberDTO);
            return memberDTO;
    
        } catch (Exception e) {
            log.error("Error in registerSocialMember: ", e);
            throw new IllegalStateException("소셜 회원 등록 중 오류가 발생했습니다.", e);
        }
    }
}