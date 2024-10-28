package com.example.hope_dog.dto.admin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AdminCommentDTO {
    private String commentType;
    private Long commentNo;
    private Long postNo;
    private String commentContent;
    private String commentWriter;
    private String commentUpdateDate;
}
