package com.example.hope_dog.dto.admin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AdminVolunRequestDTO {
    private Long volunRequestNo;
    private String volunRequestName;
    private String volunRequestPhone;
    private String volunRequestPhoneSub;
    private String volunRequestEmail;
    private char volunRequestGender;
    private String volunRequestAge;
    private String volunRequestZipcode;
    private String volunRequestAddress;
    private String volunRequestAddressDetail;
    private String volunRequestJob;
    private String volunRequestMarry;
    private char volunRequestAgreement;
    private String volunRequestQ1;
    private String volunRequestQ2;
    private String volunRequestQ3;
    private String volunRequestQ4;
    private Long volunNo;
    private String memberName;
    private String memberNickName;
    private String memberPhoneNumber;
    private String memberEmail;
    private String centerMemberName;
    private char volunRequestStatus;
    private String volunRequestRegiDate;

}
