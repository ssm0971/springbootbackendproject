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
    private String noteboxSendS;
    private String noteboxSendR;
    private String noteboxSendTitle;
    private String noteboxSendContent;
    private Date noteboxSendRegidate;
    private Long memberNo;
    private Long centerMemberNo;
    private String noteboxSendSName;
    private String noteboxSendRName;
}
