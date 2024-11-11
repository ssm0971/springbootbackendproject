package com.example.hope_dog.dto.volun.volun;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter @ToString
@NoArgsConstructor
public class VolunReportDTO {
    private Long reportNo;
    private String reportCate; //게시글 카테고리
    private String reportContent; // 신고 사유(게시글)
    private Date reportRegidate;    //신고날짜
    private Long reportWriter; // 신고자
    private Long reportContentNo; // 신고할 게시글 번호
    private String reportComment; //신고 사유(댓글)
    private Long reportCommentNo; //신고할 댓글 번호
}
