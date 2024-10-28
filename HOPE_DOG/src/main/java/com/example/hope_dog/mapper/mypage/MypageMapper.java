package com.example.hope_dog.mapper.mypage;

import com.example.hope_dog.dto.member.MemberDTO;
import com.example.hope_dog.dto.member.MemberSessionDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MypageMapper {
    // 사용자 ID로 회원 정보를 가져오는 메서드
    MemberSessionDTO findMemberById(@Param("memberId") String memberId);
    MemberSessionDTO findMemberByNo(@Param("memberNo") String memberNo);
    MemberSessionDTO findMemberByEmail(@Param("memberEmail") String memberEmail);
    MemberSessionDTO findMemberByName(@Param("memberName") String memberName);
    MemberSessionDTO findMemberByNickname(@Param("memberNickname") String memberNickname);
    //    public class MemberSessionDTO {
//        private Long memberNo;
//        private String memberId;
//        private String memberEmail;
//        private String memberName;
//        private String memberNickname;
//        private String memberLoginStatus;
//        private String memberTwoFactorEnabled;
//    }
}

