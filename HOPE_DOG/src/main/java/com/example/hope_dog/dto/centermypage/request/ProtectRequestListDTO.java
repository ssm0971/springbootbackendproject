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
public class ProtectRequestListDTO {
    private Long protectRequestNo;
    private String protectRequestName;
    private String protectTitle;
    private Date protectRegiDate;
    private Long memberNo;
    private String memberNickName;
    private String protectRequestAgreement;
    private String protectStatus;
}
