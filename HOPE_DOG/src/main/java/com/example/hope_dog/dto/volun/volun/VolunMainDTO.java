package com.example.hope_dog.dto.volun.volun;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter @ToString
@NoArgsConstructor
public class VolunMainDTO {
    private Long volunNo;
    private Date volunPeriodstart;
    private Date volunPeriodend;
    private Date volunStart;
    private Date volunEnd;
    private String volunLocal;
    private String volunTime;
    private String volunPeople;
    private String volunTitle;
    private String volunContent;
    private Date volunRegidate;
    private String volunStatus;
    private Long centerMemberNo;
    private String centerMemberName;
    private String centerMemberStatus;
}
