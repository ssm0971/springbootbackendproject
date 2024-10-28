package com.example.hope_dog.controller.admin;

import com.example.hope_dog.dto.admin.*;
import com.example.hope_dog.service.admin.AdminService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/login")
    public String login() {
        return "admin/admin-login/admin-login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("adminId") String adminId, @RequestParam("adminPw") String adminPw, HttpSession session) {
        log.info("로그인 시도 : {}", adminId);

        AdminSessionDTO loginInfo = adminService.findLoginInfo(adminId, adminPw);

        session.setAttribute("adminNo", loginInfo.getAdminNo());
        session.setAttribute("adminId", loginInfo.getAdminId());

        return "redirect:/admin/main";
    }

    @GetMapping("/main")
    public String main(Model model) {
        List<AdminMemberDTO> memberList = adminService.selectMemberList();
        List<AdminReportDTO> reportList = adminService.selectReportList();
        List<AdminPostDTO> postList = adminService.selectPostList();
        List<AdminCommentDTO> commentList = adminService.selectCommentList();

        model.addAttribute("memberList", memberList);
        model.addAttribute("reportList", reportList);
        model.addAttribute("postList", postList);
        model.addAttribute("commentList", commentList);

        return "admin/admin-main/admin-main"; // 뷰 이름 반환
    }
}