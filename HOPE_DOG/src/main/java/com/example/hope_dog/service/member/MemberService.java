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

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j  // 로그 추가
public class MemberService {

    private final MemberMapper memberMapper;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct  // 애플리케이션 시작 시 자동 실행
    public void initializePasswords() {
        log.info("Initializing passwords for existing members...");
        encryptExistingPasswords();
        log.info("Password initialization completed.");
    }

    // 회원가입 시 비밀번호 암호화 확실히 하기
    public void join(MemberDTO memberDTO) {
        // 비밀번호 암호화 여부 로깅
        log.info("Original password before encryption: {}", memberDTO.getMemberPw());

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(memberDTO.getMemberPw());
        log.info("Password after encryption: {}", encodedPassword);

        // 암호화된 비밀번호로 설정
        memberDTO.setMemberPw(encodedPassword);

        // DB에 저장
        memberMapper.insertMember(memberDTO);
        log.info("Member successfully registered with encrypted password");
    }

    //회원번호 조회
    public Long findMemberNo(String memberId, String memberPw) {
        return Optional.ofNullable(memberMapper.selectMemberNo(memberId, memberPw))
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원정보"));
    }


    public MemberSessionDTO login(String memberId, String memberPw) {
        log.info("Login attempt for ID: {}", memberId);

        // 회원 정보 조회
        MemberSessionDTO member = memberMapper.selectLoginInfo(memberId);

        if (member == null) {
            log.error("Member not found: {}", memberId);
            throw new IllegalStateException("존재하지 않는 회원정보입니다.");
        }

        // 입력된 비밀번호와 저장된 암호화된 비밀번호 비교
        if (!passwordEncoder.matches(memberPw, member.getMemberPw())) {
            log.error("Password mismatch for member: {}", memberId);
            throw new IllegalStateException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }

        log.info("Login successful for member: {}", memberId);
        return member;
    }


    // 기존 회원의 비밀번호 암호화
    @Transactional
    public void encryptExistingPasswords() {
        log.info("Starting password encryption for existing members");

        // 1. 모든 회원 조회
        List<MemberDTO> members = memberMapper.selectAllMembers();

        for (MemberDTO member : members) {
            // 만약 비밀번호가 이미 암호화되어 있지 않다면
            if (!member.getMemberPw().startsWith("$2a$")) {
                String encodedPassword = passwordEncoder.encode(member.getMemberPw());
                log.info("Encrypting password for member: {}", member.getMemberId());
                memberMapper.updateAllPasswords(member.getMemberId(), encodedPassword);
            }
        }

        log.info("Completed password encryption for existing members");
    }


    //  닉네임 중복체크
    public boolean checkNickname(String nickname) {
        if (nickname == null || nickname.trim().isEmpty()) {
            throw new IllegalArgumentException("닉네임은 필수 입력값입니다.");
        }
        // 추가 유효성 검사 가능
        return memberMapper.checkNickname(nickname) == 0;
    }

    //이메일 중복체크
    public boolean checkEmail(String email) {
        int count = memberMapper.checkMemberEmail(email);
        return count == 0;  // 중복된 이메일이 없으면 true 반환
    }


    //아이디 찾기
    public String findMemberId(String memberName, String memberPhoneNumber) {
        String memberId = memberMapper.findMemberId(memberName, memberPhoneNumber);
        if (memberId == null) {
            throw new IllegalArgumentException("해당하는 회원 정보를 찾을 수 없습니다.");
        }
        return memberId;
    }



    public void resetPassword(String memberName, String memberId, String memberEmail) {
        // 회원 정보 확인
        MemberDTO member = memberMapper.findMemberByNameIdEmail(memberName, memberId, memberEmail);

        if (member == null) {
            throw new IllegalArgumentException("입력하신 정보와 일치하는 회원이 없습니다.");
        }

        // 임시 비밀번호 생성
        String tempPassword = PasswordUtil.generateTempPassword();

        // DB에 임시 비밀번호 저장 (암호화)
        member.setMemberPw(passwordEncoder.encode(tempPassword));
        memberMapper.updateMemberPassword(member);

        // 이메일 발송
        emailService.sendTempPassword(memberEmail, tempPassword);
    }


    // 소셜 로그인 회원 조회
    public MemberDTO findByProviderAndProviderId(String provider, String providerId) {
        return memberMapper.findByProviderAndProviderId(provider, providerId);
    }



    // 이메일로 회원 조회하는 메서드 추가
    public MemberDTO findByEmail(String memberEmail) {
        return memberMapper.findByEmail(memberEmail);
    }

    // 소셜 회원 등록 메서드 수정
    public MemberDTO registerSocialMember(MemberDTO memberDTO) {
        try {
            // 이메일로 기존 회원 조회
            MemberDTO existingMember = memberMapper.findByEmail(memberDTO.getMemberEmail());

            if (existingMember != null) {
                log.info("Existing member found. Updating social info for email: {}", memberDTO.getMemberEmail());
                // 기존 계정이 있다면 소셜 정보 업데이트
                existingMember.setProvider(memberDTO.getProvider());
                existingMember.setProviderId(memberDTO.getProviderId());
                existingMember.setMemberLoginStatus(memberDTO.getProvider().toUpperCase());
                memberMapper.updateMemberSocialInfo(existingMember);
                return existingMember;
            }

            // 신규 가입 처리
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
