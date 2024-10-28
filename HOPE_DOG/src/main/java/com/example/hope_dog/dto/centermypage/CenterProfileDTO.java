package com.example.hope_dog.dto.centermypage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CenterProfileDTO {
    private Long centerMemberNo;
    private String centerMemberName;
    private String centerMemberEmail;
    private String centerMemberPhoneNumber;
    private String centerMemberAddress;
    private String centerMemberDetailAddress;

}
