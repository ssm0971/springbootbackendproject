package com.example.hope_dog.dto.adopt.review;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter @ToString
public class ReviewWriteDTO {
    private Long reviewNo;
    private String reviewTitle;
    private String reviewContent;
    private String MemberNo;
}
