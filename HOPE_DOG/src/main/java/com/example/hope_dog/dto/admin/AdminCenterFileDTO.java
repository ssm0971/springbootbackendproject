package com.example.hope_dog.dto.admin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@NoArgsConstructor
public class AdminCenterFileDTO {
private Long centerFileNo;
private String centerFileName;
private String centerFileUuid;
private String centerFilePath;
private Long centerMemberNo;
}