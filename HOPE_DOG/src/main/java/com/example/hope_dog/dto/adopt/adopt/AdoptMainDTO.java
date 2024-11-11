package com.example.hope_dog.dto.adopt.adopt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter @ToString
@NoArgsConstructor
public class AdoptMainDTO {
    private Long adoptNo;
    private String adoptTitle;
    private String adoptStatus;
    private String adoptIntroduce;
    private String adoptName;
    private String adoptGender;
    private String adoptNeutering;
    private Date adoptBirth;
    private String adoptWeight;
    private Date adoptRegidate;
    private Date adoptPeriodEnd;
    private Long centerMemberNo;
    private String centerMemberStatus;
}
