package com.example.hope_dog.dto.volun.car;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter @ToString
@NoArgsConstructor
public class CarFileDTO {
    private Long fileNo;              // 파일 번호
    private Long carNo;               // 게시글 번호
    private String fileName;          // 파일 이름
    private String storedFileName;    // 저장된 파일 이름
    private String fileUuid;          // UUID
    private Long filePath;            // 파일 경로 (이름에 대한 ID 또는 경로로 추정)
    private Date uploadTime;          // 업로드 시간
}
