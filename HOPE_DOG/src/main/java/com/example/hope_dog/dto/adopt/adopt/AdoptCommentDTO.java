package com.example.hope_dog.dto.adopt.adopt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter @ToString
@NoArgsConstructor
public class AdoptCommentDTO {
    private Long adoptCommentNo;
    private String adoptComment;
    private Date adoptCommentRegidate;
    private Long adoptCommentWriter;
    private Date adoptCommentUpdate;
    private String commentWriterName;
}
