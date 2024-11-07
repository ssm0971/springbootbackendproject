package com.example.hope_dog.mapper.centermypage;

import com.example.hope_dog.dto.centermypage.CenterProfileDTO;
import com.example.hope_dog.dto.centermypage.CenterUpdateProfileDTO;
import com.example.hope_dog.dto.centermypage.CenterViewProfileDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CenterMypageMapper {

    // 프로필 조회 메서드
    CenterProfileDTO centerProfile(Long centerMemberNo);

    // 프로필 수정 페이지 정보 조회
    CenterViewProfileDTO viewProfile(Long centerMemberNo);

    // 프로필 업데이트
    int updateCenterProfile(CenterUpdateProfileDTO centerUpdateProfileDTO);

    // 비밀번호 일치 확인
    boolean checkPasswordMatch(@Param("centerMemberNo") Long centerMemberNo, @Param("centerMemberPw") String centerMemberPw);

    // 이메일 중복 검사 메서드
    int updateCheckCenterEmail(
            @Param("centerMemberEmail") String centerMemberEmail,
            @Param("currentEmail") String currentEmail
    );

    // 회원 탈퇴
    int deleteCenterMember(Long centerMemberNo);
}