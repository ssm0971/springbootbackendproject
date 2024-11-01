package com.example.hope_dog.dto.admin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//MEMBER_NO,
//MEMBER_ID,
//MEMBER_NAME,
//MEMBER_NICKNAME,
//MEMBER_EMAIL,
//MEMBER_PHONE_NUMBER,
//MEMBER_GENDER,
//MEMBER_ZIPCODE,
//MEMBER_ADDRESS,
//MEMBER_DETAIL_ADDRESS,
//MEMBER_TWO_FACTOR_ENABLED

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AdminMemberDTO {
    private Long memberNo;
    private String memberId;
    private String memberEmail;
    private String memberName;
    private String memberNickname;
    private String memberPhoneNumber;
    private char memberGender;
    private String memberZipcode;
    private String memberAddress;
    private String memberDetailAddress;
    private char memberTwoFactorEnabled;
}
