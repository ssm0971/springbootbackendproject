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

}
