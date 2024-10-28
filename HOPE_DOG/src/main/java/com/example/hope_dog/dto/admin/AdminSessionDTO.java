package com.example.hope_dog.dto.admin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AdminSessionDTO {
    private Long adminNo;
    private String adminId;
}
