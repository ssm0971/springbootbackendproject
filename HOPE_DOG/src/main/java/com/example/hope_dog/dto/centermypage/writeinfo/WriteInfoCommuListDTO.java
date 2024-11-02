package com.example.hope_dog.dto.centermypage.writeinfo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class WriteInfoCommuListDTO {
    private Long commuNo;      // 게시글 번호
    private String commuCate;      // 게시글 분류
    private String commuTitle;     // 게시글 제목
    private String commuContent;   // 게시글 내용
    private Date commuRegiDate;  // 게시글 등록 날짜
    private Long commentCount;    // 댓글 수
    private String centerName; // 센터 회원 이름
}
