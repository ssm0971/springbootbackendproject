package com.example.hope_dog.dto.adopt.review;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class ReviewWriteDTO {
    private Long reviewNo;
    private String reviewTitle;
    private String reviewContent;
    private String MemberNo;
}
