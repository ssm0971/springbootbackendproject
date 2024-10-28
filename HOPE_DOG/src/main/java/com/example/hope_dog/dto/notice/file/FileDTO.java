package com.example.hope_dog.dto.notice.file;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter @ToString
@NoArgsConstructor
public class FileDTO {
    private Long fileNo;
    private String fileName;
    private String storedFileName;
    private String fileUuid;
    private Date uploadTime;
    private Long noticeNo;
}
