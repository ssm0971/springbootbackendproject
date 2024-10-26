package com.example.hope_dog.service.centermember;

import com.example.hope_dog.dto.centerMember.CenterFileDTO;
import com.example.hope_dog.dto.centerMember.CenterMemberDTO;
import com.example.hope_dog.dto.centerMember.CenterMemberSessionDTO;
import com.example.hope_dog.mapper.centermember.CenterMemberMapper;
import com.example.hope_dog.mapper.member.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CenterMemberService {

    public final CenterMemberMapper centerMemberMapper;
    public final MemberMapper memberMapper;


    /**
     * 센터 회원가입 처리
     * @param memberDTO 회원가입 정보
     * @throws IllegalArgumentException 필수 정보 누락 시
     * @throws IOException 파일 처리 실패 시
     */
    public void join(CenterMemberDTO memberDTO) {
        // 필수 입력값 검증
        validateMemberDTO(memberDTO);


        // 아이디 중복 체크
        if (centerMemberMapper.checkCenterId(memberDTO.getCenterMemberId()) > 0) {
            throw new IllegalArgumentException("이미 사용 중인 아이디입니다.");
        }

        // 파일 처리
        handleFile(memberDTO);

        // 회원 정보 저장
        centerMemberMapper.insertCenterMember(memberDTO);
    }

    /**
     * 로그인 처리
     * @param centerId 센터 아이디
     * @param centerPw 센터 비밀번호
     * @return 로그인 세션 정보
     * @throws IllegalArgumentException 로그인 실패 시
     */
    public CenterMemberSessionDTO login(String centerId, String centerPw) {
        CenterMemberSessionDTO sessionDTO = centerMemberMapper.selectCenterLoginInfo(centerId, centerPw);
        if (sessionDTO == null) {
            throw new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }
        return sessionDTO;
    }

    /**
     * 이메일 중복 체크 (모든 회원 테이블 검사)
     */
    public boolean checkEmail(String email) {
        int count = centerMemberMapper.checkCenterEmail(email);
        return count == 0;  // 중복된 이메일이 없으면 true 반환
    }



    /**
     * 회원 정보 검증
     * @param memberDTO 검증할 회원 정보
     * @throws IllegalArgumentException 필수 정보 누락 시
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
        if (memberDTO.getCenterMemberPhoneNumber() == null || memberDTO.getCenterMemberPhoneNumber().trim().isEmpty()){
            throw new IllegalArgumentException("핸드폰 번호는 필수입니다.");
        }
        if (memberDTO.getCenterMemberEmail() == null || memberDTO.getCenterMemberEmail().trim().isEmpty()){
            throw new IllegalArgumentException("이메일은 필수입니다.");
        }


    }

    /**
     * 파일 처리
     * @param memberDTO 파일 정보가 포함된 회원 정보
     * @throws IOException 파일 처리 실패 시
     */
    private void handleFile(CenterMemberDTO memberDTO) {
        MultipartFile file = memberDTO.getBusinessFile();
        if (file != null && !file.isEmpty()) {
            try {
                String originalFilename = file.getOriginalFilename();
                String uuid = UUID.randomUUID().toString();
                String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
                String savedFileName = uuid + extension;
                String savePath = "C:/upload/" + savedFileName;  // 실제 저장 경로로 수정 필요

                // 파일 정보 생성
                CenterFileDTO fileDTO = new CenterFileDTO();
                fileDTO.setCenterFileName(originalFilename);
                fileDTO.setCenterFileUuid(uuid);
                fileDTO.setCenterFilePath(savePath);
                fileDTO.setCenterMemberNo(memberDTO.getCenterMemberNo());

                // 파일 저장
                file.transferTo(new File(savePath));

                // 파일 정보 DB 저장
                centerMemberMapper.insertCenterFile(fileDTO);
            } catch (IOException e) {
                throw new RuntimeException("파일 저장에 실패했습니다.", e);
            }
        }
    }
}

