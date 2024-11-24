package com.example.hope_dog.dto.donation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DonaCommentReportDTO {
    private Long commentReportNo;        // 신고 번호
    private String commentReportCate;   // 신고 카테고리
    private String commentReport;       // 신고 내용
    private Long commentReportWriter; // 신고자
    private String commentReportRegidate; // 신고 일시
    private Long reportCommentNo;              // 댓글 번호
    private Long reportContentNo;              // 콘텐츠 번호
}

