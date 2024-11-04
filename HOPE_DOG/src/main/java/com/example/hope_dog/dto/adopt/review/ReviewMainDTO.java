package com.example.hope_dog.dto.adopt.review;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter @ToString
@NoArgsConstructor
public class ReviewMainDTO {
    private Long  reviewNo;
    private String  reviewTitle;
    private Date  protectRegidate;
    private Long memberNo;
}
