package com.example.hope_dog.service.centermember;

import com.example.hope_dog.dto.centerMember.CenterFileDTO;
import com.example.hope_dog.dto.centerMember.CenterMemberDTO;
import com.example.hope_dog.dto.centerMember.CenterMemberSessionDTO;
import com.example.hope_dog.mapper.centermember.CenterMemberMapper;
import com.example.hope_dog.mapper.member.MemberMapper;
import com.example.hope_dog.service.member.EmailService;
import com.example.hope_dog.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 보호소/센터 회원 관련 비즈니스 로직을 처리하는 서비스 클래스
 * 회원가입, 로그인, 파일 업로드 등의 기능을 제공
 */
@Slf4j  // 로깅 기능 활성화
@Service // 스프링의 서비스 컴포넌트로 등록
@RequiredArgsConstructor // final 필드에 대한 생성자 자동 생성
@Transactional(readOnly = true) // 기본적으로 읽기 전용 트랜잭션 설정
public class CenterMemberService {

    // 필요한 의존성 주입
    private final CenterMemberMapper centerMemberMapper;    // 센터 회원 데이터 처리
    private final MemberMapper memberMapper;                // 일반 회원 데이터 처리
    private final EmailService emailService;                // 이메일 발송 서비스
    private final PasswordEncoder passwordEncoder;          // 비밀번호 암호화

    // 파일 업로드 경로 설정 (기본값: C:/upload/)
    @Value("${file.upload.path:C:/upload/}")
    private String fileUploadPath;

    /**
     * 센터 회원 가입을 처리하는 메소드
     * 회원 정보 저장 및 사업자등록증 파일 업로드를 수행
     *
     * @param memberDTO 센터 회원 가입 정보
     * @return 생성된 센터 회원의 번호
     * @throws IllegalArgumentException 유효성 검사 실패 시
     * @throws RuntimeException 기타 처리 중 오류 발생 시
     */
    @Transactional
    public Long join(CenterMemberDTO memberDTO) {
        try {
            // 1. 회원 정보 유효성 검사
            validateMemberDTO(memberDTO);

            // 2. 비밀번호 암호화
            memberDTO.setCenterMemberPw(passwordEncoder.encode(memberDTO.getCenterMemberPw()));

            // 3. 센터 회원 정보 저장
            centerMemberMapper.insertCenterMember(memberDTO);
            Long centerMemberNo = memberDTO.getCenterMemberNo();

            // 4. 사업자등록증 파일 처리
            if (memberDTO.getBusinessFile() != null && !memberDTO.getBusinessFile().isEmpty()) {
                saveBusinessFile(memberDTO, centerMemberNo);
            }

            return centerMemberNo;

        } catch (IllegalArgumentException e) {
            log.error("회원가입 유효성 검사 실패: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("회원가입 처리 중 오류 발생", e);
            throw new RuntimeException("회원가입 처리 중 오류가 발생했습니다.", e);
        }
    }

    /**
     * 사업자등록증 파일을 저장하는 private 메소드
     *
     * @param memberDTO 센터 회원 정보
     * @param centerMemberNo 센터 회원 번호
     * @throws IOException 파일 저장 실패 시
     */
    private void saveBusinessFile(CenterMemberDTO memberDTO, Long centerMemberNo) throws IOException {
        MultipartFile file = memberDTO.getBusinessFile();
        String originalFilename = file.getOriginalFilename();

        // UUID를 사용하여 고유한 파일명 생성
        String uuid = UUID.randomUUID().toString();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String savedFileName = uuid + extension;
        String savePath = fileUploadPath + savedFileName;

        // 업로드 디렉토리가 없으면 생성
        File directory = new File(fileUploadPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // 파일 정보 DTO 생성
        CenterFileDTO fileDTO = new CenterFileDTO();
        fileDTO.setCenterFileName(originalFilename);
        fileDTO.setCenterFileUuid(uuid);
        fileDTO.setCenterFilePath(savePath);
        fileDTO.setCenterMemberNo(centerMemberNo);

        // 파일 저장 및 DB에 파일 정보 저장
        file.transferTo(new File(savePath));
        centerMemberMapper.insertCenterFile(fileDTO);
    }

    /**
     * 센터 회원 로그인을 처리하는 메소드
     *
     * @param centerId 센터 회원 ID
     * @param centerPw 센터 회원 비밀번호
     * @return 로그인한 회원의 세션 정보
     * @throws IllegalArgumentException 로그인 정보가 일치하지 않을 때
     */
    public CenterMemberSessionDTO login(String centerId, String centerPw) {
        try {
            CenterMemberSessionDTO sessionDTO = centerMemberMapper.selectCenterLoginInfo(centerId, centerPw);
            if (sessionDTO == null) {
                throw new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다.");
            }
            return sessionDTO;
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            log.error("로그인 처리 중 오류 발생", e);
            throw new RuntimeException("로그인 처리 중 오류가 발생했습니다.", e);
        }
    }

    /**
     * 이메일 중복 여부를 체크하는 메소드
     *
     * @param email 확인할 이메일 주소
     * @return 중복되지 않으면 true, 중복되면 false
     */
    public boolean checkEmail(String email) {
        try {
            int count = centerMemberMapper.checkCenterEmail(email);
            return count == 0;
        } catch (Exception e) {
            log.error("이메일 중복 체크 중 오류 발생", e);
            throw new RuntimeException("이메일 중복 체크 중 오류가 발생했습니다.", e);
        }
    }

    /**
     * 센터 회원의 아이디를 찾는 메소드
     *
     * @param centerMemberName 센터 회원 이름
     * @param centerMemberPhoneNumber 센터 회원 전화번호
     * @return 찾은 센터 회원 ID
     * @throws IllegalArgumentException 회원 정보를 찾을 수 없을 때
     */
    public String findCenterMemberId(String centerMemberName, String centerMemberPhoneNumber) {
        try {
            String centerMemberId = centerMemberMapper.findCenterMemberId(centerMemberName, centerMemberPhoneNumber);
            if (centerMemberId == null) {
                throw new IllegalArgumentException("해당하는 회원 정보를 찾을 수 없습니다.");
            }
            return centerMemberId;
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            log.error("아이디 찾기 중 오류 발생", e);
            throw new RuntimeException("아이디 찾기 중 오류가 발생했습니다.", e);
        }
    }

    /**
     * 센터 회원의 비밀번호를 재설정하는 메소드
     * 임시 비밀번호를 생성하여 이메일로 발송
     *
     * @param centerMemberName 센터 회원 이름
     * @param centerMemberId 센터 회원 ID
     * @param centerMemberEmail 센터 회원 이메일
     * @throws IllegalArgumentException 회원 정보가 일치하지 않을 때
     */
    @Transactional
    public void centerResetPassword(String centerMemberName, String centerMemberId, String centerMemberEmail) {
        try {
            // 회원 정보 확인
            CenterMemberDTO center = centerMemberMapper.findCenterByNameIdEmail(
                    centerMemberName,
                    centerMemberId,
                    centerMemberEmail
            );

            if (center == null) {
                throw new IllegalArgumentException("입력하신 정보와 일치하는 센터 회원이 없습니다.");
            }

            // 임시 비밀번호 생성 및 암호화
            String tempPassword = PasswordUtil.generateTempPassword();
            center.setCenterMemberPw(passwordEncoder.encode(tempPassword));

            // DB에 새 비밀번호 저장
            centerMemberMapper.updateCenterPassword(center);

            // 임시 비밀번호 이메일 발송
            emailService.sendTempPassword(centerMemberEmail, tempPassword);

        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            log.error("비밀번호 재설정 중 오류 발생", e);
            throw new RuntimeException("비밀번호 재설정 중 오류가 발생했습니다.", e);
        }
    }

    /**
     * 센터 회원 정보의 유효성을 검사하는 private 메소드
     *
     * @param memberDTO 검사할 센터 회원 정보
     * @throws IllegalArgumentException 필수 정보가 누락되었을 때
     */
    private void validateMemberDTO(CenterMemberDTO memberDTO) {
        // 각 필수 항목 검사
        if (memberDTO.getCenterMemberId() == null || memberDTO.getCenterMemberId().trim().isEmpty()) {
            throw new IllegalArgumentException("아이디는 필수입니다.");
        }
        if (memberDTO.getCenterMemberPw() == null || memberDTO.getCenterMemberPw().trim().isEmpty()) {
            throw new IllegalArgumentException("비밀번호는 필수입니다.");
        }
        if (memberDTO.getBusinessFile() == null || memberDTO.getBusinessFile().isEmpty()) {
            throw new IllegalArgumentException("사업자등록증은 필수입니다.");
        }
        if (memberDTO.getCenterMemberPhoneNumber() == null || memberDTO.getCenterMemberPhoneNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("핸드폰 번호는 필수입니다.");
        }
        if (memberDTO.getCenterMemberEmail() == null || memberDTO.getCenterMemberEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("이메일은 필수입니다.");
        }
    }
}