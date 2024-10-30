package com.example.hope_dog.dto.centermypage.notebox;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class NoteboxSendDetailDTO {
    private Long noteboxSendNo;
    private String noteboxSendTitle;
    private String noteboxSendContent;
    private String noteboxSendRegiDate;
    private String noteboxSenderName;

}
