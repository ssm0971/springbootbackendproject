package com.example.hope_dog.controller.adopt;

import com.example.hope_dog.dto.adopt.adopt.*;
import com.example.hope_dog.dto.adopt.protect.*;
import com.example.hope_dog.dto.page.Criteria;
import com.example.hope_dog.dto.page.Page;
import com.example.hope_dog.service.adopt.protect.ProtectService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/adopt")
public class ProtectController {
    private final ProtectService protectService;

    //    임시보호메인
    @GetMapping("/protect")
    public String protectList(Criteria criteria, Model model, HttpSession session){
        List<ProtectMainDTO> protectMainList = protectService.findAllPage(criteria);
        int total = protectService.findTotal();
        Page page = new Page(criteria, total);
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo"); //이것도 무시 세션값 자겨와서 저장

        List<ProtectMainDTO> centerMemberStatus = protectService.centerMemberStatus(centerMemberNo);

        model.addAttribute("ProtectMainList", protectMainList);
        model.addAttribute("centerMemberStatus", centerMemberStatus);
        model.addAttribute("page", page);
        model.addAttribute("centerMemberNo", centerMemberNo); //이건 나만 쓰는거 무시 세션값 html에서 쓸수있게 model추가
        model.addAttribute("All", true);

        return "adopt/protect/adopt-protect";
    }

    //임시보호 모집중인 게시글
    @GetMapping("/protectKeep")
    public String protectListKeep(Criteria criteria, Model model, HttpSession session){
        List<ProtectMainDTO> protectMainList = protectService.findAllPageKeep(criteria);
        int total = protectService.findTotalKeep();
        Page page = new Page(criteria, total);
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo"); //이것도 무시 세션값 자겨와서 저장

        model.addAttribute("ProtectMainList", protectMainList);
        model.addAttribute("page", page);
        model.addAttribute("centerMemberNo", centerMemberNo); //이건 나만 쓰는거 무시 세션값 html에서 쓸수있게 model추가
        model.addAttribute("Keep", true);

        return "adopt/protect/adopt-protect";
    }

    //임시보호상세글
    @GetMapping("/protect/protectdetail")
    public String protectDetail(@RequestParam("protectNo") Long protectNo, Model model, HttpSession session) {
        List<ProtectDetailDTO> protectDetailList = protectService.getProtectDetail(protectNo);
        List<ProtectCommentDTO> protectCommentlList = protectService.getProtectComment(protectNo);
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
        Long memberNo = (Long) session.getAttribute("memberNo");

        model.addAttribute("protectDetailList", protectDetailList);
        model.addAttribute("protectCommentList", protectCommentlList);
        model.addAttribute("centerMemberNo", centerMemberNo);
        model.addAttribute("memberNo", memberNo);

        return "adopt/protect/adopt-protectdetail";
    }

    //임시보호글작성페이지이동
    @GetMapping("/protect/protectwrite")
    public String protectWrite(HttpSession session, Model model) {
        // 세션에서 memberNo 가져오기
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");

        // 모델에 memberNo 추가
        model.addAttribute("centerMemberNo", centerMemberNo);

        return "adopt/protect/adopt-protectwrite"; // 템플릿 이름
    }

    // 임시보호 글 등록 처리
    @PostMapping("/protect/protectWriteRegi")
    public String postProtectWrite(
            @DateTimeFormat(pattern = "yyyy-MM-dd") ProtectWriteDTO protectWriteDTO,
            HttpSession session) {
        // 서비스 호출하여 데이터베이스에 저장
        protectService.registerProtection(protectWriteDTO);

        // 리다이렉트
        return "redirect:/adopt/protect";
    }

    //임시보호글마감처리
    @GetMapping("/protect/protectStatusEnd")
    public String protectEnd(@RequestParam("protectNo") Long protectNo) {
        System.out.println(protectNo + " 확인"); // adoptNo 확인 로그

        // AdoptDetailDTO 생성 및 adoptNo 설정
        ProtectDetailDTO protectDetailDTO = new ProtectDetailDTO();
        protectDetailDTO.setProtectNo(protectNo);

        // 서비스 호출
        protectService.protectEnd(protectDetailDTO);

        return "redirect:/adopt/protect/protectdetail?protectNo=" + protectNo;
    }

    //임시보호글삭제처리
    @GetMapping("/protect/protectDelete")
    public String protectDelete(@RequestParam("protectNo") Long protectNo) {
        System.out.println(protectNo + " 확인"); // adoptNo 확인 로그

        // AdoptDetailDTO 생성 및 adoptNo 설정
        ProtectDetailDTO protectDetailDTO = new ProtectDetailDTO();
        protectDetailDTO.setProtectNo(protectNo);

        // 서비스 호출
        protectService.protectDelete(protectDetailDTO);

        return "redirect:/adopt/protect"; // 리다이렉트
    }

    //임시보호글수정페이지이동
    @GetMapping("/protect/protectmodify")
    public String protectModify(@RequestParam("protectNo") Long protectNo, Model model, HttpSession session) {
        List<ProtectDetailDTO> protectDetailList = protectService.getProtectDetail(protectNo);
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
        Long memberNo = (Long) session.getAttribute("memberNo");

        model.addAttribute("protectDetailList", protectDetailList);
        model.addAttribute("centerMemberNo", centerMemberNo);
        model.addAttribute("memberNo", memberNo);

        return "adopt/protect/adopt-protectmodify";
    }

    //임시보호글수정
    @PostMapping("/protect/protectModifyRegi")
    public String postProtectModifyRegi(
            @DateTimeFormat(pattern = "yyyy-MM-dd") ProtectWriteDTO protectWriteDTO,
            HttpSession session) {
        // 서비스 호출하여 데이터베이스에 저장
        protectService.protectModify(protectWriteDTO);

        // 리다이렉트
        return "redirect:/adopt/protect"; // 리다이렉트
    }

    // 임시보호 글 신고 처리
    @GetMapping("/protect/protectContentReport")
    public String ProtectContentReport(@RequestParam("protectNo") Long protectNo, @RequestParam("reportContent") String reportContent,
                                       ProtectReportDTO protectReportDTO, HttpSession session,
                                       RedirectAttributes redirectAttributes) {
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
        Long memberNo = (Long) session.getAttribute("memberNo");

        protectReportDTO.setReportContent(reportContent);
        protectReportDTO.setReportContentNo(protectNo);
        protectReportDTO.setReportWriter(centerMemberNo != null ? centerMemberNo : memberNo);

        protectService.protectContentReport(protectReportDTO);

        // 성공 메시지를 플래시 속성으로 추가
        redirectAttributes.addFlashAttribute("ContentreportSuccess", true);

        return "redirect:/adopt/protect/protectdetail?protectNo=" + protectNo;
    }

    // 임시보호 신청서 페이지 열기
    @GetMapping("/protect/protectrequest")
    public String protectRequest(@RequestParam(value = "centerMemberNo") Long centerMemberNo,
                               HttpSession session, Model model, @RequestParam("protectNo") Long protectNo) {
        Long memberNo = (Long) session.getAttribute("memberNo");

        model.addAttribute("protectNo", protectNo);
        model.addAttribute("memberNo", memberNo);
        model.addAttribute("centerMemberNo", centerMemberNo); // 이 부분이 중요합니다.

        return "adopt/protect/adopt-protectrequest";
    }

    // 임시보호 신청서 등록
    @PostMapping("/protect/protectrequestRegi")
    public String protectRequestRegi(@DateTimeFormat(pattern = "yyyy-MM-dd") ProtectRequestDTO protectRequestDTO,
                                     RedirectAttributes redirectAttributes) {
        protectService.registerRequest(protectRequestDTO);

        Long protectNo = protectRequestDTO.getProtectNo();

        // 성공 메시지를 플래시 속성으로 추가
        redirectAttributes.addFlashAttribute("requestSuccess", true);

        return "redirect:/adopt/protect/protectdetail?protectNo=" + protectNo;
    }

    //임시보호 댓글 등록
    @GetMapping("/protect/protectCommentRegi")
    public String protectCommentRegi(ProtectCommentDTO protectCommentDTO, HttpSession session) {
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
        Long memberNo = (Long) session.getAttribute("memberNo");

        protectCommentDTO.setProtectCommentWriter(centerMemberNo != null ? centerMemberNo : memberNo);

        Long protectNo = protectCommentDTO.getProtectNo();

        protectService.protectCommentRegi(protectCommentDTO);

        return "redirect:/adopt/protect/protectdetail?protectNo=" + protectNo;
    }

    //임시보호 댓글 수정
    @PostMapping("/protect/protectCommentModi")
    public String protectCommentModi(ProtectCommentDTO protectCommentDTO, HttpSession session,
                                   @RequestParam("protectNo") Long protectNo) {
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
        Long memberNo = (Long) session.getAttribute("memberNo");

        // 댓글 작성자 설정
        protectCommentDTO.setProtectCommentWriter(centerMemberNo != null ? centerMemberNo : memberNo);

        // 댓글 수정 서비스 호출
        protectService.protectCommentModi(protectCommentDTO);

        // 수정 후 상세 페이지로 리다이렉트
        return "redirect:/adopt/protect/protectdetail?protectNo=" + protectNo;
    }

    //임시보호 댓글 삭제
    @PostMapping("/protect/protectCommentDelete")
    public String protectCommentDelete(ProtectCommentDTO protectCommentDTO, HttpSession session,
                                     @RequestParam("protectNo") Long protectNo,
                                     @RequestParam("protectCommentNo") Long protectCommentNo) {
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
        Long memberNo = (Long) session.getAttribute("memberNo");

        // 댓글 작성자 설정
        protectCommentDTO.setProtectCommentWriter(centerMemberNo != null ? centerMemberNo : memberNo);
        protectCommentDTO.setProtectCommentNo(protectCommentNo);

        // 댓글 삭제 서비스 호출
        protectService.protectCommentDelete(protectCommentDTO);

        // 삭제 후 상세 페이지로 리다이렉트
        return "redirect:/adopt/protect/protectdetail?protectNo=" + protectNo;
    }

    //임시보호 댓글 신고
    @GetMapping("/protect/protectCommentReport")
    public String ProtectCommentReport(@RequestParam("protectNo") Long protectNo, @RequestParam("reportComment") String reportComment,
                                     @RequestParam("protectCommentNo") Long protectCommentNo,
                                       ProtectReportDTO protectReportDTO, HttpSession session,
                                       RedirectAttributes redirectAttributes) {
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
        Long memberNo = (Long) session.getAttribute("memberNo");

        protectReportDTO.setReportComment(reportComment);
        protectReportDTO.setReportCommentNo(protectCommentNo);
        protectReportDTO.setReportContentNo(protectNo);
        protectReportDTO.setReportWriter(centerMemberNo != null ? centerMemberNo : memberNo);

        protectService.protectCommentReport(protectReportDTO);

        // 성공 메시지를 플래시 속성으로 추가
        redirectAttributes.addFlashAttribute("CommentreportSuccess", true);

        return "redirect:/adopt/protect/protectdetail?protectNo=" + protectNo;
    }
}
