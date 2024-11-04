package com.example.hope_dog.dto.adopt.review;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter @ToString
@NoArgsConstructor
public class ReviewCommentDTO {
    private Long reviewCommentNo;
    private String  reviewComment;
    private Date  reviewCommentRegidate;
    private Long  reviewCommentWriter;
    private Date  reviewCommentUpdate;
    private String commentWriterName;
    private Long  reviewNo;
}
