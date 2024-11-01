package com.example.hope_dog.mapper.member;

import com.example.hope_dog.dto.member.MemberDTO;
import com.example.hope_dog.dto.member.MemberSessionDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {
    void insertMember(MemberDTO memberDTO);

    // memberId 조회
    Long selectMemberNo(@Param("memberId") String memberId, @Param("memberPw") String memberPw);


    // 로그인 정보 조회
    MemberSessionDTO selectLoginInfo(@Param("memberId") String memberId);

    // 닉네임 중복 체크
    int checkNickname(@Param("memberNickname") String memberNickname);

    // 이메일 중복 체크
    int checkMemberEmail(@Param("memberEmail") String memberEmail);


   //아이디 찾기 이름과 핸드폰번호로
    String findMemberId(@Param("memberName") String memberName,
                        @Param("memberPhoneNumber") String memberPhoneNumber);


    // 이름, 아이디, 이메일로 회원 찾기
    MemberDTO findMemberByNameIdEmail(@Param("memberName") String memberName,
                                      @Param("memberId") String memberId,
                                      @Param("memberEmail") String memberEmail);

    // 비밀번호 업데이트
    void updateMemberPassword(MemberDTO member);

    void updateAllPasswords(@Param("memberId") String memberId, @Param("encodedPassword") String encodedPassword);

    // 모든 회원 조회
    List<MemberDTO> selectAllMembers();

    // 소셜 로그인 관련 메서드
    MemberDTO findByProviderAndProviderId(@Param("provider") String provider,
                                          @Param("providerId") String providerId);


    // 이메일로 회원 조회
    MemberDTO findByEmail(@Param("memberEmail") String memberEmail);

    // 소셜 로그인 정보 업데이트
    void updateMemberSocialInfo(MemberDTO memberDTO);
}
