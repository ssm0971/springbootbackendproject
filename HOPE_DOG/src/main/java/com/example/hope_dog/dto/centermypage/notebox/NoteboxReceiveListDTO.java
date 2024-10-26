package com.example.hope_dog.dto.centermypage.notebox;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class NoteboxReceiveListDTO {
    private Long noteboxReceiveNo;
    private String noteboxReceiveTitle;
    private String noteboxReceiveContent;
    private String noteboxReceiveRegiDate;
    private Boolean noteboxReceiveRead;
    private String noteboxReceiveReadDate;
    private String noteboxReceiveS;
    private String noteboxReceiveR;

    private Long centerMemberNo;
    private String sendMember;
}
