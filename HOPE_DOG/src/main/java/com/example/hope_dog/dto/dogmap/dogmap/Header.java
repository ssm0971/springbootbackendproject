package com.example.hope_dog.dto.dogmap.dogmap;

import lombok.Data;

@Data
public class Header {
    private Long reqNo;        // 요청 번호
    private String resultCode; // 결과 코드
    private String resultMsg;  // 결과 메시지
}