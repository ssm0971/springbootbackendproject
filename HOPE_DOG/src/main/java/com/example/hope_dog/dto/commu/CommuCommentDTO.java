package com.example.hope_dog.dto.commu;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter @ToString
@NoArgsConstructor
public class CommuCommentDTO {
    private Long commuCommentNo;      // 댓글 번호
    private Long commuNo;             // 연결된 커뮤니티 게시글 번호
    private String commuComment;      // 댓글 내용
    private Date commuCommetRegiDate; // 댓글 작성일 //commet로 오타
    private Date commuUpdateDate;     // 댓글 수정일
    private Long commuCommentWriter;  // 댓글 작성자

    private String memberNickname; // 댓글 작성자 닉네임(회원)
    private String centerMemberName;    // 댓글 작성자 이름(센터)
}
