package com.example.hope_dog.dto.notice;

import lombok.*;

import java.util.Date;

@Getter @Setter @ToString
@NoArgsConstructor
@Data

public class NoticeListDTO {
    private Long noticeNo;
    private String noticeTitle;
    private Date noticeRegidate;
    private String adminId;
    private Long adminNo;
    private String noticeCate;
    private String noticeUpdateDate;
}
