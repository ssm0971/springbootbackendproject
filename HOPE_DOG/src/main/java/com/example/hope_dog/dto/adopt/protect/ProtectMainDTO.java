package com.example.hope_dog.dto.adopt.protect;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter @ToString
@NoArgsConstructor
public class ProtectMainDTO {
    private Long  protectNo;
    private String  protectTitle;
    private String  protectStatus;
    private String  protectIntroduce;
    private String  protectName;
    private String  protectGender;
    private String  protectNeutering;
    private Date  protectBirth;
    private String  protectWeight;
    private Date  protectRegidate;
    private Date  protectPeriodEnd;
    private Long centerMemberNo;
    private String centerMemberStatus;
}
