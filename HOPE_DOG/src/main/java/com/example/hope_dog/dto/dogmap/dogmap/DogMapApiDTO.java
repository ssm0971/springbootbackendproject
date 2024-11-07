package com.example.hope_dog.dto.dogmap.dogmap;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class DogMapApiDTO {

    private List<Head> head; // 헤드 정보
    private List<Row> row; // 실제 데이터 목록

    @Data
    public static class Head {
        @JsonProperty("list_total_count")
        private int listTotalCount; // 전체 목록 수

        @JsonProperty("RESULT")
        private Result result; // 결과 정보

        @JsonProperty("api_version")
        private String apiVersion; // API 버전
    }

    @Data
    public static class Result {
        @JsonProperty("CODE")
        private String code; // 결과 코드

        @JsonProperty("MESSAGE")
        private String message; // 결과 메시지
    }

    @Data
    public static class Row {
        @JsonProperty("SUM_YY")
        private String sumYy; // 연도

        @JsonProperty("SIGUN_NM")
        private String sigunNm; // 시군명

        @JsonProperty("SIGUN_CD")
        private String sigunCd; // 시군 코드

        @JsonProperty("ENTRPS_NM")
        private String entrpsNm; // 센터명

        @JsonProperty("REPRSNTV_NM")
        private String reprsntvNm; // 대표자명

        @JsonProperty("ACEPTNC_ABLTY_CNT")
        private int acceptncAbiltyCnt; // 수용 능력

        @JsonProperty("ENTRPS_TELNO")
        private String entrpsTelno; // 전화번호

        @JsonProperty("CONTRACT_PERD")
        private String contractPerd; // 계약 기간

        @JsonProperty("REFINE_LOTNO_ADDR")
        private String refineLotnoAddr; // 주소 (지번)

        @JsonProperty("REFINE_ROADNM_ADDR")
        private String refineRoadnmAddr; // 주소 (도로명)

        @JsonProperty("REFINE_ZIP_CD")
        private String refineZipCd; // 우편번호

        @JsonProperty("REFINE_WGS84_LOGT")
        private Double refineWgs84Logt; // 경도

        @JsonProperty("REFINE_WGS84_LAT")
        private Double refineWgs84Lat; // 위도
    }
}
