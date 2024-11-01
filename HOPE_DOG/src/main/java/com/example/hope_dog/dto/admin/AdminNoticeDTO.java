package com.example.hope_dog.dto.admin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AdminNoticeDTO {
    private Long noticeNo;
    private Long adminNo;
    private String noticeCate;
    private String noticeTitle;
    private String noticeContent;
    private String noticeUpdateDate;
}
