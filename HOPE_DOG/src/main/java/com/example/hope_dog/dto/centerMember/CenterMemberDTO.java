package com.example.hope_dog.dto.centerMember;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;




@Getter @Setter @ToString
@NoArgsConstructor
public class CenterMemberDTO {
    // 회원 기본 정보
    private Long centerMemberNo;
    private String centerMemberId;
    private String centerMemberName;
    private String centerMemberPw;
    private String centerMemberZipcode;
    private String centerMemberEmail;
    private String centerMemberAddress;
    private String centerMemberDetailAddress;
    private String centerMemberPhoneNumber;
    private String centerMemberStatus;
    // 파일 정보
    private MultipartFile businessFile;  // 첨부파일
}