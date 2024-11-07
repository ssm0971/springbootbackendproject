package com.example.hope_dog.service.dogmap;

import com.example.hope_dog.dto.dogmap.dogmap.DogMapApiDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DogMapService {

    private final RestTemplate restTemplate;

    // 생성자 주입을 통해 RestTemplate을 주입받음
    public DogMapService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public DogMapApiDTO getDogMapList(String url) {
        // RestTemplate을 사용하여 API 호출
        return restTemplate.getForObject(url, DogMapApiDTO.class);
    }
}

