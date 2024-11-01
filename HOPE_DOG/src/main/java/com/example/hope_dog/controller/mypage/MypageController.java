package com.example.hope_dog.controller.mypage;

import com.example.hope_dog.dto.member.MemberSessionDTO;
import com.example.hope_dog.dto.mypage.*;
import com.example.hope_dog.dto.notice.NoticeViewDTO;
import com.example.hope_dog.service.mypage.MypageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.servlet.http.HttpSession; // Jakarta EE 사용
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/mypage")
public class MypageController {

    private MypageService mypageService;

    @Autowired
    public MypageController(MypageService mypageService) {
        this.mypageService = mypageService;
    }

    @GetMapping("/mypage")
    public String profile(HttpSession session, Model model) {
        Long memberNo = (Long) session.getAttribute("memberNo"); // 세션에서 memberNo 가져오기

        if (memberNo == null) {
            // memberNo가 없을 경우 적절한 처리 (예: 로그인 페이지로 리다이렉트)
            return "redirect:/login"; // 예시: 로그인 페이지로 리다이렉트
        }

        List<MypageDTO> mypageProfile = mypageService.getMypageProfile(memberNo);
        model.addAttribute("mypageProfile", mypageProfile);

        return "mypage/mypage-profile";
    }

    @GetMapping("/adopt")
    public String adopt(HttpSession session, Model model) {
        Long memberNo = (Long) session.getAttribute("memberNo"); // 세션에서 memberNo 가져오기

        if (memberNo == null) {
            // memberNo가 없을 경우 적절한 처리 (예: 로그인 페이지로 리다이렉트)
            return "redirect:/login"; // 예시: 로그인 페이지로 리다이렉트
        }

        List<MypageAdoptListDTO> mypageAdoptList = mypageService.getMypageAdoptProfile(memberNo);
        model.addAttribute("mypageAdoptList", mypageAdoptList);

        return "mypage/mypage-adopt";
    }

    @GetMapping("/protect")
    public String protect(HttpSession session, Model model) {
        Long memberNo = (Long) session.getAttribute("memberNo");

        if (memberNo == null) {
            return "redirect:/login";
        }

        List<MypageProtectDTO> mypageProtectList = mypageService.getMypageProtectProfile(memberNo);
        model.addAttribute("mypageProtectList", mypageProtectList);

        return "mypage/mypage-protect";
    }

    @GetMapping("/volun")
    public String volun() {
        return "mypage/mypage-volunteer";
    }

    @GetMapping("/volunList")
    public String volunList(HttpSession session, Model model) {
        Long memberNo = (Long) session.getAttribute("memberNo");
        if (memberNo == null) {
            return "redirect:/login";
        }

        List<MypageVolunDTO> mypageVolunList = mypageService.getMypageVolunProfile(memberNo);
        model.addAttribute("mypageVolunList", mypageVolunList);

        return "mypage/mypage-volun-list";
    }

    @GetMapping("/posts")
    public String posts(HttpSession session, Model model) {
        Long memberNo = (Long) session.getAttribute("memberNo");
        if (memberNo == null) {
            return "redirect:/login";
        }

        List<MypagePostsDTO> mypagePostsList = mypageService.getMypagePostsProfile(memberNo);
        model.addAttribute("mypagePostsList", mypagePostsList);

        return "mypage/mypage-posts";
    }

    @GetMapping("/noteboxR")
    public String noteboxR(HttpSession session, Model model) {
        Long memberNo = (Long) session.getAttribute("memberNo");
        if (memberNo == null) {
            return "redirect:/login";
        }

        List<MypageNoteReceiveDTO> mypageNoteReceiveList = mypageService.getMypageNoteReceiveProfile(memberNo);
        model.addAttribute("mypageNoteReceiveList", mypageNoteReceiveList);

        return "mypage/mypage-inbox";
    }

    @GetMapping("/noteboxS")
    public String noteboxS(HttpSession session, Model model) {
        Long memberNo = (Long) session.getAttribute("memberNo");
        if (memberNo == null) {
            return "redirect:/login";
        }

        List<MypageNoteSendDTO> mypageNoteSendList = mypageService.getMypageNoteSendProfile(memberNo);
        model.addAttribute("mypageNoteSendList", mypageNoteSendList);

        return "mypage/mypage-outbox";
    }

//    @GetMapping("/profile")
//    public String viewProfile(HttpSession session, Model model) {
//        // 세션에서 사용자 정보를 가져옴
//        String memberNo = (String) session.getAttribute("memberNo");
//        String memberId = (String) session.getAttribute("memberId");
//        String memberName = (String) session.getAttribute("memberName");
//        String memberNickname = (String) session.getAttribute("memberNickname");
//        String memberEmail = (String) session.getAttribute("memberEmail");
//
//        // 로그인 상태 확인
//        if (memberId == null) {
//            return "redirect:/member/login"; // 로그인하지 않은 경우 로그인 페이지로 리다이렉트
//        }
//
//        MemberSessionDTO memberInfo = mypageService.getMemberInfo(memberId);
//
//        // 모델에 사용자 정보를 추가하여 프로필 페이지에 표시
//        model.addAttribute("memberNo", memberNo);
//        model.addAttribute("memberId", memberId);
//        model.addAttribute("memberName", memberName);
//        model.addAttribute("memberNickname", memberNickname);
//        model.addAttribute("memberEmail", memberEmail);
//
//        return "mypage/mypage-profile";
//    }

//    @GetMapping("/adopt")
//    public String adoptList(HttpSession session, Model model) {
//        String memberNo = (String) session.getAttribute("memberNo");
//
//        // 로그인 상태 확인
////        if (memberNo == null) {
////            return "redirect:/member/login"; // 로그인하지 않은 경우 로그인 페이지로 리다이렉트
////        }
//        MemberSessionDTO memberInfo = mypageService.getMemberInfo(memberNo);
//
//        return "mypage/mypage-adopt";
//    }
}