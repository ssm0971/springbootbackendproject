package com.example.hope_dog.dto.dogmap.dogmap;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class HeadDTO {
    private List<resultDTO> result;

    @JsonProperty("list_total_count")
    private int listTotalCount; // 전체 목록 수

    @JsonProperty("api_version")
    private String apiVersion; // API 버전

}
