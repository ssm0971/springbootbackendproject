package com.example.hope_dog.dto.centermypage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CenterMemberDTO {
    private Long centerMemberNo;
    private String centerMemberId;
    private String centerMemberName;
    private String centerMemberPw;
    private String centerMemberZipcode;
    private String centerMemberEmail;
    private String centerMemberPhone;
    private String centerMemberAddress;
    private String centerMemberDetailAddress;

}
