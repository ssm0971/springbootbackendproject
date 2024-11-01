package com.example.hope_dog.controller.notice;

import com.example.hope_dog.dto.notice.NoticeListDTO;
import com.example.hope_dog.dto.notice.NoticeViewDTO;
import com.example.hope_dog.dto.page.Criteria;
import com.example.hope_dog.dto.page.Page;
import com.example.hope_dog.service.notice.NoticeService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;


@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;

//    @GetMapping("/list")
//    public String List(Model model) {
//        List<NoticeListDTO> noticeList = noticeService.getNoticeList();
//        model.addAttribute("noticeList", noticeList);
//
//        return "notice/notice-list";
//    }

    @GetMapping("/list")
    public String noticeList(Criteria criteria, Model model, HttpSession session) {
        List<NoticeListDTO> noticeList = noticeService.findAllPage(criteria);
        int total = noticeService.findTotal();
        Page page = new Page(criteria, total);

        model.addAttribute("noticeList", noticeList);
        model.addAttribute("page", page);

        return "notice/notice-list";
    }
    //    @GetMapping("/list")
//    public String donationList(Criteria criteria, Model model, HttpSession session) {
//        List<DonationListDTO> donationList = donationService.findAllPage(criteria);
//        int total = donationService.findTotal();
//        Page page = new Page(criteria, total);
//
//        model.addAttribute("donationList", donationList);
//        model.addAttribute("page", page);
//
//        return "donation/donation-main-center";
//    }




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
