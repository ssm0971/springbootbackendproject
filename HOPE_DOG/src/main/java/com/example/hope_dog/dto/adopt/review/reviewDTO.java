package com.example.hope_dog.dto.adopt.review;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@NoArgsConstructor
public class reviewDTO {
    private Long adoptNo;
    private String adoptTitle;
    private String adoptIntroduce;
    private Long fileId;
    private String name;
    private String uploadPath;
    private String uuid;
}
