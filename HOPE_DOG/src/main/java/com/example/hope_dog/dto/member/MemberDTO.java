package com.example.hope_dog.dto.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@NoArgsConstructor
public class MemberDTO {
    private Long memberNo;
    private String memberId;
    private String memberEmail;
    private String memberPw;
    private String memberName;
    private String memberNickname;
    private String memberPhoneNumber;
    private String memberGender;
    private String memberZipcode;
    private String memberAddress;
    private String memberDetailAddress;
    private String memberTwoFactorEnabled;
    private String memberStatus;
    private String memberLoginStatus;

    private String memberNewPw;
    // 카카오 로그인을 위한 필드 추가
    private String provider;    // 로그인 제공자 (ex: "kakao")
    private String providerId;  // 카카오 회원번호
}
