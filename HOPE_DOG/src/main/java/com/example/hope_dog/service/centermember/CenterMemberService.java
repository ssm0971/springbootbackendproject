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

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CenterMemberService {

    private final CenterMemberMapper centerMemberMapper;
    private final MemberMapper memberMapper;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    @Value("${file.upload.path:C:/upload/}")
    private String fileUploadPath;

    /**
     * 센터 회원 가입
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

            // 4. 파일 처리
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
     * 사업자등록증 파일 저장
     */
    private void saveBusinessFile(CenterMemberDTO memberDTO, Long centerMemberNo) throws IOException {
        MultipartFile file = memberDTO.getBusinessFile();
        String originalFilename = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String savedFileName = uuid + extension;
        String savePath = fileUploadPath + savedFileName;

        // 디렉토리 생성
        File directory = new File(fileUploadPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // 파일 정보 생성
        CenterFileDTO fileDTO = new CenterFileDTO();
        fileDTO.setCenterFileName(originalFilename);
        fileDTO.setCenterFileUuid(uuid);
        fileDTO.setCenterFilePath(savePath);
        fileDTO.setCenterMemberNo(centerMemberNo);

        // 파일 저장
        file.transferTo(new File(savePath));
        centerMemberMapper.insertCenterFile(fileDTO);
    }

    /**
     * 로그인 처리
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
     * 이메일 중복 체크
     */
    public boolean checkEmail(String email) {
        try {
            int count = centerMemberMapper.checkCenterEmail(email);
            return count == 0;  // 중복된 이메일이 없으면 true 반환
        } catch (Exception e) {
            log.error("이메일 중복 체크 중 오류 발생", e);
            throw new RuntimeException("이메일 중복 체크 중 오류가 발생했습니다.", e);
        }
    }

    /**
     * 아이디 찾기
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
     * 비밀번호 재설정
     */
    @Transactional
    public void centerResetPassword(String centerMemberName, String centerMemberId, String centerMemberEmail) {
        try {
            // 1. 센터 회원 정보 확인
            CenterMemberDTO center = centerMemberMapper.findCenterByNameIdEmail(
                    centerMemberName,
                    centerMemberId,
                    centerMemberEmail
            );

            if (center == null) {
                throw new IllegalArgumentException("입력하신 정보와 일치하는 센터 회원이 없습니다.");
            }

            // 2. 임시 비밀번호 생성 및 암호화
            String tempPassword = PasswordUtil.generateTempPassword();
            center.setCenterMemberPw(passwordEncoder.encode(tempPassword));

            // 3. 비밀번호 업데이트
            centerMemberMapper.updateCenterPassword(center);

            // 4. 이메일 발송
            emailService.sendTempPassword(centerMemberEmail, tempPassword);

        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            log.error("비밀번호 재설정 중 오류 발생", e);
            throw new RuntimeException("비밀번호 재설정 중 오류가 발생했습니다.", e);
        }
    }

    /**
     * 회원 정보 유효성 검사
     */
    private void validateMemberDTO(CenterMemberDTO memberDTO) {
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