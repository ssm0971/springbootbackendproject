package com.example.hope_dog.controller.volun.car;

import com.example.hope_dog.dto.page.Criteria;
import com.example.hope_dog.dto.page.Page;
import com.example.hope_dog.dto.volun.car.CarDTO;
import com.example.hope_dog.dto.volun.car.CarDetailDTO;
import com.example.hope_dog.service.volun.car.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/car")// 시작 도메인 localhost:8060/car
//생성자 주입
public class CarController {
    private final CarService carService;


    @GetMapping("/main")
    public String carList(@RequestParam(value = "page", defaultValue = "1") int page,
                          @RequestParam(value = "amount", defaultValue = "9") int amount,
                          @RequestParam(value = "cate", required = false) String cate,
                          Model model) {
        Criteria criteria = new Criteria(page, amount);
        criteria.setCate(cate); // 카테고리 설정

        // 페이지 정보에 맞는 카풀 게시글 조회
        List<CarDTO> carList = carService.findCarPage(criteria);
        int total = carService.findCarTotal(criteria); // 총 게시글 수 조회
        Page pageInfo = new Page(criteria, total); // 페이지 정보 생성

        // 조회한 carList와 페이지 정보를 모델에 추가
        model.addAttribute("carList", carList);
        model.addAttribute("page", pageInfo);

        // 페이지네이션이 적용된 카풀 게시판 메인 뷰로 이동
        return "volun/car/volun-car-main";
    }


    // 카테고리 분류에 따른 게시글 조회
    @GetMapping("/filter")
    public String filterCarList(@RequestParam("cate") String cate,
                                @RequestParam(value = "page", defaultValue = "1") int page,
                                @RequestParam(value = "amount", defaultValue = "9") int amount,
                                Model model) {
        Criteria criteria = new Criteria(page, amount);
        criteria.setCate(cate); // 카테고리 설정

        List<CarDTO> carList = carService.getCarListByCate(cate, criteria);
        int total = carService.findCarTotal(criteria); // 총 게시글 수 조회
        Page pageInfo = new Page(criteria, total);

        model.addAttribute("carList", carList);
        model.addAttribute("page", pageInfo);
        return "volun/car/volun-car-main";
    }

    // 검색
    @GetMapping("/main/search")
    public String searchCars(@RequestParam("searchType") String searchType,
                             @RequestParam("keyword") String keyword,
                             Model model) {
        System.out.println("컨트롤러 검색 타입 : " + searchType);
        System.out.println("컨트롤러 키워드 : " + keyword);

        // 검색 조건에 따른 차량 리스트 조회
        List<CarDTO> carList = carService.searchCars(searchType, keyword);
        System.out.println("컨트롤러 List : " + carList);

        model.addAttribute("carList", carList); // 차량 리스트 추가
        model.addAttribute("searchType", searchType); // 검색 타입 추가
        model.addAttribute("keyword", keyword); // 검색어 추가

        return "volun/car/volun-car-main"; // 결과 페이지로 이동
    }

    //게시글 상세
    @GetMapping("/post/{carNo}")
    public String carDetail(@PathVariable("carNo") Long carNo, Model model) {
        CarDetailDTO carDetail = carService.getCarDetail(carNo);
        model.addAttribute("carDetail", carDetail);
        return "volun/car/volun-car-post";
    }


}