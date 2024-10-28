package com.example.hope_dog.controller.mypage;

import com.example.hope_dog.dto.member.MemberSessionDTO;
import com.example.hope_dog.service.mypage.MypageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.servlet.http.HttpSession; // Jakarta EE 사용

@Controller
@RequestMapping("/mypage")
public class MypageController {

    private MypageService mypageService;

    @Autowired
    public MypageController(MypageService mypageService) {
        this.mypageService = mypageService;
    }

    @GetMapping("/profile")
    public String viewProfile(HttpSession session, Model model) {
        // 세션에서 사용자 정보를 가져옴
        String memberNo = (String) session.getAttribute("memberNo");
        String memberId = (String) session.getAttribute("memberId");
        String memberName = (String) session.getAttribute("memberName");
        String memberNickname = (String) session.getAttribute("memberNickname");
        String memberEmail = (String) session.getAttribute("memberEmail");

        // 로그인 상태 확인
        if (memberId == null) {
            return "redirect:/member/login"; // 로그인하지 않은 경우 로그인 페이지로 리다이렉트
        }

        MemberSessionDTO memberInfo = mypageService.getMemberInfo(memberId);

        // 모델에 사용자 정보를 추가하여 프로필 페이지에 표시
        model.addAttribute("memberNo", memberNo);
        model.addAttribute("memberId", memberId);
        model.addAttribute("memberName", memberName);
        model.addAttribute("memberNickname", memberNickname);
        model.addAttribute("memberEmail", memberEmail);

        return "mypage/mypage-profile";
    }
}
