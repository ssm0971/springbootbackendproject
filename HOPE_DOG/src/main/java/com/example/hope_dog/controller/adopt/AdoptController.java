package com.example.hope_dog.controller.adopt;

import com.example.hope_dog.dto.adopt.adopt.AdoptDetailDTO;
import com.example.hope_dog.dto.adopt.adopt.AdoptRequestDTO;
import com.example.hope_dog.dto.adopt.adopt.MainDTO;
import com.example.hope_dog.service.adopt.adopt.AdoptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/adopt")    //시작도메인 localhost:8060/adopt
public class AdoptController {
    private final AdoptService adoptService;

    //입양/임보/후기 메인페이지
    @GetMapping("/main")        //열릴페도메인 localhost:8060/adopt/main
    public String Main(Model model) {
        List<MainDTO> MainList = adoptService.getMainList();
        model.addAttribute("MainList", MainList);
        return "adopt/adopt-main";  //localhost:8060/adopt/main로 접속했을시 열릴 html
    }

    //입양메인
    @GetMapping("/adopt")
    public String adoptMain() {
        return "adopt/adopt/adopt-adopt";
    }

    //입양상세글
    @GetMapping("/adopt/adoptdetail")
    public String adoptDetail(@RequestParam("adoptNo") Long adoptNo, Model model) {
        List<AdoptDetailDTO> adoptDetailList = adoptService.getAdoptDetail(adoptNo);
        model.addAttribute("adoptDetailList", adoptDetailList);
        return "adopt/adopt/adopt-adoptdetail";
    }

    //입양글작성
    @GetMapping("/adopt/adoptwrite")
    public String adoptWrite() {
        return "adopt/adopt/adopt-adoptwrite";
    }

    //입양글수정
    @GetMapping("/adopt/adoptmodify")
    public String adoptModify() {
        return "adopt/adopt/adopt-adoptmodify";
    }

    //입양신청서
    @GetMapping("/adopt/adoptrequest")
    public String adoptRequest(@RequestParam("adoptNo") Long adoptNo,Model model) {
        List<AdoptRequestDTO> adoptRequestList = adoptService.getAdoptRequest(adoptNo);
        model.addAttribute("adoptRequestList", adoptRequestList);
        return "adopt/adopt/adopt-adoptrequest";
    }

    //임시보호 메인
    @GetMapping("/protect")
    public String protectMain() {
        return "adopt/protect/adopt-protect";
    }

    //임시보호상세글
    @GetMapping("/protect/protectdetail")
    public String protectDetail() {
        return "adopt/protect/adopt-protectdetail";
    }

    //임시보호글작성
    @GetMapping("/protect/protectwrite")
    public String protectWrite() {
        return "adopt/protect/adopt-protectwrite";
    }

    //임시보호글수정
    @GetMapping("/protect/protectmodify")
    public String protectModify() {
        return "adopt/protect/adopt-protectmodify";
    }

    //후기메인
    @GetMapping("/review")
    public String reviewMain() {
        return "adopt/review/adopt-review";
    }

    //후기상세글
    @GetMapping("/review/reviewdetail")
    public String reviewDetail() {
        return "adopt/review/adopt-reviewdetail";
    }

    //후기글작성
    @GetMapping("/review/reviewwrite")
    public String reviewWrite() {
        return "adopt/review/adopt-reviewwrite";
    }

    //후기글수정
    @GetMapping("/review/reviewmodify")
    public String reviewModify() {
        return "adopt/review/adopt-reviewmodify";
    }
}
