package com.example.hope_dog.dto.mypage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MypageUpdateProfileDTO {
    private Long memberNo;
    private String memberId;
    private String memberEmail;
    private String memberPw;
    private String memberName;
    private String memberNickname;
    private String memberPhoneNumber;
    private String memberZipcode;
    private String memberAddress;
    private String memberDetailAddress;
    private String memberAgree;

    private String memberNewPw;      // 새 비밀번호
}
