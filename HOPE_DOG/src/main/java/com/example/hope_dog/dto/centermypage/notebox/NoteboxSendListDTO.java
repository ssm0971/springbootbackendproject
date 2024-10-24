package com.example.hope_dog.dto.centermypage.notebox;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class NoteboxSendListDTO {
    private Long noteboxSendNo;
    private String noteboxSendTitle;
    private String noteboxSendContent;
    private Date noteboxSendRegiDate;
    private Long noteboxSendS;
    private Long noteboxSendR;

//    private Long noteboxSendNo;
//    private String noteboxSendTitle;
//    private String noteboxSendContent;
//    private String noteboxSendRediDate;
//    private Long noteboxSendS;
//    private Long noteboxSendR;
//
//    private Long receiveMember;
}
