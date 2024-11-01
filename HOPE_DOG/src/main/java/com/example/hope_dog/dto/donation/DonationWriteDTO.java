package com.example.hope_dog.dto.donation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DonationWriteDTO {
    private Long donaNo;
    private String donaTitle;
    private String donaContent;
    private String donaRegidate;
    private Long centerMemberNo;
}
