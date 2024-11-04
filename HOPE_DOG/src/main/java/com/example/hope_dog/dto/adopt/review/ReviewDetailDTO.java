package com.example.hope_dog.dto.adopt.review;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter @ToString
@NoArgsConstructor
public class ReviewDetailDTO {
    private Long  reviewNo;
    private String  reviewTitle;
    private String memberName;
    private String  reviewContent;
}
