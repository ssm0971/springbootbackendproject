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
public class DonaCommentListDTO {
    private Long donaCommentNo;
    private Long donaNo;
    private String donaCommentContent;
    private Date donaCommentRegidate;
    private Date donaCommentUpdatedate;
    private Long donaCommentWriter;
    private String commentWriterName;
    private Long memberNo;
    private Long centerMemberNo;
    private Long adminNo;
}
