package com.example.hope_dog.dto.adopt.adopt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter @ToString
@NoArgsConstructor
public class MainDTO {
    private Long adoptNo;
    private String adoptTitle;
    private Date adoptRegidate;
    private String adoptStatus;
    private String centerMemberName;
}
