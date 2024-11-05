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
public class ProtectRequestDetailDTO {
    private Long protectRequestNo;
    private String protectRequestName;
    private String protectRequestPhone;
    private String protectRequestPhoneSub;
    private String protectRequestEmail;
    private String protectRequestGender;
    private Date protectRequestAge;
    private String protectRequestZipcode;
    private String protectRequestAddress;
    private String protectRequestAddressDetail;
    private String protectRequestJob;
    private String protectRequestMarry;
    private String protectRequestInfoAgreement;
    private String protectRequestQ1;
    private String protectRequestQ2;
    private String protectRequestQ3;
    private String protectRequestQ4;
    private String protectRequestQ5;
    private String protectRequestQ6;
    private String protectRequestQ7;
    private String protectRequestQ8;
    private String protectRequestQ9;
    private String protectRequestQ10;
    private String protectRequestQ11;
    private String protectRequestQ12;
    private String protectRequestQ13;
    private String protectRequestQ14;
    private String protectRequestQ15;
    private String protectRequestAgreement;
    private Long protectNo;
    private Long memberNo;
    private Long centerMemberNo;
    private String protectTitle;
    private Date protectRegidate; // 날짜 형식에 따라 LocalDate 또는 LocalDateTime 사용
    private String memberNickname;
    private String centerMemberName;
}
