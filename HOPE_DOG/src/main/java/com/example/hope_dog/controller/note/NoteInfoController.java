package com.example.hope_dog.controller.note;

import com.example.hope_dog.service.note.NoteInfoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class NoteInfoController {
    private final HttpSession session;
    private final NoteInfoService noteInfoService;

    @GetMapping("/count")
    public void countPage(Model model) {
        Long memberNo = (Long) session.getAttribute("memberNo");
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");

        Long userNo = (memberNo != null) ? memberNo : centerMemberNo;
        Long unreadCount = null;

        if (userNo != null) {
            unreadCount = noteInfoService.getUnreadCount(userNo);
            session.setAttribute("unreadCount", unreadCount); // 세션에 업데이트
        }

        model.addAttribute("unreadCount", unreadCount);

        // count.html 페이지로 이동
//        return "count"; // count.html로 이동
    }

    @GetMapping("/markAllAsRead")
    public String markAllAsRead(HttpServletRequest request) {
        Long memberNo = (Long) session.getAttribute("memberNo");
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");

        Long userNo = (memberNo != null) ? memberNo : centerMemberNo;

        if (userNo != null) {
            noteInfoService.markAllAsRead(userNo);
            Long unreadCount = noteInfoService.getUnreadCount(userNo); // 다시 읽지 않은 쪽지 개수 조회
            session.setAttribute("unreadCount", unreadCount); // 세션 업데이트
            log.info("Updated unread count for user {}: {}", userNo, unreadCount); // 로그 추가

        }

        // 현재 URL 가져오기
        String referer = request.getHeader("Referer");
        if (referer == null || referer.isEmpty()) {
            referer = "/defaultPage"; // 기본 리다이렉트 URL
        }
        return "redirect:" + referer; // 원래 페이지로 리다이렉트
    }
}

