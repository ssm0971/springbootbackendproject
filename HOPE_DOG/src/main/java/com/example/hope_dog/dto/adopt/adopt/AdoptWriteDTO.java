package com.example.hope_dog.dto.adopt.adopt;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter @Setter
public class AdoptWriteDTO {
    private Long adoptNo;
    private LocalDate adoptPeriodstart;
    private LocalDate adoptPeriodend;
    private String adoptName;
    private String adoptBreed;
    private String adoptNeutering;
    private String adoptGender;
    private String adoptWeight;
    private LocalDate adoptBirth;
    private String adoptIntroduce;
    private String adoptTitle;
    private String adoptContent;
    private String centerMemberNo;
}
