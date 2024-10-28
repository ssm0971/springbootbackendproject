package com.example.hope_dog.dto.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@NoArgsConstructor
public class MemberSessionDTO {
    private Long memberNo;
    private String memberId;
    private String memberEmail;
    private String memberName;
    private String memberNickname;
    private String memberLoginStatus;
    private String memberTwoFactorEnabled;
    private String memberPw;
}
