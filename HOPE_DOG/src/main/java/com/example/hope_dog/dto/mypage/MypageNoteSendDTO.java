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
public class MypageNoteSendDTO {
    private String memberNickname;
    private Long noteboxSendNo; // NOTEBOX_SEND_NO
    private String noteboxSendTitle; // NOTEBOX_SEND_TITLE
    private String noteboxSendContent; // NOTEBOX_SEND_CONTENT
    private Date noteboxSendRegiDate; // NOTEBOX_SEND_REGIDATE
    private Long noteboxSendR; // NOTEBOX_SEND_R
    private String noteboxReceiverName;// noteboxReceiverName
    private String noteboxSenderName;
    private Long noteboxReceiveNo;
}
