package com.example.hope_dog.controller;

import com.example.hope_dog.dto.ShelterInfo;
import com.example.hope_dog.service.ShelterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/test")
public class ShelterController {
    private final ShelterService shelterService;

    public ShelterController(ShelterService shelterService) {
        this.shelterService = shelterService;
    }

    @GetMapping("/shelters")
    public String getShelters(Model model) {
        List<ShelterInfo> shelters = shelterService.getShelterInfo(); // ShelterInfo 리스트로 변경
        model.addAttribute("shelters", shelters); // 모델에 shelters 추가
        return "shelters"; // Thymeleaf 템플릿 이름
    }
}
