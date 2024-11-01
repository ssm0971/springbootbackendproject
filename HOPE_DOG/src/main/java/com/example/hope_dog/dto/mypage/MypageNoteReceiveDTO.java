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
public class MypageNoteReceiveDTO {
    //받은 쪽지 TBL_NOTEBOX_RECEIVE
    //상태 송신자 수신자 제목 내용 날짜
    //NOTEBOX_RECEIVE_READ / NOTEBOX_RECEIVE_S / NOTEBOX_RECEIVE_R / NOTEBOX_RECEIVE_TITLE / NOTEBOX_RECEIVE_CONTENT
    //NOTEBOX_RECEIVE_REGIDATE

    private String noteboxReceiveRead;
    private String noteboxReceiveS;
    private String noteboxReceiveR;
    private String noteboxReceiveTitle;
    private String noteboxReceiveContent;
    private Date noteboxReceiveRegidate;
    private Long memberNo;
    private Long centerMemberNo;
    private String noteboxReceiveSName;
    private String noteboxReceiveRName;
}

