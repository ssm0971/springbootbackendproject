package com.example.hope_dog.mapper.mypage;

import com.example.hope_dog.dto.member.MemberDTO;
import com.example.hope_dog.dto.mypage.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MypageMapper {

    List<MypageDTO> mypageProfile(Long memberNo);

    List<MypageAdoptListDTO> mypageAdoptList(Long memberNo);

    List<MypageProtectDTO> mypageProtectList(Long memberNo);

    List<MypageVolunDTO> mypageVolunList(Long memberNo);

    List<MypagePostsDTO> mypagePostsList(Long memberNo);

    // 프로필 수정 페이지 정보 조회
    MypageViewProfileDTO viewProfile(Long memberNo);

    // 프로필 업데이트
    int updateProfile(MypageUpdateProfileDTO mypageUpdateProfileDTO);

    // 비밀번호 일치 확인
    boolean checkPasswordMatch(@Param("memberNo") Long memberNo, @Param("memberPw") String memberPw);

    // 닉네임 중복 검사 메서드
    int checkedNickname(
            @Param("memberNickname") String memberNickname,
            @Param("currentNickname") String currentNickname
    );

    // 이메일 중복 검사 메서드
    int updateCheckedEmail(
            @Param("memberEmail") String memberEmail,
            @Param("currentEmail") String currentEmail
    );

    //    임시보호신청서 상세페이지 정보 조회
    MpProtectRequestDTO protectRequestInfo(Long protectRequestNo);

    // 임시보호신청서 수정
    void updateProtectRequest(MpProtectRequestDTO mpProtectRequestDTO);

    // 회원 탈퇴
    int deleteMember(Long memberNo);

    // 입양 상세페이지 정보 조회
    MpAdoptRequestDTO adoptRequestInfo(Long adoptRequestNo);

    // 입양신청서 수정
    void updateAdoptRequest(MpAdoptRequestDTO mpAdoptRequestDTO);

    // 봉사 상세페이지 정보 조회
    MpVolunRequestDTO volunRequestInfo(Long volunRequestNo);

    // 봉사신청서 수정
    void updateVolunRequest(MpVolunRequestDTO mpVolunRequestDTO);
}

