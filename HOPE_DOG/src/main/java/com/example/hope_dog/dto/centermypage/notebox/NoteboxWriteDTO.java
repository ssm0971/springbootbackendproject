package com.example.hope_dog.dto.centermypage.notebox;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class NoteboxWriteDTO {
    private Long noteboxSendNo;
    private String noteboxSendTitle;
    private String noteboxSendContent;
    private String noteboxSendRediDate;
    private String noteboxSendS;
    private String noteboxSendR;

    private Long noteboxReceiveNo;
    private String noteboxReceiveTitle;
    private String noteboxReceiveContent;
    private String noteboxReceiveRegiDate;
    private Boolean noteboxReceiveRead;
    private String noteboxReceiveReadDate;
    private String noteboxReceiveS;
    private String noteboxReceiveR;
}
