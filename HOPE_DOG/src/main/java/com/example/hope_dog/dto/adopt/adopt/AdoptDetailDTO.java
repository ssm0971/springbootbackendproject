package com.example.hope_dog.dto.adopt.adopt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter @ToString
@NoArgsConstructor
public class AdoptDetailDTO {
    private Long adoptNo;
    private String adoptTitle;
    private String centerMemberName;
    private Date adoptRegidate;
    private Date adoptPeriodStart;
    private Date adoptPeriodEnd;
    private String adoptName;
    private String adoptBreed;
    private String adoptNeutering;
    private String adoptGender;
    private String adoptWeight;
    private Date adoptBirth;
    private String adoptContent;
    private Long centerMemberNo;
    private String adoptStatus;
    private String adoptIntroduce;
}
