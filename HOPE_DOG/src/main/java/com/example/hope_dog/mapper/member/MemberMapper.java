package com.example.hope_dog.mapper.member;

import com.example.hope_dog.dto.member.MemberDTO;
import com.example.hope_dog.dto.member.MemberSessionDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {
    void insertMember(MemberDTO memberDTO);

    // memberId 조회
    Long selectMemberNo(@Param("memberId") String memberId, @Param("memberPw") String memberPw);

    // 로그인 정보 조회
    MemberSessionDTO selectLoginInfo(@Param("memberId") String memberId, @Param("memberPw") String memberPw);


    // 닉네임 중복 체크
    int checkNickname(@Param("memberNickname") String memberNickname);

    // 이메일 중복 체크
    int checkEmail(@Param("memberEmail") String memberEmail);

}
