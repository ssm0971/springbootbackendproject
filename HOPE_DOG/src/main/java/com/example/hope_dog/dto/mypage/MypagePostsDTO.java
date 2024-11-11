package com.example.hope_dog.dto.mypage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MypagePostsDTO {
    private Long memberNo;
    private String type;
    private String title;
    private String writer;
    private Date regidate;
    private Long commuNo;
    private Long carNo;
    private Long reviewNo;
//    private String subquery;
}
