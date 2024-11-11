package com.example.hope_dog.dto.volun.volun;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter @ToString
@NoArgsConstructor
public class VolunCommentDTO {
    private Long volunCommentNo;
    private String volunComment;
    private Date volunCommentRegidate;
    private Long volunCommentWriter;
    private Date volunCommentUpdate;
    private String commentWriterName;
    private Long volunNo;
}
