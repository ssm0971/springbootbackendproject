package com.example.hope_dog.dto.centermypage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CenterViewProfileDTO {
    private String centerMemberName;          // 회원 이름
    private String centerMemberEmail;         // 이메일
    private String centerMemberPhoneNumber;   // 전화번호
    private String centerMemberZipcode;       // 우편번호
    private String centerMemberAddress;       // 주소
    private String centerMemberDetailAddress; // 상세주소
    private String centerMemberAgree;      // 알림 수신 동의 (Y or N)
}
