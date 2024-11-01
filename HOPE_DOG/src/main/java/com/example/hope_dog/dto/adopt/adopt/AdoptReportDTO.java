package com.example.hope_dog.dto.adopt.adopt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter @ToString
@NoArgsConstructor
public class AdoptReportDTO {
    private Long reportNo;
    private String reportCate; // 기본값 설정
    private String reportContent; // 신고 사유
    private Date reportRegidate;
    private Long reportWriter; // 신고자
    private Long reportContentNo; // 신고할 게시글 번호
}
