package com.example.hope_dog.dto.centermypage.notebox;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class NoteboxReceiveDetailDTO {
    private Long noteboxReceiveNo;       // 쪽지 번호
    private String noteboxReceiveTitle;   // 쪽지 제목
    private String noteboxReceiveContent; // 쪽지 내용
    private String noteboxReceiveRegiDate; // 등록 날짜
    private String noteboxReceiverName;   // 수신자 이름
    private String noteboxReceiveRead;    // 읽음 여부 (예: "Y" 또는 "N")

}
