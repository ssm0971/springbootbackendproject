package com.example.hope_dog.dto.adopt.protect;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter @ToString
@NoArgsConstructor
public class ProtectDetailDTO {
    private Long  protectNo;
    private String  protectTitle;
    private String centerMemberName;
    private Date  protectRegidate;
    private Date  protectPeriodStart;
    private Date  protectPeriodEnd;
    private String  protectName;
    private String  protectBreed;
    private String  protectNeutering;
    private String  protectGender;
    private String  protectWeight;
    private Date  protectBirth;
    private String  protectContent;
    private Long centerMemberNo;
    private String  protectStatus;
    private String  protectIntroduce;
}
