package com.example.hope_dog.dto.dogmap.dogmap;

import lombok.Data;

@Data
public class Item {
    private String careNm;           // 보호소 이름
    private String orgNm;            // 기관 이름
    private String divisionNm;       // 구분
    private String saveTrgtAnimal;   // 보호 대상 동물
    private String careAddr;         // 주소
    private String jibunAddr;        // 지번 주소
    private Double lat;              // 위도
    private Double lng;              // 경도
    private String dsignationDate;   // 지정 날짜
    private String weekOprStime;     // 주간 운영 시작 시간
    private String weekOprEtime;     // 주간 운영 종료 시간
    private String closeDay;         // 휴무일
    private Integer vetPersonCnt;    // 수의사 수
    private Integer specsPersonCnt;   // 전문 인력 수
    private String careTel;          // 연락처
    private String dataStdDt;        // 데이터 기준일

    // 추가적인 필드 (필요시)
    private Integer medicalCnt;      // 의료 수
    private Integer breedCnt;        // 품종 수
    private Integer quarabtineCnt;   // 격리 수
    private Integer feedCnt;         // 먹이 수
    private Integer transCarCnt;     // 이동차량 수
    private String weekCellStime;    // 주간 셀 시작 시간
    private String weekCellEtime;    // 주간 셀 종료 시간
    private String weekendOprStime;  // 주말 운영 시작 시간
    private String weekendOprEtime;  // 주말 운영 종료 시간
    private String weekendCellStime;  // 주말 셀 시작 시간
    private String weekendCellEtime;  // 주말 셀 종료 시간
}
