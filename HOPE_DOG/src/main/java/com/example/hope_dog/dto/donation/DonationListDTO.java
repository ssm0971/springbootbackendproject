package com.example.hope_dog.dto.donation;

import lombok.*;

import java.util.Date;

@Getter @Setter @ToString
@NoArgsConstructor
@Data

public class DonationListDTO {

    private Long donaNo;
    private String donaTitle;
    private String donaContent;
    private Date donaRegidate;
    private Date donaUpdatedate;
    private String centerMemberNo;
    private String centerMemberName;
    private String centerMemberId;
    private Long donaCommentNo;
    private String donaCommentRegidate;
    //    private String donaCommentUpdatedate;
    private String donaCommentContent;
    private String donaCommentWriter;
}
