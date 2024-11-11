package com.example.hope_dog.dto.volun.volun;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Getter @Setter @ToString
@NoArgsConstructor
public class VolunWriteDTO {
    private Long volunNo;
    private LocalDate volunPeriodstart;
    private LocalDate volunPeriodend;
    private LocalDate volunStart;
    private LocalDate volunEnd;
    private String volunLocal;
    private String volunTime;
    private String volunPeople;
    private String volunTitle;
    private String volunContent;
    private LocalDate volunRegidate;
    private String volunStatus;
    private Long centerMemberNo;
}
