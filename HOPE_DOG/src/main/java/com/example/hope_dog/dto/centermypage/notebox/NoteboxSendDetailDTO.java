package com.example.hope_dog.dto.centermypage.notebox;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class NoteboxSendDetailDTO {
    private Long noteboxSendNo;     // 쪽지 번호
    private String noteboxSendTitle;    // 쪽지 제목
    private String noteboxSendContent;  // 쪽지 내용
    private String noteboxSendRegiDate; // 작성일
    private String noteboxReceiverName; // 수신자 이름

}
