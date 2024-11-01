package com.example.hope_dog.dto.admin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//CENTER_MEMBER_NO,
//CENTER_MEMBER_ID,
//CENTER_MEMBER_NAME,
//CENTER_MEMBER_ZIPCODE,
//CENTER_MEMBER_EMAIL,
//CENTER_MEMBER_ADDRESS,
//CENTER_MEMBER_DETAIL_ADDRESS,
//CENTER_MEMBER_PHONE_NUMBER,
//TBL_CENTER_MEMBER_STATUS

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AdminCenterMemberDTO {
    private Long centerMemberNo;
    private String centerMemberId;
    private String centerMemberName;
    private String centerMemberPhoneNumber;
    private String centerMemberEmail;
    private String centerMemberZipcode;
    private String centerMemberAddress;
    private String centerMemberDetailAddress;
    private char centerMemberStatus;
    private Long centerFileNo;
    private String centerFileName;
    private String centerFileUuid;
    private String centerFilePath;
}
