package com.example.hope_dog.dto.adopt.protect;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter @ToString
@NoArgsConstructor
public class ProtectCommentDTO {
    private Long protectCommentNo;
    private String  protectComment;
    private Date  protectCommentRegidate;
    private Long  protectCommentWriter;
    private Date  protectCommentUpdate;
    private String commentWriterName;
    private Long  protectNo;
}
