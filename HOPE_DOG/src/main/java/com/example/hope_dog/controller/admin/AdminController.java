package com.example.hope_dog.controller.admin;

import com.example.hope_dog.dto.admin.*;
import com.example.hope_dog.service.admin.AdminFileService;
import com.example.hope_dog.service.admin.AdminService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    private final AdminFileService adminFileService;

    private boolean isAdminLoggedIn(HttpSession session) {
        return session.getAttribute("adminNo") != null;
    }

    @GetMapping("/login")
    public String login() {
        return "admin/admin-login/admin-login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("adminId") String adminId,
                        @RequestParam("adminPw") String adminPw,
                        HttpSession session) {
        AdminSessionDTO loginInfo = adminService.findLoginInfo(adminId, adminPw);

        if (loginInfo == null) {
            // 로그인 실패 시 error 파라미터를 URL에 직접 추가
            return "redirect:/admin/login?error=true";
        }

        // 로그인 성공 시 세션에 관리자 정보 저장
        session.setAttribute("adminNo", loginInfo.getAdminNo());
        session.setAttribute("adminId", loginInfo.getAdminId());

        return "redirect:/admin/main";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {

        // 세션에서 로그인 정보 제거
        session.invalidate(); // 세션 전체를 무효화하여 모든 속성을 제거합니다.

        return "redirect:/admin/login"; // 로그인 페이지로 리다이렉트
    }


    @GetMapping("/main")
    public String main(Model model, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login"; // 비로그인 시 로그인 페이지로 리다이렉트
        }
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

    @GetMapping("/postList")
    public String postList(Model model, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login"; // 비로그인 시 로그인 페이지로 리다이렉트
        }
        List<AdminPostDTO> postList = adminService.selectPostList();

        model.addAttribute("postList", postList);

        return "admin/admin-post/admin-post-list";
    }

    @GetMapping("/searchPostList")
    public String searchPostList(@RequestParam("keyword") String keyword, Model model, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login";
        }
        System.out.println("실행");
        List<AdminPostDTO> searchedPostList = adminService.searchPostByKeyword(keyword);
        System.out.println(searchedPostList);

        model.addAttribute("searchedPostList", searchedPostList);

        return "admin/admin-post/admin-post-search-list";
    }

    @PostMapping("/deletePost")
    @DeleteMapping
    @ResponseBody
    public String deletePost(@RequestBody List<AdminPostDTO> itemList, HttpSession session) {
        // 관리자 로그인 체크
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login";
        }

        // 각 게시글 삭제
        for (AdminPostDTO item : itemList) {
            adminService.deletePost(item); // 게시글 삭제 메서드 호출
        }

        return "redirect:/admin/postList";
    }

    @PostMapping("/deleteComment")
    @DeleteMapping
    @ResponseBody
    public String deleteComment(@RequestBody List<AdminCommentDTO> itemList, HttpSession session) {
        // 관리자 로그인 체크
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login";
        }

        // 각 게시글 삭제
        for (AdminCommentDTO item : itemList) {
            adminService.deleteComment(item); // 게시글 삭제 메서드 호출
        }

        return "redirect:/admin/commentList";
    }


    @GetMapping("/postDetail")
    public String postDetail(@RequestParam("postType") String postType, @RequestParam("postNo") Long postNo, Model model, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login";
        }

        AdminPostDTO post = adminService.selectPostDetail(postType, postNo);
        List<AdminCommentDTO> commentList = adminService.selectCommentListByPostNo(postType, postNo);
        List<AdminFileDTO> fileList = adminFileService.selectFileListByPostNo(post);

        model.addAttribute("post", post);
        model.addAttribute("commentList", commentList);
        model.addAttribute("fileList", fileList);

        return "admin/admin-post/admin-post-detail";
    }

    @PostMapping("/deletePostDetail")
    @DeleteMapping
    @ResponseBody
    public String deletePostDetail(@RequestBody AdminPostDTO item, HttpSession session) {
        // 관리자 로그인 체크
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login";
        }

        adminService.deletePostDetail(item);

        return "redirect:/admin/postList";
    }

    @PostMapping("/deleteCommentDetail")
    @DeleteMapping
    @ResponseBody
    public String deleteCommentDetail(@RequestBody AdminCommentDTO item, HttpSession session) {
        // 관리자 로그인 체크
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login";
        }

        adminService.deleteCommentDetail(item);

        return "redirect:/admin/postList";
    }


    @GetMapping("/commentList")
    public String commentList(Model model, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login"; // 비로그인 시 로그인 페이지로 리다이렉트
        }
        List<AdminCommentDTO> commentList = adminService.selectCommentList();
        List<AdminPostDTO> postList = adminService.selectPostList();

        model.addAttribute("commentList", commentList);
        model.addAttribute("postList", postList);

        return "admin/admin-comment/admin-comment-list";
    }

    @GetMapping("/searchCommentList")
    public String searchCommentList(@RequestParam("keyword") String keyword, Model model, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login";
        }

        List<AdminCommentDTO> searchedCommentList = adminService.searchCommentByKeyword(keyword);
        List<AdminPostDTO> searchedPostList = adminService.searchPostByKeyword(keyword);

        model.addAttribute("searchedCommentList", searchedCommentList);
        model.addAttribute("searchedPostList", searchedPostList);

        return "admin/admin-comment/admin-comment-search-list";
    }

    @GetMapping("/noticeWrite")
    public String noticeWrite(HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login";
        }

        return "admin/admin-notice/admin-notice-write";
    }

    @PostMapping("/noticeWrite")
    public String noticeWrite(@RequestParam("files") List<MultipartFile> files,
                              @RequestParam("cate") String cate,
                              @RequestParam("title") String title,
                              @RequestParam("content") String content,
                              Model model, HttpSession session) throws IOException {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login";
        }

        AdminNoticeDTO notice = new AdminNoticeDTO();
        notice.setNoticeCate(cate);
        notice.setNoticeTitle(title);
        notice.setNoticeContent(content);

        adminService.insertNotice(notice, files);

        return "redirect:/admin/noticeList";
    }

    @GetMapping("/noticeModify")
    public String noticeModify(@RequestParam("noticeNo") Long noticeNo, Model model, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login";
        }

        AdminNoticeDTO notice = adminService.selectNoticeDetail(noticeNo);
        List<AdminFileDTO> fileList = adminFileService.selectFileByNoticeNo(noticeNo);

        model.addAttribute("notice", notice);
        model.addAttribute("fileList", fileList);

        return "admin/admin-notice/admin-notice-modify";
    }

    @PostMapping("/noticeModify")
    public String noticeModify(@RequestParam("noticeNo") Long noticeNo,
                               @RequestParam("files") List<MultipartFile> files,
                               @RequestParam("cate") String cate,
                               @RequestParam("title") String title,
                               @RequestParam("content") String content, Model model, HttpSession session) throws IOException {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login";
        }

        AdminNoticeDTO notice = new AdminNoticeDTO();
        notice.setNoticeNo(noticeNo);
        notice.setNoticeCate(cate);
        notice.setNoticeTitle(title);
        notice.setNoticeContent(content);

        adminService.modifyNotice(notice);
        adminService.deleteFileByNoticeNo(noticeNo);
        adminService.saveFiles(files);

        return "redirect:/admin/noticeDetail?noticeNo=" + (noticeNo);
    }

    @GetMapping("/noticeList")
    public String noticeList(Model model, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login"; // 비로그인 시 로그인 페이지로 리다이렉트
        }
        List<AdminNoticeDTO> noticeList = adminService.selectNoticeList();

        model.addAttribute("noticeList", noticeList);

        return "admin/admin-notice/admin-notice-list";
    }

    @GetMapping("/searchNoticeList")
    public String searchNoticeList(@RequestParam("keyword") String keyword ,Model model, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login";
        }

        List<AdminNoticeDTO> noticeList = adminService.searchNoticeByKeyword(keyword);

        model.addAttribute("noticeList", noticeList);

        return "admin/admin-notice/admin-notice-search-list";
    }

    @PostMapping("/deleteNotice")
    @DeleteMapping
    @ResponseBody
    public String deleteNotice(@RequestBody List<Long> itemList, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login";
        }

        adminService.deleteNotice(itemList);

        return "redirect:/admin/noticeList";
    }

    @GetMapping("/noticeDetail")
    public String noticeDetail(@RequestParam("noticeNo") Long noticeNo, Model model, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login";
        }

        AdminNoticeDTO notice = adminService.selectNoticeDetail(noticeNo);
        List<AdminFileDTO> files = adminFileService.selectFileByNoticeNo(noticeNo);

        model.addAttribute("notice", notice);
        model.addAttribute("fileList", files);

        return "admin/admin-notice/admin-notice-detail";
    }

    @GetMapping("/memberList")
    public String memberList(Model model, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login"; // 비로그인 시 로그인 페이지로 리다이렉트
        }
        List<AdminMemberDTO> memberList = adminService.selectMemberList();

        model.addAttribute("memberList", memberList);
        model.addAttribute("currentPage", "general");

        return "admin/admin-member/admin-member-list";
    }

    @GetMapping("/centerMemberList")
    public String centerMemberList(Model model, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login"; // 비로그인 시 로그인 페이지로 리다이렉트
        }
        List<AdminCenterMemberDTO> centerMemberList = adminService.selectPassedCenterMemberList();

        model.addAttribute("centerMemberList", centerMemberList);
        model.addAttribute("currentPage", "center");

        return "admin/admin-member/admin-center-member-list";
    }

    @PostMapping("/deleteMembers")
    @DeleteMapping
    @ResponseBody
    public String deleteMembers(@RequestBody List<Long> itemList, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login";
        }
        adminService.deleteMember(itemList);

        return "redirect:/admin/memberList";
    }

    @PostMapping("/deleteCenterMembers")
    @DeleteMapping
    @ResponseBody
    public String deleteCenterMembers(@RequestBody List<Long> itemList, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login";
        }
        adminService.deleteCenterMember(itemList);

        return "redirect:/admin/centerMemberList";
    }

    @GetMapping("/searchMemberList")
    public String searchMemberList(@RequestParam("keyword") String keyword, Model model, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login"; // 비로그인 시 로그인 페이지로 리다이렉트
        }
        List<AdminMemberDTO> memberSearchedList = adminService.searchMemberByKeyword(keyword);

        model.addAttribute("memberSearchedList", memberSearchedList);
        model.addAttribute("currentPage", "general");

        return "admin/admin-member/admin-member-search-list";
    }

    @GetMapping("/searchCenterMemberList")
    public String searchCenterMemberList(@RequestParam("keyword") String keyword, Model model, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login"; // 비로그인 시 로그인 페이지로 리다이렉트
        }
        List<AdminCenterMemberDTO> centerMemberSearchedList = adminService.searchPassedCenterMemberByKeyword(keyword);

        model.addAttribute("centerMemberSearchedList", centerMemberSearchedList);
        model.addAttribute("currentPage", "center");

        return "admin/admin-member/admin-center-member-search-list";
    }

    @GetMapping("/memberDetail")
    public String memberDetail(@RequestParam("memberNo") Long memberNo, Model model, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login";
        }
        AdminMemberDTO member = adminService.selectMemberByNo(memberNo);

        model.addAttribute("member", member);

        return "admin/admin-member/admin-member-detail";
    }

    @GetMapping("/centerMemberDetail")
    public String centerMemberDetail(@RequestParam("centerMemberNo") Long centerMemberNo, Model model, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login";
        }
        AdminCenterMemberDTO centerMember = adminService.selectPassedCenterMemberByNo(centerMemberNo);
        AdminCenterFileDTO file = adminFileService.selectFileByCenterMemberNo(centerMemberNo);

        model.addAttribute("centerMember", centerMember);
        model.addAttribute("file", file);

        return "admin/admin-member/admin-center-member-detail";
    }

    @GetMapping("/reportList")
    public String reportList(Model model, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login"; // 비로그인 시 로그인 페이지로 리다이렉트
        }
        List<AdminReportDTO> reportList = adminService.selectReportList();

        model.addAttribute("reportList", reportList);

        return "admin/admin-report/admin-report-list";
    }

    @GetMapping("/reportSearchList")
    public String reportSearchList(@RequestParam("keyword") String keyword, Model model, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login"; // 비로그인 시 로그인 페이지로 리다이렉트
        }
        List<AdminReportDTO> reportSearchList = adminService.searchReportByKeyword(keyword);

        model.addAttribute("reportSearchList", reportSearchList);

        return "admin/admin-report/admin-report-search-list";
    }

    @GetMapping("/centerApplyList")
    public String centerApplyList(Model model, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login"; // 비로그인 시 로그인 페이지로 리다이렉트
        }
        List<AdminCenterMemberDTO> centerMemberList = adminService.selectCenterMemberList();

        model.addAttribute("centerMemberList", centerMemberList);

        return "admin/admin-center-apply/admin-center-apply-list";
    }

    @GetMapping("/centerApplyDetail")
    public String centerApplyDetail(@RequestParam("centerMemberNo") Long centerMemberNo, Model model, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login";
        }

        AdminCenterMemberDTO centerMember = adminService.selectNotPassedCenterMemberByNo(centerMemberNo);
        AdminCenterFileDTO file = adminFileService.selectFileByCenterMemberNo(centerMemberNo);

        model.addAttribute("centerMember", centerMember);
        model.addAttribute("file", file);
        
        return "admin/admin-center-apply/admin-center-apply-detail";
    }

    @GetMapping("/adoptSearchList")
    public String adoptSearchList(@RequestParam("keyword") String keyword, Model model, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login";
        }
        List<AdminAdoptRequestDTO> adoptSearchList = adminService.searchAdoptRequestByKeyword(keyword);

        model.addAttribute("adoptSearchList", adoptSearchList);
        model.addAttribute("currentPage", "adopt");

        return "admin/admin-ATP/admin-adopt-search-list";
    }

    @GetMapping("/adoptList")
    public String adoptList(Model model, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login";
        }
        List<AdminAdoptRequestDTO> adoptRequestList = adminService.selectAdoptRequestList();

        model.addAttribute("adoptRequestList", adoptRequestList);
        model.addAttribute("currentPage", "adopt");

        return "admin/admin-ATP/admin-adopt-list";
    }

    @GetMapping("/protectList")
    public String protectList(Model model, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login";
        }
        List<AdminProtectRequestDTO> protectRequestList = adminService.selectProtectRequestList();

        model.addAttribute("protectRequestList", protectRequestList);
        model.addAttribute("currentPage", "protect");

        return "admin/admin-ATP/admin-protect-list";
    }

    @GetMapping("/protectSearchList")
    public String protectList(@RequestParam("keyword") String keyword, Model model, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login";
        }
        List<AdminProtectRequestDTO> protectSearchList = adminService.searchProtectRequestByKeyword(keyword);

        model.addAttribute("protectSearchList", protectSearchList);
        model.addAttribute("currentPage", "protect");

        return "admin/admin-ATP/admin-protect-search-list";
    }

    @GetMapping("/protectDetail")
    public String protectDetail(@RequestParam("protectRequestNo") Long protectRequestNo, Model model, HttpSession session){
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login";
        }

        AdminProtectRequestDTO protectRequest = adminService.selectProtectRequestDetail(protectRequestNo);

        model.addAttribute("protectRequest", protectRequest);

        return "admin/admin-ATP/admin-protect-detail";
    }

    @GetMapping("/adoptDetail")
    public String adoptDetail(@RequestParam("adoptRequestNo") Long adoptRequestNo, Model model, HttpSession session){
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login";
        }

        AdminAdoptRequestDTO adoptRequest = adminService.selectAdoptRequestDetail(adoptRequestNo);

        model.addAttribute("adoptRequest", adoptRequest);

        return "admin/admin-ATP/admin-adopt-detail";
    }

    @GetMapping("/volunList")
    public String volunList(Model model, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login";
        }
        List<AdminVolunRequestDTO> volunRequestList = adminService.selectVolunRequestList();

        model.addAttribute("volunRequestList", volunRequestList);

        return "admin/admin-volun/admin-volun-list";
    }

    @GetMapping("/volunSearchList")
    public String volunSearchList(@RequestParam("keyword") String keyword, Model model, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login";
        }
        List<AdminVolunRequestDTO> volunRequestList = adminService.searchVolunRequestList(keyword);

        model.addAttribute("volunRequestList", volunRequestList);

        return "admin/admin-volun/admin-volun-search-list";
    }

    @GetMapping("volunDetail")
    public String volunDetail(@RequestParam("volunNo") Long volunNo, Model model, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login";
        }
        AdminVolunRequestDTO volunRequest = adminService.selectVolunRequestDetail(volunNo);

        model.addAttribute("volunRequest", volunRequest);

        return "admin/admin-volun/admin-volun-detail";
    }

    @GetMapping("/noteboxIn")
    public String noteboxIn(Model model, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login";
        }
        List<AdminNoteReceiveDTO> noteReceiveList = adminService.selectNoteReceiveList();

        model.addAttribute("noteReceiveList", noteReceiveList);
        model.addAttribute("currentPage", "receive");

        return "admin/admin-notebox/admin-notebox-in";
    }

    @GetMapping("/searchNoteboxIn")
    public String searchNoteboxIn(@RequestParam("keyword") String keyword, Model model, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login";
        }

        List<AdminNoteReceiveDTO> noteReceiveList = adminService.searchNoteInByKeyword(keyword);

        model.addAttribute("noteReceiveList", noteReceiveList);
        model.addAttribute("currentPage", "receive");

        return "admin/admin-notebox/admin-notebox-in";
    }

    @GetMapping("/noteboxOut")
    public String noteboxOut(Model model, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login";
        }
        List<AdminNoteSendDTO> noteSendList = adminService.selectNoteSendList();

        model.addAttribute("noteSendList", noteSendList);
        model.addAttribute("currentPage", "send");

        return "admin/admin-notebox/admin-notebox-out";
    }

    @GetMapping("/searchNoteboxOut")
    public String searchNoteboxOut(@RequestParam("keyword") String keyword, Model model, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login";
        }
        List<AdminNoteSendDTO> noteSendList = adminService.searchNoteOutByKeyword(keyword);

        model.addAttribute("noteSendList", noteSendList);
        model.addAttribute("currentPage", "send");

        return "admin/admin-notebox/admin-notebox-out";
    }
    @GetMapping("/noteboxInDetail")
    public String noteboxInDetail(@RequestParam("noteboxReceiveNo") Long noteboxReceiveNo, Model model, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login";
        }

        AdminNoteReceiveDTO noteReceive = adminService.selectNoteReceiveDetail(noteboxReceiveNo);

        model.addAttribute("noteReceive", noteReceive);

        return "admin/admin-notebox/admin-notebox-in-detail";
    }

    @GetMapping("/noteboxOutDetail")
    public String noteboxOutDetail(@RequestParam("noteboxSendNo") Long noteboxSendNo, Model model, HttpSession session){
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login";
        }

        AdminNoteSendDTO noteSend = adminService.selectNoteSendDetail(noteboxSendNo);

        model.addAttribute("noteSend", noteSend);

        return "admin/admin-notebox/admin-notebox-out-detail";
    }

    @GetMapping("/noteboxWrite")
    public String noteboxWrite(@RequestParam(value = "noteSender", required = false) String noteSender, Model model, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login";
        }
        if (noteSender != null && !noteSender.isEmpty()) {
            model.addAttribute("noteSender", noteSender);
        }
        return "admin/admin-notebox/admin-notebox-write";
    }

    @PostMapping("/noteboxWrite")
    public String noteboxWrite(@RequestParam("title") String title, @RequestParam("content") String content, @RequestParam("receiver") String receiver, Model model, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login";
        }

        adminService.insertNoteWriteReceive(title, content, receiver);
        adminService.insertNoteWriteSend(title, content, receiver);

        return "redirect:/admin/noteboxOut";
    }

    @PostMapping("/deleteNoteIn")
    @DeleteMapping
    @ResponseBody
    public String deleteNoteIn(@RequestBody List<Long> receiveList, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login";
        }

        adminService.deleteNoteIn(receiveList);

        return "redirect:/admin/noteboxIn";
    }

    @PostMapping("/deleteNoteOut")
    @DeleteMapping
    @ResponseBody
    public String deleteNoteOut(@RequestBody List<Long> sendList, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login";
        }

        adminService.deleteNoteOut(sendList);

        return "redirect:/admin/noteboxOut";

    }

    @PostMapping("/approveCenterMember")
    @ResponseBody
    public String approveCenterMember(@RequestBody List<Long> itemList, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login";
        }

        adminService.approveCenterMember(itemList);

        for(Long item : itemList) {
            adminService.insertNoteWriteReceive("회원 가입 심사 요청 결과", "회원 가입 심사 요청 결과 - 승인됨", adminService.findCenterMemberNameByNo(item));
            adminService.insertNoteWriteSend("회원 가입 심사 요청 결과", "회원 가입 심사 요청 결과 - 승인됨", adminService.findCenterMemberNameByNo(item));
        }

        return "redirect:/admin/centerApplyList";
    }

    @PostMapping("/refuseCenterMember")
    @ResponseBody
    public String refuseCenterMember(@RequestBody List<Long> itemList, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/admin/login";
        }

        for(Long item : itemList) {
            System.out.println("asdasd" + item);
            adminService.insertNoteWriteReceive("회원 가입 심사 요청 결과", "회원 가입 심사 요청 결과 - 거절됨", adminService.findCenterMemberNameByNo(item));
            adminService.insertNoteWriteSend("회원 가입 심사 요청 결과", "회원 가입 심사 요청 결과 - 거절됨", adminService.findCenterMemberNameByNo(item));
        }

        return "redirect:/admin/centerApplyList";
    }
}