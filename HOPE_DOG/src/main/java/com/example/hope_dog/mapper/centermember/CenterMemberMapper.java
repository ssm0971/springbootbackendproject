package com.example.hope_dog.mapper.centermember;

import com.example.hope_dog.dto.centerMember.CenterFileDTO;
import com.example.hope_dog.dto.centerMember.CenterMemberDTO;
import com.example.hope_dog.dto.centerMember.CenterMemberSessionDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CenterMemberMapper {
//    센터멤버 회원가입
    void insertCenterMember(CenterMemberDTO centerMemberDTO);

//    센터멤버 세션
    CenterMemberSessionDTO selectCenterLoginInfo(@Param("centerId") String centerId, @Param("centerPw") String centerPw);

//    이메일 중복체크
    int checkCenterEmail(String centerMemberEmail);

//    아이디 중복체크
    int checkCenterId(String centerMemberId);

//    파일 insert
    void insertCenterFile(CenterFileDTO centerFileDTO);


//    아이디 찾기 이름과 핸드폰번호로 찾기
    String findCenterMemberId(@Param("centerMemberName") String centerMemberName,
                              @Param("centerMemberPhoneNumber") String centerMemberPhoneNumber);


//    // 이름, 아이디, 이메일로 센터 회원 찾기
    CenterMemberDTO findCenterByNameIdEmail(@Param("centerMemberName") String centerMemberName,
                                      @Param("centerMemberId") String centerId,
                                      @Param("centerMemberEmail") String centerEmail);

    // 비밀번호 업데이트
    void updateCenterPassword(CenterMemberDTO center);
}
