package com.example.hope_dog.service;

import com.example.hope_dog.dto.ShelterInfo;
import com.example.hope_dog.dto.ShelterResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ShelterService {

//    private final String SERVICE_KEY = "YHW1P%2F57dgSSQi1EHu1NvPwRjRhbDQSsVMiA%2BwstM1aQkH0mt0JIwg7%2FdPAZgu1UE8x6oHCtoM9Z%2BEicawOZDw%3D%3D"; // 실제 서비스 키로 변경
// postman 에서 서비스키 YHW1P%2F57dgSSQi1EHu1NvPwRjRhbDQSsVMiA%2BwstM1aQkH0mt0JIwg7%2FdPAZgu1UE8x6oHCtoM9Z%2BEicawOZDw%3D%3D
    // application.properties에서 서비스 키를 읽어옵니다.


    @Value("${api.service.key}")
    private String serviceKey;

    public List<ShelterInfo> getShelterInfo() {
        String baseurl = "http://apis.data.go.kr/1543061/animalShelterSrvc/shelterInfo";
        String subType = "&numOfRows=3&pageNo=1&_type=json";
        String Type = "&_type=json";

        // 이미 인코딩된 serviceKey를 그대로 사용
        String url = baseurl + "?serviceKey=" + serviceKey + Type;
        log.info("URL 확인 : " + url);

        RestTemplate restTemplate = new RestTemplate();
        String response;


        try {
            // API 호출

            response = restTemplate.getForObject(url, String.class);
            log.info(url);
            log.info("API Response: " + response); // 응답 내용 출력
        } catch (RestClientException e) {
            log.error("API 호출 중 오류 발생: " + e.getMessage());
            return new ArrayList<>(); // 오류 발생 시 빈 리스트 반환
        }
        List<ShelterResponse> shelterResponses = new ArrayList<>();
        List<ShelterInfo> shelters = new ArrayList<>();

        // JSON 응답이 예상한 형식인지 확인
        if (response.startsWith("{")) {
            try {
                // JSON 응답 파싱
                JSONObject jsonResponse = new JSONObject(response);
                JSONArray shelterArray = jsonResponse.getJSONObject("response")
                        .getJSONObject("body")
                        .getJSONObject("items")
                        .getJSONArray("item");

                for (int i = 0; i < shelterArray.length(); i++) {
                    JSONObject shelterJson = shelterArray.getJSONObject(i);
                    ShelterInfo shelterInfo = new ShelterInfo(); // ShelterInfo 객체 생성

                    // JSON 필드에 맞춰 DTO 필드에 값 설정
                    shelterInfo.setCareNm(shelterJson.getString("careNm"));
                    shelterInfo.setOrgNm(shelterJson.getString("orgNm"));
                    shelterInfo.setDivisionNm(shelterJson.getString("divisionNm"));
                    shelterInfo.setSaveTrgtAnimal(shelterJson.getString("saveTrgtAnimal"));
                    shelterInfo.setCareAddr(shelterJson.getString("careAddr"));
                    shelterInfo.setJibunAddr(shelterJson.getString("jibunAddr"));
                    shelterInfo.setLat(shelterJson.getDouble("lat"));
                    shelterInfo.setLng(shelterJson.getDouble("lng"));
                    shelterInfo.setDsignationDate(shelterJson.getString("dsignationDate"));
                    shelterInfo.setWeekOprStime(shelterJson.getString("weekOprStime"));
                    shelterInfo.setWeekOprEtime(shelterJson.getString("weekOprEtime"));
                    shelterInfo.setCloseDay(shelterJson.getString("closeDay"));
                    shelterInfo.setVetPersonCnt(shelterJson.getInt("vetPersonCnt"));
                    shelterInfo.setSpecsPersonCnt(shelterJson.getInt("specsPersonCnt"));
                    shelterInfo.setCareTel(shelterJson.getString("careTel"));
                    shelterInfo.setDataStdDt(shelterJson.getString("dataStdDt"));

                    shelters.add(shelterInfo); // 리스트에 추가
                }
            } catch (Exception e) {
                log.error("JSON 파싱 중 오류 발생: " + e.getMessage());
                return new ArrayList<>(); // 오류 발생 시 빈 리스트 반환
            }
        } else {
            log.error("응답이 JSON 형식이 아닙니다: " + response);
        }

        return shelters;
    }

}


//@Value("${api.service.key}")
//    private String serviceKey;
//
//    private final RestTemplate restTemplate;
//
//    public ShelterService(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//
//    public List<ShelterInfo> getShelterInfo() {
//        String baseurl = "http://apis.data.go.kr/1543061/animalShelterSrvc/shelterInfo";
//        String url = baseurl + "?serviceKey=" + serviceKey + "&numOfRows=3&pageNo=1&_type=json";
//        log.info("URL 확인 : " + url);
//
//        ShelterResponse response = new RestTemplate().getForObject(url, ShelterResponse.class);
//
//        try {
//            // API 호출
//            response = restTemplate.getForObject(url, ShelterResponse.class);
//            log.info("API Response: " + response); // 응답 내용 출력
//        } catch (RestClientException e) {
//            log.error("API 호출 중 오류 발생: " + e.getMessage());
//            log.info("API Response: " + response); // 응답 내용 출력
//            return new ArrayList<>(); // 오류 발생 시 빈 리스트 반환
//
//        }
//
//        List<ShelterInfo> shelters = new ArrayList<>();
//
//        // JSON 응답이 예상한 형식인지 확인
//        if (response != null && response.getResponse() != null && response.getResponse().getBody() != null) {
//            List<ShelterInfo> shelterList = response.getResponse().getBody().getItems().getItem();
//            shelters.addAll(shelterList); // 리스트에 추가
//        } else {
//            log.error("응답이 예상한 형식이 아닙니다.");
//        }
//        log.info("왜 안돼? : " + url);
//        return shelters;
//    }