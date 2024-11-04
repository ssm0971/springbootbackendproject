package com.example.hope_dog.dto.mypage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MpNoteboxReceiveDetailDTO {
    private Long noteboxReceiveNo;       // 쪽지 번호
    private String noteboxReceiveTitle;   // 쪽지 제목
    private String noteboxReceiveContent; // 쪽지 내용
    private String noteboxReceiveRegiDate; // 작성일
    private String noteboxSenderName;   // 발신자 이름
    private String noteboxReceiveRead;    // 읽음 여부 (예: "Y" 또는 "N")
    private String noteboxReceiverName; // 수신자 이름
}