package com.example.hope_dog.dto.donation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DonationUpdateDTO {
    private Long donaNo;
    private String donaTitle;
    private String donaContent;
    private Date donaRegidate;
    private Date donaUpdatedate;
    private Long centerMemberNo;
    private String centerMemberName;
    private String centerMemberId;
}