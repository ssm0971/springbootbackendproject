package com.example.hope_dog.controller.centermypage;

import com.example.hope_dog.dto.centermypage.CenterProfileDTO;
import com.example.hope_dog.dto.centermypage.notebox.NoteboxReceiveListDTO;
import com.example.hope_dog.dto.centermypage.notebox.NoteboxSendListDTO;
import com.example.hope_dog.dto.centermypage.notebox.NoteboxWriteDTO;
import com.example.hope_dog.service.centermypage.CenterMypageService;
import com.example.hope_dog.service.centermypage.NoteBoxService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/centerMypage")
class CenterMypageController {
    private final CenterMypageService centerMypageService;
    private final HttpSession session;

    @GetMapping("/centerProfile")
    public String centerProfile(Model model) {
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo"); // 세션에서 센터회원 번호 가져오기

        if (centerMemberNo == null) {
            log.warn("세션에서 centerMemberNo가 존재하지 않습니다.");
            return "redirect:/login"; // 세션이 없으면 로그인 페이지로 리다이렉트
        }

        // 프로필 정보 가져오기
        CenterProfileDTO profile = centerMypageService.centerProfile(centerMemberNo);
        model.addAttribute("profile", profile); // 모델에 프로필 정보 추가

        return "centermypage/center-mypage-profile"; // HTML 파일 경로
    }






    private final NoteBoxService noteBoxService;

    //보낸쪽지함
    @GetMapping("/noteboxSendList")
    public String sendList(Model model) {
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo"); // 세션에서 센터회원 번호 가져오기
        if (centerMemberNo == null) {
            log.error("세션에서 centerMemberNo를 찾을 수 없습니다.");
            return "redirect:/login"; // 세션이 없으면 로그인 페이지로 리다이렉트
        }

        List<NoteboxSendListDTO> noteboxSendList = noteBoxService.getSendList(centerMemberNo);
        model.addAttribute("noteboxSendList", noteboxSendList);
        log.info("SendList 요청이 들어왔습니다. centerMemberNo: {}", centerMemberNo);

        return "centermypage/notebox/center-mypage-notebox-send"; // HTML 파일 경로
    }

    //받은쪽지함
    @GetMapping("/noteboxReceiveList")
    public String receiveList(Model model) {
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo"); // 세션에서 센터회원 번호 가져오기
        if (centerMemberNo == null) {
            log.error("세션에서 centerMemberNo를 찾을 수 없습니다.");
            return "redirect:/login"; // 세션이 없으면 로그인 페이지로 리다이렉트
        }

        List<NoteboxReceiveListDTO> noteboxReceiveList = noteBoxService.getReceiveList(centerMemberNo);
        model.addAttribute("noteboxReceiveList", noteboxReceiveList);
        log.info("ReceiveList 요청이 들어왔습니다. centerMemberNo: {}", centerMemberNo);

        return "centermypage/notebox/center-mypage-notebox-receive"; // HTML 파일 경로
    }

    //쪽지보내기 페이지 이동
//    @GetMapping(value = "/noteboxWrite")
//    public String noteboxWrite() {
//        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo"); // 세션에서 센터회원 번호 가져오기
//        if (centerMemberNo == null) {
//            log.error("세션에서 centerMemberNo를 찾을 수 없습니다.");
//            return "redirect:/centerMypage/noteboxSendList"; // 세션이 없으면 로그인 페이지로 리다이렉트
//        }
//
//        return "centermypage/notebox/center-mypage-notebox-write"; // HTML 파일 경로
//    }
    //쪽지보내기 페이지 이동
    @GetMapping(value = "/noteboxWrite")
    public String noteboxWrite(Model model, HttpSession session) {
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo"); // 세션에서 센터회원 번호 가져오기
        if (centerMemberNo == null) {
            log.error("세션에서 centerMemberNo를 찾을 수 없습니다.");
            return "redirect:/login"; // 세션이 없으면 로그인 페이지로 리다이렉트
        }

        // 필요한 경우, 모델에 데이터 추가
        model.addAttribute("noteboxWriteDTO", new NoteboxWriteDTO()); // DTO 객체 생성 및 모델에 추가

        return "centermypage/notebox/center-mypage-notebox-write"; // HTML 파일 경로
    }

    // 쪽지 전송 처리
    @PostMapping("/sendingNote")
    public String sendingNote(@ModelAttribute NoteboxWriteDTO noteboxWriteDTO, Model model) {
        // 세션에서 센터회원 번호 가져오기
        Long senderNo = (Long) session.getAttribute("centerMemberNo");

        // 닉네임을 통해 회원 번호 조회
        Long receiverNo = noteBoxService.findMemberNoByNickname(noteboxWriteDTO.getNoteboxReceiverName());

        if (receiverNo != null) {
            // DTO에 수신자 번호 및 발신자 번호 설정
            noteboxWriteDTO.setNoteboxReceiveNo(receiverNo);
            noteboxWriteDTO.setNoteboxSendNo(senderNo); // 발신자 번호 설정

            // 쪽지 전송
            noteBoxService.sendingNote(noteboxWriteDTO);
            log.info("쪽지가 성공적으로 전송되었습니다: {}", noteboxWriteDTO);
            return "redirect:/centermypage/NoteboxSendList"; // 쪽지 전송 후 목록 페이지로 리다이렉트
        } else {
            // 쪽지 전송 실패시 원래페이지로
            model.addAttribute("error", "회원 번호를 찾을 수 없습니다.");
            log.error("회원 번호를 찾을 수 없습니다: {}", noteboxWriteDTO.getNoteboxReceiver());
            return "centermypage/notebox/center-mypage-notebox-send"; // 에러가 발생하면 다시 폼으로 돌아감
        }
    }



}
