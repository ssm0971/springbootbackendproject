package com.example.hope_dog.dto.volun.volun;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@NoArgsConstructor
public class VolunFileDTO {
    private Long fileNo;
    private Long volunNo;
    private String fileName;
    private String storedFileName;
    private String fileUuid;
    private String filePath;
    private String uploadDate;
}
