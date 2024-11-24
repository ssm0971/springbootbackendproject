package com.example.hope_dog.dto.admin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AdminReportDTO {
    private Long reportNo;
    private String reportType;  //  post/comment 구분
    private String reportCate;  //  commu/volun/... 구분
    private String reportContent;
    private String reportRegidate;
    private String reportWriter;
    private Long reportContentNo;
}