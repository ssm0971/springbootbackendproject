package com.example.hope_dog.dto.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FindPwRequestDTO {
    private String memberName;
    private String memberId;
    private String memberEmail;
}
