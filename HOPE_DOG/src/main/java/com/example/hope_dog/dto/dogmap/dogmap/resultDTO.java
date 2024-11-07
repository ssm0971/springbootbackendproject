package com.example.hope_dog.dto.dogmap.dogmap;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class resultDTO {
    @JsonProperty("CODE")
    private String code; // 결과 코드

    @JsonProperty("MESSAGE")
    private String message; // 결과 메시지

}
