package com.example.hope_dog.dto.mypage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MpNoteboxWriteDTO {

    private String noteboxTitle;    //쪽지 제목
    private String noteboxContent;  //쪽지 내용
//    private String noteboxRegiDate; //쪽지 시간

    private String noteboxReceiverName; //입력한 닉네임 or 센터명

    private Long noteboxS; //발신자
    private Long noteboxR; //수신자
}
