package com.example.hope_dog.controller.mypage;

import com.example.hope_dog.dto.centermypage.notebox.*;
import com.example.hope_dog.dto.member.MemberSessionDTO;
import com.example.hope_dog.dto.mypage.*;
import com.example.hope_dog.dto.notice.NoticeViewDTO;
import com.example.hope_dog.service.centermypage.NoteBoxService;
import com.example.hope_dog.service.mypage.MpNoteBoxService;
import com.example.hope_dog.service.mypage.MypageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession; // Jakarta EE 사용
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/mypage")
public class MypageController {
    //로그인 세션
    private final HttpSession session;


    private MypageService mypageService;

    @Autowired
    public MypageController(HttpSession session, MypageService mypageService, MpNoteBoxService mpNoteBoxService) {
        this.session = session;
        this.mypageService = mypageService;
        this.mpNoteBoxService = mpNoteBoxService;
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

//    @GetMapping("/noteboxR")
//    public String noteboxR(HttpSession session, Model model) {
//        Long memberNo = (Long) session.getAttribute("memberNo");
//        if (memberNo == null) {
//            return "redirect:/login";
//        }
//
//        List<MypageNoteReceiveDTO> mypageNoteReceiveList = mypageService.getMypageNoteReceiveProfile(memberNo);
//        model.addAttribute("mypageNoteReceiveList", mypageNoteReceiveList);
//
//        return "mypage/mypage-inbox";
//    }
//
//    @GetMapping("/noteboxS")
//    public String noteboxS(HttpSession session, Model model) {
//        Long memberNo = (Long) session.getAttribute("memberNo");
//        if (memberNo == null) {
//            return "redirect:/login";
//        }
//
//        List<MypageNoteSendDTO> mypageNoteSendList = mypageService.getMypageNoteSendProfile(memberNo);
//        model.addAttribute("mypageNoteSendList", mypageNoteSendList);
//
//        return "mypage/mypage-outbox";
//    }

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


    //쪽지함
    private final MpNoteBoxService mpNoteBoxService;

    //보낸쪽지함
    @GetMapping("/noteboxSendList")
    public String sendList(Model model) {
        Long memberNo = (Long) session.getAttribute("memberNo"); // 세션에서 회원 번호 가져오기
        if (memberNo == null) {
            log.error("세션에서 centerMemberNo를 찾을 수 없습니다.");
            return "redirect:/login"; // 세션이 없으면 로그인 페이지로 리다이렉트
        }

        List<MypageNoteSendDTO> noteboxSendList = mpNoteBoxService.getSendList(memberNo);
        model.addAttribute("noteboxSendList", noteboxSendList);
        log.info("SendList 요청이 들어왔습니다. memberNo: {}", memberNo);

        return "mypage/mypage-outbox"; // HTML 파일 경로
    }

    //받은쪽지함
    @GetMapping("/noteboxReceiveList")
    public String receiveList(Model model) {
        Long memberNo = (Long) session.getAttribute("memberNo"); // 세션에서 회원 번호 가져오기
        if (memberNo == null) {
            log.error("세션에서 memberNo를 찾을 수 없습니다.");
            return "redirect:/login"; // 세션이 없으면 로그인 페이지로 리다이렉트
        }


        List<MypageNoteReceiveDTO> noteboxReceiveList = mpNoteBoxService.getReceiveList(memberNo);
        model.addAttribute("noteboxReceiveList", noteboxReceiveList);
        log.info("ReceiveList 요청이 들어왔습니다. memberNo: {}", memberNo);

        return "mypage/mypage-inbox"; // HTML 파일 경로
    }

    // 보낸 쪽지 상세 페이지
    @GetMapping("/noteboxSendDetail")
    public String getNoteboxSendDetail(@RequestParam("noteboxSendNo") Long noteboxSendNo, Model model) {
        MpNoteboxSendDetailDTO mpNoteboxSendDetail = mpNoteBoxService.getNoteboxSendDetail(noteboxSendNo);

        // 쪽지 정보를 찾지 못한 경우
        if (mpNoteboxSendDetail == null) {
            log.error("보낸 쪽지를 찾을 수 없습니다: {}", noteboxSendNo);
            return "redirect:/mypage/noteboxSendList"; // 쪽지를 찾지 못하면 목록으로 리다이렉트
        }

        model.addAttribute("mpNoteboxSendDetail", mpNoteboxSendDetail); // 모델에 쪽지 상세 정보 추가
        return "mypage/mypage-outbox-detail"; // 상세 페이지의 템플릿 이름
    }

    // 받은 쪽지 상세 페이지
    @GetMapping("/noteboxReceiveDetail")
    public String getNoteboxReceiveDetail(@RequestParam("noteboxReceiveNo") Long noteboxReceiveNo, Model model) {
        MpNoteboxReceiveDetailDTO mpNoteboxReceiveDetail = mpNoteBoxService.getNoteboxReceiveDetail(noteboxReceiveNo);

        // 쪽지 정보를 찾지 못한 경우
        if (mpNoteboxReceiveDetail == null) {
            log.error("보낸 쪽지를 찾을 수 없습니다: {}", noteboxReceiveNo);
            return "redirect:/mypage/noteboxReceiveList"; // 쪽지를 찾지 못하면 목록으로 리다이렉트
        }

        model.addAttribute("mpNoteboxReceiveDetail", mpNoteboxReceiveDetail); // 모델에 쪽지 상세 정보 추가
        return "mypage/mypage-inbox-detail"; // 상세 페이지의 템플릿 이름
    }


    //쪽지보내기 페이지 이동
    @GetMapping(value = "/noteboxWrite")
    public String noteboxWrite(Model model, HttpSession session) {
        Long memberNo = (Long) session.getAttribute("memberNo"); // 세션에서 센터회원 번호 가져오기
        if (memberNo == null) {
            log.error("세션에서 memberNo를 찾을 수 없습니다.");
            return "redirect:/login"; // 세션이 없으면 로그인 페이지로 리다이렉트
        }

        // DTO 객체 생성 및 모델에 추가
        model.addAttribute("mpNoteboxWriteDTO", new MpNoteboxWriteDTO());

        return "mypage/mypage-notebox-write"; // HTML 파일 경로
    }

    //쪽지보내기 (답장) 페이지 이동
    @GetMapping("/noteboxResponse")
    public String writeNote(@RequestParam("noteboxSenderName") String noteboxSenderName, Model model, HttpSession session) {
        // 세션 체크 및 DTO 설정
        Long memberNo = (Long) session.getAttribute("memberNo");
        if (memberNo == null) {
            log.error("세션에서 memberNo를 찾을 수 없습니다.");
            return "redirect:/login";
        }

        MpNoteboxWriteDTO mpNoteboxWriteDTO = new MpNoteboxWriteDTO();
        mpNoteboxWriteDTO.setNoteboxReceiverName(noteboxSenderName); // senderName을 받는 사람 이름으로 설정
        model.addAttribute("mpNoteboxWriteDTO", mpNoteboxWriteDTO);

        return "mypage/mypage-notebox-write";
    }

    // 쪽지 전송 실패
    @GetMapping("/notebox/sendFail")
    public String showSendNoteForm(Model model) {
        model.addAttribute("mpNoteboxWriteDTO", new MpNoteboxWriteDTO());
        return "mypage/mypage-notebox-write"; // 템플릿 경로
    }

    // 쪽지 보내기
    @PostMapping("/sendingNote")
    public String sendingNote(@ModelAttribute MpNoteboxWriteDTO mpNoteboxWriteDTO, RedirectAttributes redirectAttributes) {
        // 세션에서 회원 번호 가져오기
        Long senderNo = (Long) session.getAttribute("memberNo");

        if (senderNo == null) {
            redirectAttributes.addFlashAttribute("error", "발신자 정보를 찾을 수 없습니다.");
            return "redirect:/mypage/notebox/sendFail"; // 에러가 발생하면 다시 폼으로 돌아감
        }

        Long receiverNo;
        try {
            receiverNo = mpNoteBoxService.findMemberNoByNickname(mpNoteboxWriteDTO.getNoteboxReceiverName());
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            log.error("수신자 조회 실패: {}", e.getMessage());
            return "redirect:/mypage/notebox/sendFail"; // 에러가 발생하면 다시 폼으로 돌아감
        }

        if (receiverNo != null) {
            // DTO에 수신자 번호 및 발신자 번호 설정
            mpNoteboxWriteDTO.setNoteboxR(receiverNo);
            mpNoteboxWriteDTO.setNoteboxS(senderNo); // 발신자 번호 설정

            // 쪽지 전송
            mpNoteBoxService.sendingNote(mpNoteboxWriteDTO);
            log.info("쪽지가 성공적으로 전송되었습니다: {}", mpNoteboxWriteDTO);
            return "redirect:/mypage/noteboxSendList"; // 보낸 쪽지함으로 리다이렉트
        } else {
            redirectAttributes.addFlashAttribute("error", "회원 번호를 찾을 수 없습니다.");
            log.error("회원 번호를 찾을 수 없습니다: {}", mpNoteboxWriteDTO.getNoteboxReceiverName());
            return "redirect:/mypage/notebox/sendFail"; // 에러가 발생하면 다시 폼으로 돌아감
        }
    }


    @GetMapping("/noteboxDelete")
    public String deleteNote(@RequestParam("noteboxReceiveNo") Long noteboxReceiveNo, Model model) {
        boolean isDeleted = mpNoteBoxService.deleteNoteByReceiveNo(noteboxReceiveNo);

        if (isDeleted) {
            log.info("쪽지 번호 {}가 성공적으로 삭제되었습니다.", noteboxReceiveNo);
            return "redirect:/mypage/noteboxReceiveList"; // 쪽지 리스트 페이지로 리다이렉트
        } else {
            log.error("쪽지 번호 {} 삭제 실패", noteboxReceiveNo);
            model.addAttribute("errorMessage", "쪽지 삭제에 실패했습니다.");
            return "mypage/noteboxError"; // 에러 페이지로 이동
        }
    }





}
