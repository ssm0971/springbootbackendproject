package com.example.hope_dog.dto.adopt.protect;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter @Setter @ToString
public class ProtectWriteDTO {
    private Long protectNo;
    private LocalDate protectPeriodstart;
    private LocalDate protectPeriodend;
    private String protectName;
    private String protectBreed;
    private String protectNeutering;
    private String protectGender;
    private String protectWeight;
    private LocalDate protectBirth;
    private String protectIntroduce;
    private String protectTitle;
    private String protectContent;
    private String centerMemberNo;
}
