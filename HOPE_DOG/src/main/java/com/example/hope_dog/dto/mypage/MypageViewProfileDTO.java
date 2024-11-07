package com.example.hope_dog.dto.mypage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MypageViewProfileDTO {
    private Long memberNo;
    private String memberEmail;
    private String memberName;
    private String memberNickname;
    private String memberPhoneNumber;
    private String memberZipcode;
    private String memberAddress;
    private String memberDetailAddress;
}
