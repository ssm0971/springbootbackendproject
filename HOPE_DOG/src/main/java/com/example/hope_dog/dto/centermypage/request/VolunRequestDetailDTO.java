package com.example.hope_dog.dto.centermypage.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class VolunRequestDetailDTO {
    private Long volunRequestNo;
    private String volunRequestName;
    private String volunRequestPhone;
    private String volunRequestPhoneSub;
    private String volunRequestEmail;
    private String volunRequestGender;
    private Date volunRequestAge;
    private String volunRequestZipcode;
    private String volunRequestAddress;
    private String volunRequestAddressDetail;
    private String volunRequestJob;
    private String volunRequestMarry;
    private String volunRequestInfoAgreement;
    private String volunRequestQ1;
    private String volunRequestQ2;
    private String volunRequestQ3;
    private String volunRequestQ4;
    private String volunRequestAgreement;
    private Long volunNo;
    private Long memberNo;
    private Long centerMemberNo;
    private String volunTitle;
    private Date volunRegiDate;
    private String memberNickname;
    private String centerMemberName;
    private String volunRequestStatus;
}
