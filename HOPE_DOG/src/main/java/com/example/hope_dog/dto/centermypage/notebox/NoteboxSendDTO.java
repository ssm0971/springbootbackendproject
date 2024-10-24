package com.example.hope_dog.dto.centermypage.notebox;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class NoteboxSendDTO {
    private Long noteboxSendNo;
    private String noteboxSendTitle;
    private String noteboxSendContent;
    private String noteboxSendRediDate;
    private String noteboxSendS;
    private String noteboxSendR;
}
