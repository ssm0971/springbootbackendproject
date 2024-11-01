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
public class MypageProtectDTO {
    private Long adoptNo;
    private String protectTitle;
    private String protectStatus;
    private Date protectPeriodend;
    private Long memberNo;
}
