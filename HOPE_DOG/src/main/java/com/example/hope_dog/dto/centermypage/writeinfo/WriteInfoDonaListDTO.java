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
public class WriteInfoDonaListDTO {
    private Long donaNo;
    private String donaTitle;
//    private String donaContent;
    private Date donaRegiDate;
    private Long commentCount;
    private String centerName;
}
