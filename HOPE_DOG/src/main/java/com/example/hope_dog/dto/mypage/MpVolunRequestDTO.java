package com.example.hope_dog.dto.mypage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Getter @Setter
@ToString @NoArgsConstructor
public class MpVolunRequestDTO {
    private Long volunRequestNo;
    private String volunRequestName;
    private String volunRequestPhone;
    private String volunRequestPhoneSub;
    private String volunRequestEmail;
    private String volunRequestGender;
    private LocalDate volunRequestAge;
    private String volunRequestZipcode;
    private String volunRequestAddress;
    private String volunRequestAddressDetail;
    private String volunRequestJob;
    private String volunRequestMarry;
    private String volunRequestAgreement;
    private String volunRequestQ1;
    private String volunRequestQ2;
    private String volunRequestQ3;
    private String volunRequestQ4;
    private Long volunNo;
    private Long memberNo;
    private Long centerMemberNo;
    private String volunTitle;
    private Date volunRegidate; // 날짜 형식에 따라 LocalDate 또는 LocalDateTime 사용
    private String memberNickname;
    private String centerMemberName;
    private String volunRequestStatus;
}
