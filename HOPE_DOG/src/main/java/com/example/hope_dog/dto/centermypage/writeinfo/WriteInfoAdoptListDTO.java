package com.example.hope_dog.dto.centermypage.writeinfo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class WriteInfoAdoptListDTO {
    private Long adoptNo;
    private String adoptTitle;
//    private String adoptContent;
    private Date adoptRegiDate;
    private String adoptStatus;
    private Long commentCount;
    private String centerName;
}
