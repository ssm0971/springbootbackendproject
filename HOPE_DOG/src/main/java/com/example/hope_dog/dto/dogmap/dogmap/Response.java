package com.example.hope_dog.dto.dogmap.dogmap;

import lombok.Data;

@Data
public class Response {
    private Header header; // 응답 헤더
    private Body body;     // 응답 바디
}
