package com.example.hope_dog.dto;

import lombok.Data;

@Data
public class ShelterInfo {
    private String careNm;          // 보호 센터명
    private String orgNm;           // 기관명
    private String divisionNm;      // 구분명
    private String saveTrgtAnimal;  // 보호 대상 동물
    private String careAddr;        // 소재지 도로명 주소
    private String jibunAddr;       // 지번 주소
    private double lat;             // 위도
    private double lng;             // 경도
    private String dsignationDate;  // 지정일
    private String weekOprStime;    // 주 운영 시작 시간
    private String weekOprEtime;    // 주 운영 종료 시간
    private String closeDay;        // 휴무일
    private int vetPersonCnt;       // 수의사 인원 수
    private int specsPersonCnt;     // 전문 인력 수
    private String careTel;         // 전화번호
    private String dataStdDt;       // 데이터 기준일
}
