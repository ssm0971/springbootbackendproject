package com.example.hope_dog.dto.admin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AdminPostDTO {
    private String postType;
    private Long postNo;
    private String postTitle;
    private String postContent;
    private String postWriter;
    private String postUpdateDate;
}
