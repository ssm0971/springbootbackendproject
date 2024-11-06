package com.example.hope_dog.controller.centermypage;

import com.example.hope_dog.dto.centermypage.CenterProfileDTO;
import com.example.hope_dog.dto.centermypage.CenterUpdateProfileDTO;
import com.example.hope_dog.dto.centermypage.CenterViewProfileDTO;
import com.example.hope_dog.dto.centermypage.notebox.*;
import com.example.hope_dog.dto.centermypage.request.AdoptRequestListDTO;
import com.example.hope_dog.dto.centermypage.request.ProtectRequestDetailDTO;
import com.example.hope_dog.dto.centermypage.request.ProtectRequestListDTO;
import com.example.hope_dog.dto.centermypage.request.VolunRequestListDTO;
import com.example.hope_dog.dto.centermypage.writeinfo.WriteInfoAdoptListDTO;
import com.example.hope_dog.dto.centermypage.writeinfo.WriteInfoCommuListDTO;
import com.example.hope_dog.dto.centermypage.writeinfo.WriteInfoVolListDTO;
import com.example.hope_dog.service.centermypage.CenterMypageService;
import com.example.hope_dog.service.centermypage.NoteBoxService;
import com.example.hope_dog.service.centermypage.RequestService;
import com.example.hope_dog.service.centermypage.WriteInfoService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/centerMypage")
class CenterMypageController {
    //로그인 세션
    private final HttpSession session;

    //센터회원
    private final CenterMypageService centerMypageService;

    //회원정보
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

    // 업데이트 페이지 이동
    @GetMapping("/updateProfile")
    public String updateProfilePage(Model model) {
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo"); // 세션에서 센터회원 번호 가져오기

        // 프로필 정보 가져오기
        CenterViewProfileDTO profile = centerMypageService.getCenterViewProfile(centerMemberNo);
        model.addAttribute("profile", profile); // 모델에 프로필 정보 추가

        return "centermypage/center-mypage-profile-update"; // 업데이트 페이지 HTML 파일 경로
    }

    // 이메일 중복 체크
    @GetMapping("/check-email")
    @ResponseBody
    public ResponseEntity<Map<String, Boolean>> checkCenterEmail(
            @RequestParam(name = "newEmail") String newEmail,
            @RequestParam(name = "currentEmail") String currentEmail) {

        // 중복 검사 수행
        boolean available = centerMypageService.updateCheckCenterEmail(newEmail, currentEmail);
        return ResponseEntity.ok(Map.of("available", available));
    }


//    @PostMapping("/updateProfileOk") // HTTP POST 요청을 처리하는 메서드
//    @ResponseBody // 메서드의 반환값을 HTTP 응답 본문에 직접 작성
//    public ResponseEntity<Map<String, String>> updateProfile(@ModelAttribute CenterUpdateProfileDTO centerUpdateProfileDTO) {
//        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
//
//        if (centerMemberNo == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "로그인이 필요합니다."));
//        }
//
//        centerUpdateProfileDTO.setCenterMemberNo(centerMemberNo);
//
//        try {
//            int updateCount = centerMypageService.updateCenterProfile(centerUpdateProfileDTO);
//            String message = (updateCount > 0)
//                    ? "프로필이 성공적으로 업데이트되었습니다."
//                    : "프로필 업데이트에 실패했습니다.";
//
//            return ResponseEntity.ok(Map.of("message", message));
//        } catch (Exception e) {
//            log.error("프로필 업데이트 중 오류 발생", e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "프로필 업데이트 중 오류가 발생했습니다."));
//        }
//    }


//    @PostMapping("/updateProfileOk") // HTTP POST 요청을 처리하는 메서드
//    @ResponseBody // 메서드의 반환값을 HTTP 응답 본문에 직접 작성
//    public ResponseEntity<Map<String, String>> updateProfile(@ModelAttribute CenterUpdateProfileDTO centerUpdateProfileDTO) {
//        // 세션에서 현재 사용자의 centerMemberNo를 가져옴
//        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
//
//        // 사용자가 로그인하지 않은 경우
//        if (centerMemberNo == null) {
//            // 로그인 필요 메시지를 포함하여 401 Unauthorized 응답 반환
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "로그인이 필요합니다."));
//        }
//
//        // centerMemberNo를 DTO에 설정하여 업데이트할 프로필 정보를 준비
//        centerUpdateProfileDTO.setCenterMemberNo(centerMemberNo);
//
//        // 프로필 업데이트 시도
//        try {
//            // 프로필 업데이트를 수행하고, 업데이트된 데이터의 수를 반환받음
//            int updateCount = centerMypageService.updateCenterProfile(centerUpdateProfileDTO);
//
//            // 업데이트가 성공적으로 이루어진 경우
//            if (updateCount > 0) {
//                // 성공 메시지를 포함하여 200 OK 응답 반환
//                return ResponseEntity.ok(Map.of("message", "프로필이 성공적으로 업데이트되었습니다."));
//            } else {
//                // 업데이트가 실패한 경우
//                return ResponseEntity.ok(Map.of("message", "프로필 업데이트에 실패했습니다."));
//            }
//        } catch (Exception e) {
//            // 예외 발생 시 오류 메시지를 로그에 기록
//            log.error("프로필 업데이트 중 오류 발생", e);
//            // 내부 서버 오류 메시지를 포함하여 500 Internal Server Error 응답 반환
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "프로필 업데이트 중 오류가 발생했습니다."));
//        }
//    }



    @PostMapping("/updateProfileOk")
    public String updateProfile(@ModelAttribute CenterUpdateProfileDTO centerUpdateProfileDTO, RedirectAttributes redirectAttributes) {
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
        if (centerMemberNo == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "로그인이 필요합니다.");
            return "redirect:/login";
        }

        // centerMemberNo를 DTO에 설정
        centerUpdateProfileDTO.setCenterMemberNo(centerMemberNo);

        // 프로필 업데이트 시도
        try {
            int updateCount = centerMypageService.updateCenterProfile(centerUpdateProfileDTO);
            if (updateCount > 0) {
                redirectAttributes.addFlashAttribute("successMessage", "프로필이 성공적으로 업데이트되었습니다.");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "프로필 업데이트에 실패했습니다.");
            }
        } catch (Exception e) {
            log.error("프로필 업데이트 중 오류 발생", e);
            redirectAttributes.addFlashAttribute("errorMessage", "프로필 업데이트 중 오류가 발생했습니다.");
        }

        return "redirect:/centerMypage/centerProfile"; // 업데이트 후 리다이렉트할 페이지
    }


//    // 프로필 수정 처리
//    @PostMapping("/updateProfileOk")
//    public String updateProfile(@ModelAttribute CenterUpdateProfileDTO centerUpdateProfileDTO, Model model) {
//        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
//        if (centerMemberNo == null) {
//            model.addAttribute("errorMessage", "로그인이 필요합니다.");
//            return "redirect:/login";
//        }
//
//        // centerMemberNo를 DTO에 설정
//        centerUpdateProfileDTO.setCenterMemberNo(centerMemberNo);
//
//        // 프로필 업데이트 시도
//        try {
//            int updateCount = centerMypageService.updateCenterProfile(centerUpdateProfileDTO);
//            if (updateCount > 0) {
//                model.addAttribute("successMessage", "프로필이 성공적으로 업데이트되었습니다.");
//            } else {
//                model.addAttribute("errorMessage", "프로필 업데이트에 실패했습니다.");
//            }
//        } catch (Exception e) {
//            log.error("프로필 업데이트 중 오류 발생", e);
//            model.addAttribute("errorMessage", "프로필 업데이트 중 오류가 발생했습니다.");
//        }
//
//        return "redirect:/centerMypage/centerProfile"; // 업데이트 후 리다이렉트할 페이지
//    }

//    // 프로필 수정 처리
//    @PostMapping("/updateProfileOk")
//    public String updateProfile(@ModelAttribute CenterUpdateProfileDTO centerUpdateProfileDTO, Model model) {
//        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
//        if (centerMemberNo == null) {
//            model.addAttribute("errorMessage", "로그인이 필요합니다.");
//            return "redirect:/login";
//        }
//
//        centerUpdateProfileDTO.setCenterMemberNo(centerMemberNo);
//        boolean isUpdated = centerMypageService.updateCenterProfile(centerUpdateProfileDTO);
//
//        if (!isUpdated) {
//            model.addAttribute("errorMessage", "현재 비밀번호가 일치하지 않습니다.");
//            return "redirect:/centerMypage/login";
//        }
//
//        model.addAttribute("successMessage", "프로필이 성공적으로 업데이트되었습니다.");
//        return "redirect:/centerMypage/centerProfile";
//    }

//    //프로필 수정
//    @PostMapping("/updateProfileOk")
//    public String updateProfile(@ModelAttribute CenterUpdateProfileDTO centerUpdateProfileDTO, Model model) {
//        // 세션에서 centerMemberNo 가져오기
//        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
//        if (centerMemberNo == null) {
//            model.addAttribute("errorMessage", "로그인이 필요합니다.");
//            return "redirect:/login"; // 로그인 페이지로 리디렉션
//        }
//
//        // DTO에 centerMemberNo 설정
//        centerUpdateProfileDTO.setCenterMemberNo(centerMemberNo);
//
//        boolean isUpdated = centerMypageService.updateCenterProfile(centerUpdateProfileDTO);
//
//        // 현재 비밀번호가 틀린 경우 오류 메시지를 모델에 추가하고 페이지 유지
//        if (!isUpdated) {
//            model.addAttribute("errorMessage", "현재 비밀번호가 일치하지 않습니다.");
//            return "redirect:/centerProfile";
//        }
//
//        // 업데이트 성공 시 확인 페이지 또는 메시지 추가
//        model.addAttribute("successMessage", "프로필이 성공적으로 업데이트되었습니다.");
//        return "redirect:/updateProfile";
//    }





//게시글 조회
    private final WriteInfoService writeInfoService;

    //커뮤니티 작성글 페이지
    @GetMapping("/writeInfoCommuList")
    public String centerWriteInfoCommuList(Model model) {
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo"); // 세션에서 센터회원 번호 가져오기

        if (centerMemberNo == null) {
            log.warn("세션에서 centerMemberNo가 존재하지 않습니다.");
            return "redirect:/login"; // 세션이 없으면 로그인 페이지로 리다이렉트
        }

        // 커뮤니티 작성글 조회
        List<WriteInfoCommuListDTO> commuWriteInfoList = writeInfoService.commuList(centerMemberNo);
        model.addAttribute("commuWriteInfoList", commuWriteInfoList);

        return "centermypage/center-mypage-writeinfo-my";
    }

    //봉사 모집글 페이지
    @GetMapping("/writeInfoVolList")
    public String centerWriteInfoVolList(Model model) {
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo"); // 세션에서 센터회원 번호 가져오기

        if (centerMemberNo == null) {
            log.warn("세션에서 centerMemberNo가 존재하지 않습니다.");
            return "redirect:/login"; // 세션이 없으면 로그인 페이지로 리다이렉트
        }

        // 커뮤니티 작성글 조회
        List<WriteInfoVolListDTO> volWriteInfoList = writeInfoService.volunList(centerMemberNo);
        model.addAttribute("volWriteInfoList", volWriteInfoList);

        return "centermypage/center-mypage-writeinfo-volun";
    }

    //안심입양 모집글 페이지
    @GetMapping("/writeInfoAdoptList")
    public String centerWriteInfoAdoptList(Model model) {
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo"); // 세션에서 센터회원 번호 가져오기

        if (centerMemberNo == null) {
            log.warn("세션에서 centerMemberNo가 존재하지 않습니다.");
            return "redirect:/login"; // 세션이 없으면 로그인 페이지로 리다이렉트
        }

        // 커뮤니티 작성글 조회
        List<WriteInfoAdoptListDTO> adoptWriteInfoList = writeInfoService.adoptList(centerMemberNo);
        model.addAttribute("adoptWriteInfoList", adoptWriteInfoList);

        return "centermypage/center-mypage-writeinfo-adopt";
    }


    private final RequestService requestService;

    //봉사 신청서 목록 조회
    @GetMapping("/volunRequestList")
    public String centerMypageVolunRequestList(Model model) {
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");

        if (centerMemberNo == null) {
            log.warn("세션에서 centerMemberNo가 존재하지 않습니다.");
            return "redirect:/login"; // 세션이 없으면 로그인 페이지로 리다이렉트
        }

        List<VolunRequestListDTO> volRequestList = requestService.volRequestList(centerMemberNo);
        model.addAttribute("volRequestList", volRequestList);

        return "centermypage/center-mypage-volun-list";
    }

    //입양 신청서 목록 조회
    @GetMapping("/adoptRequestList")
    public String centerMypageAdoptRequestList(Model model) {
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");

        if (centerMemberNo == null) {
            log.warn("세션에서 centerMemberNo가 존재하지 않습니다.");
            return "redirect:/login"; // 세션이 없으면 로그인 페이지로 리다이렉트
        }

        List<AdoptRequestListDTO> adoptRequestList = requestService.adoptRequestList(centerMemberNo);
        model.addAttribute("adoptRequestList", adoptRequestList);

        return "centermypage/center-mypage-adopt-adopt-list";
    }


    //임시보호 신청서 목록 조회
    @GetMapping("/protectRequestList")
    public String centerMypageProtectRequestList(Model model) {
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");

        if (centerMemberNo == null) {
            log.warn("세션에서 centerMemberNo가 존재하지 않습니다.");
            return "redirect:/login"; // 세션이 없으면 로그인 페이지로 리다이렉트
        }

        List<ProtectRequestListDTO> protectRequestList = requestService.protectRequestList(centerMemberNo);
        model.addAttribute("protectRequestList", protectRequestList);

        return "centermypage/center-mypage-adopt-protect-list";
    }
//    //임시보호 신청서 상태처리
//    @GetMapping("/adoptRequestDetail")







    //임시보호 신청서 상세 조회
    @GetMapping("/protectRequestDetail")
    public String getProtectRequestDetail(@RequestParam("protectRequestNo") Long protectRequestNo, Model model) {
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");

        if (centerMemberNo == null) {
            log.warn("세션에서 centerMemberNo가 존재하지 않습니다.");
            return "redirect:/login"; // 세션이 없으면 로그인 페이지로 리다이렉트
        }

        ProtectRequestDetailDTO protectRequestInfo = requestService.protectRequestDetail(protectRequestNo);
        model.addAttribute("protectRequestInfo", protectRequestInfo);

        return "centermypage/center-mypage-adopt-protectrequest";
    }

    //임시보호 신청서 상태처리
    @GetMapping("/protectStatus")
    public String centerMypageProtectStatus(@RequestParam("protectRequestStatus") String protectRequestStatus,
                                            @RequestParam("protectRequestNo") Long protectRequestNo, Model model) {
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
        if (centerMemberNo == null) {
            log.warn("세션에서 centerMemberNo가 존재하지 않습니다.");
            return "redirect:/login"; // 세션이 없으면 로그인 페이지로 리다이렉트
        }

        try {
            requestService.updateProtectRequestStatus(protectRequestNo, protectRequestStatus);
        } catch (Exception e) {
            log.error("요청 상태 업데이트 중 오류 발생: ", e);
            return "redirect:/error"; // 에러 페이지로 리다이렉트
        }

        return "redirect:/centerMypage/protectRequestList";
    }



//쪽지함
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

    // 보낸 쪽지 상세 페이지
    @GetMapping("/noteboxSendDetail")
    public String getNoteboxSendDetail(@RequestParam("noteboxSendNo") Long noteboxSendNo, Model model) {
        NoteboxSendDetailDTO noteboxSendDetail = noteBoxService.getNoteboxSendDetail(noteboxSendNo);

        // 쪽지 정보를 찾지 못한 경우
        if (noteboxSendDetail == null) {
            log.error("보낸 쪽지를 찾을 수 없습니다: {}", noteboxSendNo);
            return "redirect:/centerMypage/noteboxSendList"; // 쪽지를 찾지 못하면 목록으로 리다이렉트
        }

        model.addAttribute("noteboxSendDetail", noteboxSendDetail); // 모델에 쪽지 상세 정보 추가
        return "centermypage/notebox/center-mypage-notebox-senddetail"; // 상세 페이지의 템플릿 이름
    }

    // 보낸 쪽지 상세 페이지
    @GetMapping("/noteboxReceiveDetail")
    public String getNoteboxReceiveDetail(@RequestParam("noteboxReceiveNo") Long noteboxReceiveNo, Model model) {
        NoteboxReceiveDetailDTO noteboxReceiveDetail = noteBoxService.getNoteboxReceiveDetail(noteboxReceiveNo);

        // 쪽지 정보를 찾지 못한 경우
        if (noteboxReceiveDetail == null) {
            log.error("보낸 쪽지를 찾을 수 없습니다: {}", noteboxReceiveNo);
            return "redirect:/centerMypage/noteboxReceiveList"; // 쪽지를 찾지 못하면 목록으로 리다이렉트
        }

        model.addAttribute("noteboxReceiveDetail", noteboxReceiveDetail); // 모델에 쪽지 상세 정보 추가
        return "centermypage/notebox/center-mypage-notebox-receivedetail"; // 상세 페이지의 템플릿 이름
    }


    //쪽지보내기 페이지 이동
    @GetMapping(value = "/noteboxWrite")
    public String noteboxWrite(Model model, HttpSession session) {
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo"); // 세션에서 센터회원 번호 가져오기
        if (centerMemberNo == null) {
            log.error("세션에서 centerMemberNo를 찾을 수 없습니다.");
            return "redirect:/login"; // 세션이 없으면 로그인 페이지로 리다이렉트
        }

        // DTO 객체 생성 및 모델에 추가
        model.addAttribute("noteboxWriteDTO", new NoteboxWriteDTO());

        return "centermypage/notebox/center-mypage-notebox-write"; // HTML 파일 경로
    }

    //쪽지보내기 (답장) 페이지 이동
    @GetMapping("/noteboxResponse")
    public String writeNote(@RequestParam("noteboxSenderName") String noteboxSenderName, Model model, HttpSession session) {
        // 세션 체크 및 DTO 설정
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
        if (centerMemberNo == null) {
            log.error("세션에서 centerMemberNo를 찾을 수 없습니다.");
            return "redirect:/login";
        }

        NoteboxWriteDTO noteboxWriteDTO = new NoteboxWriteDTO();
        noteboxWriteDTO.setNoteboxReceiverName(noteboxSenderName); // senderName을 받는 사람 이름으로 설정
        model.addAttribute("noteboxWriteDTO", noteboxWriteDTO);

        return "centermypage/notebox/center-mypage-notebox-write";
    }

    // 쪽지 전송 실패
    @GetMapping("/notebox/sendFail")
    public String showSendNoteForm(Model model) {
        model.addAttribute("noteboxWriteDTO", new NoteboxWriteDTO());
        return "centermypage/notebox/center-mypage-notebox-write"; // 템플릿 경로
    }

    @PostMapping("/sendingNote")
    public String sendingNote(@ModelAttribute NoteboxWriteDTO noteboxWriteDTO, RedirectAttributes redirectAttributes) {
        // 세션에서 센터회원 번호 가져오기
        Long senderNo = (Long) session.getAttribute("centerMemberNo");

        if (senderNo == null) {
            redirectAttributes.addFlashAttribute("error", "발신자 정보를 찾을 수 없습니다.");
            return "redirect:/centerMypage/notebox/sendFail"; // 에러가 발생하면 다시 폼으로 돌아감
        }

        Long receiverNo;
        try {
            receiverNo = noteBoxService.findMemberNoByNickname(noteboxWriteDTO.getNoteboxReceiverName());
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            log.error("수신자 조회 실패: {}", e.getMessage());
            return "redirect:/centerMypage/notebox/sendFail"; // 에러가 발생하면 다시 폼으로 돌아감
        }

        if (receiverNo != null) {
            // DTO에 수신자 번호 및 발신자 번호 설정
            noteboxWriteDTO.setNoteboxR(receiverNo);
            noteboxWriteDTO.setNoteboxS(senderNo); // 발신자 번호 설정

            // 쪽지 전송
            noteBoxService.sendingNote(noteboxWriteDTO);
            log.info("쪽지가 성공적으로 전송되었습니다: {}", noteboxWriteDTO);
            return "redirect:/centerMypage/noteboxSendList"; // 보낸 쪽지함으로 리다이렉트
        } else {
            redirectAttributes.addFlashAttribute("error", "회원 번호를 찾을 수 없습니다.");
            log.error("회원 번호를 찾을 수 없습니다: {}", noteboxWriteDTO.getNoteboxReceiverName());
            return "redirect:/centerMypage/notebox/sendFail"; // 에러가 발생하면 다시 폼으로 돌아감
        }
    }


    @GetMapping("/noteboxDelete")
    public String deleteNote(@RequestParam("noteboxReceiveNo") Long noteboxReceiveNo, Model model) {
        boolean isDeleted = noteBoxService.deleteNoteByReceiveNo(noteboxReceiveNo);

        if (isDeleted) {
            log.info("쪽지 번호 {}가 성공적으로 삭제되었습니다.", noteboxReceiveNo);
            return "redirect:/centerMypage/noteboxReceiveList"; // 쪽지 리스트 페이지로 리다이렉트
        } else {
            log.error("쪽지 번호 {} 삭제 실패", noteboxReceiveNo);
            model.addAttribute("errorMessage", "쪽지 삭제에 실패했습니다.");
            return "centerMypage/noteboxError"; // 에러 페이지로 이동
        }
    }





}
