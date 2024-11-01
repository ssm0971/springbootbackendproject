package com.example.hope_dog.dto.admin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AdminNoteReceiveDTO {
    private Long noteboxReceiveNo;
    private String noteboxReceiveTitle;
    private String noteboxReceiveContent;
    private String noteboxReceiveRegiDate;
    private String noteboxReceiveRead;
    private String noteboxReceiveReadDate;
    private Long noteboxReceiveS;
    private String noteboxReceiveSname;
}
