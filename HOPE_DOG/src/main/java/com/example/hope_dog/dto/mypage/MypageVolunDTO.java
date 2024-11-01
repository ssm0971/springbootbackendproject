package com.example.hope_dog.dto.mypage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MypageVolunDTO {
    private Long volunNo;
    private String volunTitle;
    private String volunStatus;
    private Date volunPeriodend;
    private Long memberNo;
}
