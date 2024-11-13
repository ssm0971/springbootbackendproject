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

    // 제한 데이터 페이지 메소드
    public Item[] getStaticShelterInfo() {
        Item[] staticShelters = new Item[10];

        staticShelters[0] = createItem(1, "24시아이동물메디컬", "경기도 부천시 오정구 소사로 779 (원종동) 201호", "032-677-5262", 37.52566, 126.804565);
        staticShelters[1] = createItem(2, "가나동물병원", "경기도 부천시 소사구 경인로 72 (송내동)", "032-665-0075", 37.48555, 126.76318);
        staticShelters[2] = createItem(3, "가평군유기동물보호소", "경기도 가평군 가평읍 아랫마장길 59 (가평읍, 농업기술센터)", "031-580-4794", 37.845997, 127.4987);
        staticShelters[3] = createItem(4, "고양시동물보호센터", "경기도 고양시 덕양구 고양대로 1695 (원흥동, 고양시 농업기술센터)", "031-962-3232", 37.64964, 126.87027);
        staticShelters[4] = createItem(5, "광주TNR동물병원송정", "경기도 광주시 경안천로 142 (송정동)", "031-798-7583", 37.41734, 127.275024);
        staticShelters[5] = createItem(6, "광주TNR동물병원초월", "경기도 광주시 초월읍 현산로385번길 74-12 (초월읍)", "031-798-7581", 37.41589, 127.27616);
        staticShelters[6] = createItem(7, "구리반려동물문화센터", "경기도 구리시 동구릉로136번길 57 (인창동) 2층", "031-566-0059", 37.61347, 127.14054);
        staticShelters[7] = createItem(8, "남양동물보호센터", "경기도 화성시 남양읍 화성로 1483-27 (남양읍)", "031-356-2281", 37.22495, 126.84342);
        staticShelters[8] = createItem(9, "남양주시동물보호센터", "경기도 남양주시 경강로163번길 32-27 (이패동)", "031-590-2785", 37.60878, 127.191536);
        staticShelters[9] = createItem(10, "부천시수의사회", "경기도 부천시 원미구 중동로 100 (중동) 아이파크 상가동 213호", "032-661-7575", 37.488747, 126.76579);

        return staticShelters;
    }

    private Item createItem(int index, String careNm, String careAddr, String careTel, double lat, double lng) {
        Item item = new Item();
        item.setIndex(index);
        item.setCareNm(careNm);
        item.setCareAddr(careAddr);
        item.setCareTel(careTel);
        item.setLat(lat);
        item.setLng(lng);
        return item;
    }

}

