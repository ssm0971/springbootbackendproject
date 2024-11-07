package com.example.hope_dog.controller.dogmap.map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class DogMapController {

    @GetMapping("/dogmap")
    public String getDogMap(Model model) {

        return "dogmap/dogmap"; // dogmap.html 페이지로 반환
    }

}

//    private final DogMapRestController dogMapRestController;
//
//    public DogMapController(DogMapRestController dogMapRestController) {
//        this.dogMapRestController = dogMapRestController;
//    }
//
//    @GetMapping("/dogmap")
//    public String getDogMap(Model model) {
//        // dogmap.html 페이지를 반환하기 전에 DTO를 가져와서 모델에 추가
//        DogMapDTO dogMapDTO = dogMapRestController.getDogMapList();
//        model.addAttribute("dogMapDTO", dogMapDTO);
//        return "dogmap/dogmap"; // dogmap.html 페이지를 반환
//    }
//}
//    @GetMapping("/doghouses")
//    public String showDoghouses(Model model) {
//        // 보호소 목록 데이터 생성 (예시)
//        List<Doghouse> doghouses = new ArrayList<>();
//        doghouses.add(new Doghouse("아이조아요양보호소 동탄점", "경기 화성시 중리북길 39", "0507-1375-9331"));
//        doghouses.add(new Doghouse("안다 동물보호소", "경기 평택시 오좌동길 47-48", "0507-1333-0783"));
//        // 추가 보호소 데이터...
//
//        // 모델에 데이터 추가
//        model.addAttribute("doghouses", doghouses);
//        return "dogmap/dogmap"; // doghouses.html로 리턴
//    }
//
//    // Doghouse 클래스 정의
//    public static class Doghouse {
//        private String name;
//        private String address;
//        private String phone;
//
//        public Doghouse(String name, String address, String phone) {
//            this.name = name;
//            this.address = address;
//            this.phone = phone;
//        }
//
//        public String getName() { return name; }
//        public String getAddress() { return address; }
//        public String getPhone() { return phone; }
//    }
//}
