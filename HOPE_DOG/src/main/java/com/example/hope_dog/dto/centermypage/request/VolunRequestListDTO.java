package com.example.hope_dog.dto.centermypage.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class VolunRequestListDTO {
    private Long volunRequestNo;
    private String volunRequestName;
    private String volunTitle;
    private Date volunRegiDate;
    private Long memberNo;
    private String memberNickName;
    private String volunRequestAgreement;
    private String volunStatus;
}
