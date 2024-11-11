package com.example.hope_dog.dto.mypage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MpProtectRequestDTO {
    private Long protectRequestNo;
    private String protectRequestName;
    private String protectRequestPhone;
    private String protectRequestPhoneSub;
    private String protectRequestEmail;
    private String protectRequestGender;
    private LocalDate protectRequestAge;
    private String protectRequestZipcode;
    private String protectRequestAddress;
    private String protectRequestAddressDetail;
    private String protectRequestJob;
    private String protectRequestMarry;
    private String protectRequestInfoAgreement;
    private String proRequestQ1;
    private String proRequestQ2;
    private String proRequestQ3;
    private String proRequestQ4;
    private String proRequestQ5;
    private String proRequestQ6;
    private String proRequestQ7;
    private String proRequestQ8;
    private String proRequestQ9;
    private String proRequestQ10;
    private String proRequestQ11;
    private String proRequestQ12;
    private String proRequestQ13;
    private String proRequestQ14;
    private String proRequestQ15;
    private String protectRequestAgreement;
    private Long protectNo;
    private Long memberNo;
    private Long centerMemberNo;
    private String protectTitle;
    private Date protectRegidate; // 날짜 형식에 따라 LocalDate 또는 LocalDateTime 사용
    private String memberNickname;
    private String centerMemberName;
    private String proRequestStatus;
}
