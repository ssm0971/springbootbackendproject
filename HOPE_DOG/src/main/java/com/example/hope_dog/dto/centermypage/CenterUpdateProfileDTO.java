package com.example.hope_dog.dto.centermypage;

//@Getter
//@Setter
//@ToString
//@NoArgsConstructor
//public class CenterUpdateProfileDTO {
//    private Long centerMemberNo;
//    private String centerMemberName;          // 회원 이름
//    private String centerMemberEmail;         // 이메일
//    private String centerMemberPhoneNumber;   // 전화번호
//    private String centerMemberZipcode;       // 우편번호
//    private String centerMemberAddress;       // 주소
//    private String centerMemberDetailAddress; // 상세주소
//    private String centerMemberAgree;      // 알림 수신 동의 (Y or N)
//
//    private String centerMemberPassword;      //현재 비밀번호
//    private String centerMemberNewPassword;   //새 비밀번호
//
//}

import lombok.*;

@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
public class CenterUpdateProfileDTO {
    private Long centerMemberNo;
    private String centerMemberName;
    private String centerMemberEmail;
    private String centerMemberPhoneNumber;
    private String centerMemberZipcode;
    private String centerMemberAddress;
    private String centerMemberDetailAddress;
    private String centerMemberAgree;

    private String centerMemberPw;         // 현재 비밀번호
    private String centerMemberNewPw;      // 새 비밀번호
//    private String confirmNewPw;           // 새 비밀번호 확인
}
