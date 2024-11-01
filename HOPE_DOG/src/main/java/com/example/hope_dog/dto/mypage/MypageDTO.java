package com.example.hope_dog.dto.mypage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@NoArgsConstructor
public class MypageDTO {
//    private Long memberNo;
//    private String memberId;
//    private String memberEmail;
//    private String memberName;
//    private String memberNickname;
//    private String memberLoginStatus;
//    private String memberTwoFactorEnabled;
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
}
