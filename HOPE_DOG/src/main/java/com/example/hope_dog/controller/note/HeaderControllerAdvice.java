package com.example.hope_dog.controller.note;

import com.example.hope_dog.service.note.NoteInfoService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@Slf4j
@RequiredArgsConstructor // final 필드를 가진 생성자를 자동으로 생성
@ControllerAdvice // 모든 컨트롤러에서 사용 가능한 전역 설정 클래스
public class HeaderControllerAdvice {

    private final HttpSession session;
    private final NoteInfoService noteInfoService;

    // 모든 컨트롤러에서 공통으로 사용할 모델 속성을 추가
    @ModelAttribute
    public void addAttributes(Model model) {
        Long memberNo = (Long) session.getAttribute("memberNo");
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");

        Long userNo = (memberNo != null) ? memberNo : centerMemberNo;
        if (userNo != null) {
            model.addAttribute("userNo", userNo);
            Long unreadCount = noteInfoService.getUnreadCount(userNo);
            model.addAttribute("unreadCount", unreadCount);
            log.info("Current unread count for user {}: {}", userNo, unreadCount); // 로그 추가
        }
    }
}
