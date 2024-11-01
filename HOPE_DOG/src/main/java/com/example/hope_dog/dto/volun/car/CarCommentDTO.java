package com.example.hope_dog.dto.volun.car;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter @ToString
@NoArgsConstructor
public class CarCommentDTO {
    private Long carCommentNo;      // 댓글 번호
    private Long carNo;             // 게시글 번호
    private String carComment;       // 댓글 내용
    private Date carCommentRegiDate; // 댓글 작성일
    private Date carUpdateDate;      // 댓글 수정일
    private Long carCommentWriter;    // 댓글 작성자

    private String memberNickname; // 댓글 작성자 닉네임(회원)
    private String centerMemberName;    // 댓글 작성자 이름(센터)
}
