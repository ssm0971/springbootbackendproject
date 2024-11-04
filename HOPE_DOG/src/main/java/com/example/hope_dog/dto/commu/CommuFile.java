package com.example.hope_dog.dto.commu;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Getter @Setter @ToString
@NoArgsConstructor
public class CommuFile {
    private Long fileNo;              // 파일 번호
    private Long commuNo;             // 연결된 커뮤니티 게시글 번호
    private String fileName;          // 원본 파일 이름
    private String storedFileName;    // 저장된 파일 이름
    private String fileUuid;          // 파일 UUID
    private String filePath;          // 파일 경로
    private Date uploadTime;          // 파일 업로드 시간
}
