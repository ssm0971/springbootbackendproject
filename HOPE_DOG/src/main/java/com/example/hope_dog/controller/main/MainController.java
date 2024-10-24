package com.example.hope_dog.controller.main;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/main")
public class MainController {

    /**
     * 메인 페이지
     */
    @GetMapping("/main")
    public String main() {
        log.info("메인페이지 요청");
        return "main/main";
    }
}
