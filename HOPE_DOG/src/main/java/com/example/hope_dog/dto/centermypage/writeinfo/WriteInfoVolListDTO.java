package com.example.hope_dog.dto.centermypage.writeinfo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class WriteInfoVolListDTO {
    private Long volunNo;
    private String volunTitle;
//    private String volunContent;
    private Date volunRegiDate;
    private Long commentCount;
    private String centerName;
}
