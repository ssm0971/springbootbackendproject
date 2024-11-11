package com.example.hope_dog.dto.admin;

import lombok.*;

@Getter @Setter
@ToString
@NoArgsConstructor
public class AdminFileDTO {
private Long fileNo;
private Long noticeNo;
private String fileName;
private String storedFileName;
private String fileUuid;
private String filePath;
private String uploadDate;
}