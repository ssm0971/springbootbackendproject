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
public class AdoptRequestListDTO {
    private Long adoptRequestNo;
    private String adoptRequestName;
    private String adoptTitle;
    private Date adoptRegiDate;
    private Long memberNo;
    private String memberNickName;
    private String adoptRequestAdoptAgreement;
    private String adoptStatus;
}
