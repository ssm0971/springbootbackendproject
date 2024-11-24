package com.example.hope_dog.controller.dogmap.map;

import com.example.hope_dog.dto.dogmap.dogmap.Item;
import com.example.hope_dog.service.dogmap.DogMapService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/dogmap")
public class DogMapController {

    private final DogMapService dogMapService;

    public DogMapController(DogMapService dogMapService) {
        this.dogMapService = dogMapService;
    }


    @GetMapping("/shelters")
    public String getShelters(Model model) {

        List<Item> shelters = dogMapService.getShelterInfo(); // ShelterInfo 리스트로 변경

        List<Item> filteredByAddress = dogMapService.filterByAddress(shelters, "경기도");


        model.addAttribute("shelters", filteredByAddress); // 모델에 shelters 추가
        return "dogmap/dogmap"; // Thymeleaf 템플릿 이름
    }

    @GetMapping("/sheltersStatic")
    public String getSheltersStatic(Model model) {
        Item[] staticShelters = dogMapService.getStaticShelterInfo(); // 정적 데이터
        model.addAttribute("shelters", staticShelters); // 모델에 정적 shelters 추가
        return "dogmap/dogmap-static"; // Thymeleaf 템플릿 이름
    }

}

