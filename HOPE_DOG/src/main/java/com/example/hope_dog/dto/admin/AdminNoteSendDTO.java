package com.example.hope_dog.dto.admin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AdminNoteSendDTO {
    private Long noteboxSendNo;
    private String noteboxSendTitle;
    private String noteboxSendContent;
    private String noteboxSendRegiDate;
    private Long noteboxSendR;
    private String noteboxSendRname;
}
