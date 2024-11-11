package com.example.hope_dog.dto.commu;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
@Getter @Setter @ToString
@NoArgsConstructor
public class CommuDetailDTO {
    private Long commuNo;           // 커뮤니티 글 번호
    private String commuCate;       // 커뮤니티 분류
    private String commuTitle;      // 커뮤니티 제목
    private String commuContent;    // 커뮤니티 내용
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date commuRegiDate;     // 등록일
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date commuUpdateDate;   // 수정일
    private Long commuGood = 0L;      // 조회 수(인기)
    private Long commuWriter;       // 작성자 ID

    private Long commuCommentNo;      // 댓글 번호
    private String commuComment;      // 댓글 내용
    private Date commuCommentRegiDate; // 댓글 작성일 //commet로 오타
    private Long commuCommentWriter;  // 댓글 작성자
    private String commentWriterName;    //작성자 이름

    // 작성자 정보
    private Long memberNo;          // 일반회원 넘버
    private String memberNickname;  // 일반회원 닉네임
    private Long centerMemberNo;    // 센터회원 넘버
    private String centerMemberName; // 센터회원 이름
}
