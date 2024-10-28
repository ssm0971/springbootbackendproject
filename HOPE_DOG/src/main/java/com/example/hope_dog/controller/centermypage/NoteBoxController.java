package com.example.hope_dog.controller.centermypage;

import com.example.hope_dog.dto.centermypage.notebox.NoteboxReceiveListDTO;
import com.example.hope_dog.dto.centermypage.notebox.NoteboxSendListDTO;
import com.example.hope_dog.service.centermypage.NoteBoxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
//@RequestMapping("/Notebox")
public class NoteBoxController {
//    private final NoteBoxService NoteBoxService;
//
//    @GetMapping("/SendList")
//    public String sendList(Model model) {
//        Long centerMemberNo = 12L; // 임시로 사용할 값, 추후 세션에서 가져올 것
//        List<NoteboxSendListDTO> noteboxSendList = NoteBoxService.getSendList(centerMemberNo);
//
//        // 모델에 값 추가
//        model.addAttribute("noteboxSendList", noteboxSendList);
//
//        // 로그 추가 (요청이 들어왔을 때)
//        log.info("SendList 요청이 들어왔습니다. centerMemberNo: {}", centerMemberNo);
//
//        return "centermypage/notebox/center-mypage-notebox-send"; // HTML 파일 경로
//    }
//
//    @GetMapping("/ReceiveList")
//    public String receiveList(Model model) {
//        Long centerMemberNo = 12L; // 임시로 사용할 값, 추후 세션에서 가져올 것
//        List<NoteboxReceiveListDTO> noteboxReceiveList = NoteBoxService.getReceiveList(centerMemberNo);
//
//        // 모델에 값 추가
//        model.addAttribute("noteboxReceiveList", noteboxReceiveList);
//
//        // 로그 추가 (요청이 들어왔을 때)
//        log.info("SendList 요청이 들어왔습니다. centerMemberNo: {}", centerMemberNo);
//
//        return "centermypage/notebox/center-mypage-notebox-receive"; // HTML 파일 경로
//    }
//
//    @GetMapping("/Write")
//    public String Write() {
//
//        return "centermypage/notebox/center-mypage-notebox-write"; // 뷰로 이동
//    }
}
