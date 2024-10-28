package com.example.hope_dog.controller.volun.car;

import com.example.hope_dog.dto.volun.car.CarDTO;
import com.example.hope_dog.service.volun.car.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/car")// 시작 도메인 localhost:8060/car
//생성자 주입
public class CarController {
    private final CarService carService;

    //카풀 게시판 목록
    @GetMapping("/main")
    public String carList(Model model) {
        List<CarDTO> carList = carService.getCarList(); // 데이터 조회
        model.addAttribute("carList", carList); // 모델에 추가
        return "volun/car/volun-car-main";
    }


    //카테고리 분류에 따른 게시글 조회
    @GetMapping("/filter")
    public String filterCarList(@RequestParam("cate") String cate, Model model) {
        List<CarDTO> carList = carService.getCarListByCate(cate);
        model.addAttribute("carList", carList);
        return "volun/car/volun-car-main";
    }

    //게시글 상세페이지
    @GetMapping("/post/{no}")
    public String postCar(@PathVariable("no") Long no, Model model) {
        CarDTO carDetail = carService.getCarPost(no);
        model.addAttribute("car", carDetail);
        return "volun/car/volun-car-post";
    }

}