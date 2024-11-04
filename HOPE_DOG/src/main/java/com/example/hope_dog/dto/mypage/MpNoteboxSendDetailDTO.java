package com.example.hope_dog.dto.mypage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MpNoteboxSendDetailDTO {
    private Long noteboxSendNo;     // 쪽지 번호
    private String noteboxSendTitle;    // 쪽지 제목
    private String noteboxSendContent;  // 쪽지 내용
    private String noteboxSendRegiDate; // 작성일
    private String noteboxReceiverName; // 수신자 이름
    private String noteboxSenderName; // 송신자이름

}
