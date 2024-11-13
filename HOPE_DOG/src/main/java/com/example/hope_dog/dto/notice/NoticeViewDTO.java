package com.example.hope_dog.dto.notice;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Getter @Setter @ToString
@NoArgsConstructor
public class NoticeViewDTO {
    private Long noticeNo;
    private String noticeCate;
    private String noticeTitle;
    private String noticeContent;
    private Date noticeRegidate;
    private String adminId;
    private Long adminNo;

    private String noticeUpdateDate;
}

