package com.example.hope_dog.dto.commu;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter @ToString
@NoArgsConstructor
public class CommuDTO {
    private Long commuNo;           // 커뮤니티 글 번호
    private String commuCate;       // 커뮤니티 분류
    private String commuTitle;      // 커뮤니티 제목
    private String commuContent;    // 커뮤니티 내용
    private Date commuRegiDate;     // 등록일
    private Date commuUpdateDate;   // 수정일
    private Long commuGood;      // 조회 수(인기)
    private Long commuWriter;       // 작성자 ID

    private Long memberNo;      //일반회원넘버
    private String memberNickname;  //일반회원 닉네임
    private Long centerMemberNo;    //센터회원 넘버
    private String centerMemberName;    //센터회원 이름
}
