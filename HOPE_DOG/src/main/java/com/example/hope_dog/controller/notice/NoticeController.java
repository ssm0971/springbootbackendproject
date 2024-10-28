package com.example.hope_dog.controller.notice;

import com.example.hope_dog.dto.adopt.adopt.AdoptDetailDTO;
import com.example.hope_dog.dto.adopt.adopt.AdoptMainDTO;
import com.example.hope_dog.dto.notice.NoticeListDTO;
import com.example.hope_dog.dto.notice.NoticeViewDTO;
import com.example.hope_dog.dto.notice.page.Criteria;
import com.example.hope_dog.dto.notice.page.Page;
import com.example.hope_dog.service.adopt.adopt.AdoptService;
import com.example.hope_dog.service.notice.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/list")
    public String List(Model model) {
        List<NoticeListDTO> noticeList = noticeService.getNoticeList();
        model.addAttribute("noticeList", noticeList);

        return "notice/notice-list";
    }

    @GetMapping("/view")
    public String View(@RequestParam("noticeNo") Long noticeNo,Model model) {
        List<NoticeViewDTO> noticeViewList = noticeService.getNoticeViewList(noticeNo);
        model.addAttribute("noticeViewList", noticeViewList);

        return "notice/notice-detail";
    }


//    @GetMapping("/write")
//    public String boardWrite(@SessionAttribute(value = "userId", required = false) Long userId) {
//        return userId == null ? "redirect:/user/login" : "board/write";
//    }
//
//    @PostMapping("/write")
//    public String boardWrite(BoardWriteDTO boardWriteDTO, @SessionAttribute("userId") Long userId
//            , RedirectAttributes redirectAttributes, @RequestParam("boardFile") List<MultipartFile> files) {
//        boardWriteDTO.setUserId(userId);
//        log.info("boardWriteDTO = ", boardWriteDTO);
//
//        try {
//            boardService.registerBoardWithFiles(boardWriteDTO, files);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        



}
