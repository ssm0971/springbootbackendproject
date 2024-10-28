package com.example.hope_dog.dto.admin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    private char gender;
    private String memberZipcode;
    private String address;
    private String addressDetail;
    private char twoFactor;
}
