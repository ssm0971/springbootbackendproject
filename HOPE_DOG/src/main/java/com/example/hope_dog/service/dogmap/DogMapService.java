package com.example.hope_dog.service.dogmap;

import com.example.hope_dog.dto.dogmap.dogmap.AnimalShelterResponse;
import com.example.hope_dog.dto.dogmap.dogmap.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DogMapService {

    @Value("${api.service.key1}")
    private String serviceKey1;
    private String serviceKey2;

    public List<Item> getShelterInfo() {
        String baseurl = "https://apis.data.go.kr/1543061/animalShelterSrvc/shelterInfo";
        String subType = "json";
        String numOfRows = "250";
        String pageNo = "1";
//        String serviceKey = "YHW1P%2F57dgSSQi1EHu1NvPwRjRhbDQSsVMiA%2BwstM1aQkH0mt0JIwg7%2FdPAZgu1UE8x6oHCtoM9Z%2BEicawOZDw%3D%3D&serviceKey=YHW1P/57dgSSQi1EHu1NvPwRjRhbDQSsVMiA+wstM1aQkH0mt0JIwg7/dPAZgu1UE8x6oHCtoM9Z+EicawOZDw==";

        // URL 생성
        String url = baseurl + "?serviceKey=" + serviceKey1 + "&numOfRows=" + numOfRows + "&pageNo=" + pageNo + "&_type=" + subType;
        log.info("URL 확인 : " + url);

        RestTemplate restTemplate = new RestTemplate();
        AnimalShelterResponse response = null;

        try {
            // HTTP 요청 헤더 설정
            HttpHeaders headers = new HttpHeaders();
            log.info("요청헤더 확인==============" + headers.toString());
            HttpEntity<String> entity = new HttpEntity<>(headers);
            log.info("요청헤더 Entity확인==============" + entity.toString());
            ResponseEntity<AnimalShelterResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, AnimalShelterResponse.class);

            // 응답 상태 코드 확인
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                response = responseEntity.getBody();
                log.info("API Response: " + response); // 응답 내용 출력
            } else {
                log.error("API 호출 실패: " + responseEntity.getStatusCode());
                return new ArrayList<>(); // 실패 시 빈 리스트 반환
            }
        } catch (RestClientException e) {
            log.error("API 호출 중 오류 발생: " + e.getMessage());
            return new ArrayList<>(); // 오류 발생 시 빈 리스트 반환
        }

        List<Item> items = new ArrayList<>();

        // JSON 응답에서 items 항목을 가져옴
        if (response != null && response.getResponse() != null && response.getResponse().getBody() != null) {
            // items 리스트 가져오기
            items = response.getResponse().getBody().getItems().getItem();
        } else {
            log.error("응답이 유효하지 않거나 JSON 형식이 아닙니다: " + response);
            return new ArrayList<>(); // 유효하지 않은 응답 시 빈 리스트 반환
        }

        return items;
    }

    // 주소 필터링하는 메소드
    public List<Item> filterByAddress(List<Item> dogmapDTOList, String addressPrefix) {
        List<Item> filteredItems = dogmapDTOList.stream()
                .filter(dto -> dto.getCareAddr().startsWith(addressPrefix))
                .collect(Collectors.toList());

        // 인덱스를 추가하면서 리스트에 저장
        for (int i = 0; i < filteredItems.size(); i++) {
            Item item = filteredItems.get(i);
            item.setIndex(i + 1); // 인덱스를 설정 (1부터 시작)
        }

        return filteredItems;
    }

//    @Value("${api.service.key}")
//    private String serviceKey;
//
//
//
//    public List<Item> getShelterInfo() {
//        String baseurl = "https://apis.data.go.kr/1543061/animalShelterSrvc/shelterInfo";
//        String subType = "json";
//        String numOfRows = "200";
//        String pageNo = "1";
//
//        // URL 생성
//        String url = baseurl + "?serviceKey=" + serviceKey + "&numOfRows=" + numOfRows + "&pageNo=" + pageNo + "&_type=" + subType;
//        log.info("URL 확인 : " + url);
//
//        RestTemplate restTemplate = new RestTemplate();
//        AnimalShelterResponse response = null;
//
//        try {
//            // HTTP 요청 헤더 설정
//            HttpHeaders headers = new HttpHeaders();
//
//            HttpEntity<String> entity = new HttpEntity<>(headers);
//            ResponseEntity<AnimalShelterResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, AnimalShelterResponse.class);
//
//            // 응답 상태 코드 확인
//            if (responseEntity.getStatusCode().is2xxSuccessful()) {
//                response = responseEntity.getBody();
//                log.info("API Response: " + response); // 응답 내용 출력
//            } else {
//                log.error("API 호출 실패: " + responseEntity.getStatusCode());
//                return new ArrayList<>(); // 실패 시 빈 리스트 반환
//            }
//        } catch (RestClientException e) {
//            log.error("API 호출 중 오류 발생: " + e.getMessage());
//            return new ArrayList<>(); // 오류 발생 시 빈 리스트 반환
//        }
//
//        List<Item> items = new ArrayList<>();
//
//        // JSON 응답에서 items 항목을 가져옴
//        if (response != null && response.getResponse() != null && response.getResponse().getBody() != null) {
//            // items 리스트 가져오기
//            List<Item> apiItems = response.getResponse().getBody().getItems().getItem();
//
//            // 인덱스를 추가하면서 리스트에 저장
//            for (int i = 0; i < apiItems.size(); i++) {
//                Item item = apiItems.get(i);
//                item.setIndex(i + 1); // 인덱스 설정 (1부터 시작)
//                items.add(item); // 인덱스를 설정한 아이템을 새로운 리스트에 추가
//            }
//        } else {
//            log.error("응답이 유효하지 않거나 JSON 형식이 아닙니다: " + response);
//            return new ArrayList<>(); // 유효하지 않은 응답 시 빈 리스트 반환
//        }
//
//        return items;
//    }
//
//    // 주소 필터링하는 메소드
//    public List<Item> filterByAddress(List<Item> dogmapDTOList, String addressPrefix) {
//        return dogmapDTOList.stream()
//                .filter(dto -> dto.getCareAddr().startsWith(addressPrefix))
//                .collect(Collectors.toList());
//    }

//    private final String SERVICE_KEY = "YHW1P%2F57dgSSQi1EHu1NvPwRjRhbDQSsVMiA%2BwstM1aQkH0mt0JIwg7%2FdPAZgu1UE8x6oHCtoM9Z%2BEicawOZDw%3D%3D"; // 실제 서비스 키로 변경

//    서비스키
//    YHW1P%2F57dgSSQi1EHu1NvPwRjRhbDQSsVMiA%2BwstM1aQkH0mt0JIwg7%2FdPAZgu1UE8x6oHCtoM9Z%2BEicawOZDw%3D%3D

//    @Value("${api.service.key}")
//    private String serviceKey;
//
//    public List<DogMapApiDTO> getShelterInfo() {
//        String baseurl = "http://apis.data.go.kr/1543061/animalShelterSrvc/shelterInfo";
//        String subType = "_type=" + "json";
//        String numOfRows = "numOfRows=" + "250";
//        String pageNo = "pageNo=" + "1";
//        String KeyStr = "serviceKey=";
//
//        // URL 생성
//        String url = (baseurl + "?" + KeyStr + serviceKey + "&" + numOfRows + "&" + pageNo + "&" + subType );
//        log.info("URL 확인 : " + url);
//
//        RestTemplate restTemplate = new RestTemplate();
//        String response;
//
//        try {
//            // API 호출
//            response = restTemplate.getForObject(url, String.class);
//            log.info("API Response: " + response); // 응답 내용 출력
//        } catch (RestClientException e) {
//            log.error("API 호출 중 오류 발생: " + e.getMessage());
//            return new ArrayList<>(); // 오류 발생 시 빈 리스트 반환
//        }
//
//        List<DogMapApiDTO> dogmapDTOList = new ArrayList<>();
//
//        // JSON 응답이 예상한 형식인지 확인
//        if (response.startsWith("{")) {
//            try {
//                // JSON 응답 파싱
//                JSONObject jsonResponse = new JSONObject(response);
//                JSONArray shelterArray = jsonResponse.getJSONObject("response")
//                        .getJSONObject("body")
//                        .getJSONObject("items")
//                        .getJSONArray("item"); // "items" 아래의 "item" 배열을 가져옴
//
//                for (int i = 0; i < shelterArray.length(); i++) {
//                    JSONObject shelterJson = shelterArray.getJSONObject(i);
//                    DogMapApiDTO dogMapApiDTOInfo = new DogMapApiDTO(); // ShelterInfo 객체 생성
//
//                    // JSON 필드에 맞춰 DTO 필드에 값 설정
//                    dogMapApiDTOInfo.setCareNm(shelterJson.getString("careNm"));
//                    dogMapApiDTOInfo.setOrgNm(shelterJson.getString("orgNm"));
//                    dogMapApiDTOInfo.setDivisionNm(shelterJson.getString("divisionNm"));
//                    dogMapApiDTOInfo.setSaveTrgtAnimal(shelterJson.getString("saveTrgtAnimal"));
//                    dogMapApiDTOInfo.setCareAddr(shelterJson.getString("careAddr"));
//                    dogMapApiDTOInfo.setJibunAddr(shelterJson.getString("jibunAddr"));
//                    dogMapApiDTOInfo.setLat(shelterJson.getDouble("lat"));
//                    dogMapApiDTOInfo.setLng(shelterJson.getDouble("lng"));
//                    dogMapApiDTOInfo.setDsignationDate(shelterJson.getString("dsignationDate"));
//                    dogMapApiDTOInfo.setWeekOprStime(shelterJson.getString("weekOprStime"));
//                    dogMapApiDTOInfo.setWeekOprEtime(shelterJson.getString("weekOprEtime"));
//                    dogMapApiDTOInfo.setCloseDay(shelterJson.getString("closeDay"));
//                    dogMapApiDTOInfo.setVetPersonCnt(shelterJson.getInt("vetPersonCnt"));
//                    dogMapApiDTOInfo.setSpecsPersonCnt(shelterJson.getInt("specsPersonCnt"));
//                    dogMapApiDTOInfo.setCareTel(shelterJson.getString("careTel"));
//                    dogMapApiDTOInfo.setDataStdDt(shelterJson.getString("dataStdDt"));
//
//                    dogmapDTOList.add(dogMapApiDTOInfo); // 리스트에 추가
//                }
//            } catch (Exception e) {
//                log.error("JSON 파싱 중 오류 발생: " + e.getMessage());
//                return new ArrayList<>(); // 오류 발생 시 빈 리스트 반환
//            }
//        } else {
//            log.error("응답이 JSON 형식이 아닙니다: " + response);
//        }
//
//        return dogmapDTOList;
//    }
//
//    // 주소 필터링하는 메소드
//    public List<DogMapApiDTO> filterByAddress(List<DogMapApiDTO> dogmapDTOList, String addressPrefix) {
//        return dogmapDTOList.stream()
//                .filter(dto -> dto.getCareAddr().startsWith(addressPrefix))
//                .collect(Collectors.toList());
//    }




//    private final RestTemplate restTemplate;
//
//    // 생성자 주입을 통해 RestTemplate을 주입받음
//    public DogMapService(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//
//    public DogMapApiDTO getDogMapList(String url) {
//        // RestTemplate을 사용하여 API 호출
//        return restTemplate.getForObject(url, DogMapApiDTO.class);
//    }
}

