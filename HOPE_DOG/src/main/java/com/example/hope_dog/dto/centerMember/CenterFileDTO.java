package com.example.hope_dog.dto.centerMember;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@NoArgsConstructor
public class CenterFileDTO {
    private Long centerFileNo;
    private String centerFileName;
    private String centerFileUuid;
    private String centerFilePath;
    private Long centerMemberNo;
}
