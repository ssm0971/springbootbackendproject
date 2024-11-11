package com.example.hope_dog.dto.volun.car;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Getter @Setter @ToString
@NoArgsConstructor

public class CarDetailDTO {
    private Long carNo;         // 게시글 번호
    private String carCate;     // 게시글 분류
    private String carTitle;    // 게시글 제목
    private String carContent;  // 게시글 내용
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date carRegiDate;   // 게시글 등록일
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date carUpdateDate; // 수정일
    private Long carWriter;      // 게시글 작성자


    private Long carCommentNo;      // 댓글 번호
    private String carComment;       // 댓글 내용
    private Date carCommentRegiDate; // 댓글 작성일
    private Long carCommentWriter;    // 댓글 작성자
    private String carWriterName;      //작성자 이름

    private Long memberNo;      //일반회원넘버
    private String memberNickname;  //일반회원 닉네임
    private Long centerMemberNo;    //센터회원 넘버
    private String centerMemberName;    //센터회원 이름
}

