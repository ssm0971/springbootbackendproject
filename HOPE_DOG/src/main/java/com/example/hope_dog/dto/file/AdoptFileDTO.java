package com.example.hope_dog.dto.file;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@NoArgsConstructor
public class AdoptFileDTO {
    private Long fileNo;
    private String fileName;
    private String storedFileName;
    private String fileUuid;
    private String filePath;
    private Long adoptNo;
}
