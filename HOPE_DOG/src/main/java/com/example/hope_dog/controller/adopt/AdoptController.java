package com.example.hope_dog.controller.adopt;

import com.example.hope_dog.dto.adopt.adopt.*;
import com.example.hope_dog.dto.page.Criteria;
import com.example.hope_dog.dto.page.Page;
import com.example.hope_dog.service.adopt.adopt.AdoptService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/adopt")    //시작도메인 localhost:8060/adopt
public class AdoptController {
    private final AdoptService adoptService;

    //입양/임보/후기 메인페이지
    @GetMapping("/main")        //열릴페도메인 localhost:8060/adopt/main
    public String Main(Model model) {
        List<MainDTO> MainList = adoptService.getMainList();

        model.addAttribute("MainList", MainList);
        return "adopt/adopt-main";  //localhost:8060/adopt/main로 접속했을시 열릴 html
    }

    //    입양메인
    @GetMapping("/adopt")
    public String adoptList(Criteria criteria, Model model, HttpSession session){
        List<AdoptMainDTO> adoptMainList = adoptService.findAllPage(criteria);
        int total = adoptService.findTotal();
        Page page = new Page(criteria, total);
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo"); //이것도 무시 세션값 자겨와서 저장

        model.addAttribute("AdoptMainList", adoptMainList);
        model.addAttribute("page", page);
        model.addAttribute("centerMemberNo", centerMemberNo); //이건 나만 쓰는거 무시 세션값 html에서 쓸수있게 model추가
        model.addAttribute("All", true);

        return "adopt/adopt/adopt-adopt";
    }

    //입양 모집중인 게시글
    @GetMapping("/adoptKeep")
    public String adoptListKeep(Criteria criteria, Model model, HttpSession session){
        List<AdoptMainDTO> adoptMainList = adoptService.findAllPageKeep(criteria);
        int total = adoptService.findTotalKeep();
        Page page = new Page(criteria, total);
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo"); //이것도 무시 세션값 자겨와서 저장

        model.addAttribute("AdoptMainList", adoptMainList);
        model.addAttribute("page", page);
        model.addAttribute("centerMemberNo", centerMemberNo); //이건 나만 쓰는거 무시 세션값 html에서 쓸수있게 model추가
        model.addAttribute("Keep", true);

        return "adopt/adopt/adopt-adopt";
    }

    //입양상세글
    @GetMapping("/adopt/adoptdetail")
    public String adoptDetail(@RequestParam("adoptNo") Long adoptNo, Model model, HttpSession session) {
        List<AdoptDetailDTO> adoptDetailList = adoptService.getAdoptDetail(adoptNo);
        List<AdoptCommentDTO> adoptCommentlList = adoptService.getAdoptComment(adoptNo);
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
        Long memberNo = (Long) session.getAttribute("memberNo");

        model.addAttribute("adoptDetailList", adoptDetailList);
        model.addAttribute("adoptCommentList", adoptCommentlList);
        model.addAttribute("centerMemberNo", centerMemberNo);
        model.addAttribute("memberNo", memberNo);

        return "adopt/adopt/adopt-adoptdetail";
    }

    //입양글작성페이지이동
    @GetMapping("/adopt/adoptwrite")
    public String adoptWrite(HttpSession session, Model model) {
        // 세션에서 memberNo 가져오기
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");

        // 모델에 memberNo 추가
        model.addAttribute("centerMemberNo", centerMemberNo);

        return "adopt/adopt/adopt-adoptwrite"; // 템플릿 이름
    }

    // 입양 글 등록 처리
    @PostMapping("/adopt/adoptWriteRegi")
    public String postAdoptWrite(
            @DateTimeFormat(pattern = "yyyy-MM-dd") AdoptWriteDTO adoptWriteDTO,
            HttpSession session) {
        // 서비스 호출하여 데이터베이스에 저장
        adoptService.registerAdoption(adoptWriteDTO);

        // 리다이렉트
        return "redirect:/adopt/adopt";
    }

    //입양글마감처리
    @GetMapping("/adopt/adoptStatusEnd")
    public String adoptEnd(@RequestParam("adoptNo") Long adoptNo) {
        System.out.println(adoptNo + " 확인"); // adoptNo 확인 로그

        // AdoptDetailDTO 생성 및 adoptNo 설정
        AdoptDetailDTO adoptDetailDTO = new AdoptDetailDTO();
        adoptDetailDTO.setAdoptNo(adoptNo);

        // 서비스 호출
        adoptService.adoptEnd(adoptDetailDTO);

//        return "redirect:/adopt/adopt"; // 리다이렉트
        return "redirect:/adopt/adopt/adoptdetail?adoptNo=" + adoptNo;
    }

    //입양글삭제처리
    @GetMapping("/adopt/adoptDelete")
    public String adoptDelete(@RequestParam("adoptNo") Long adoptNo) {
        System.out.println(adoptNo + " 확인"); // adoptNo 확인 로그

        // AdoptDetailDTO 생성 및 adoptNo 설정
        AdoptDetailDTO adoptDetailDTO = new AdoptDetailDTO();
        adoptDetailDTO.setAdoptNo(adoptNo);

        // 서비스 호출
        adoptService.adoptDelete(adoptDetailDTO);

        return "redirect:/adopt/adopt"; // 리다이렉트
    }

    // 입양 글 신고 처리
    @GetMapping("/adopt/adoptContentReport")
    public String AdoptContentReport(@RequestParam("adoptNo") Long adoptNo, @RequestParam("reportContent") String reportContent,
                                  AdoptReportDTO adoptReportDTO, HttpSession session) {
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
        Long memberNo = (Long) session.getAttribute("memberNo");

        adoptReportDTO.setReportContent(reportContent);
        adoptReportDTO.setReportContentNo(adoptNo);
        adoptReportDTO.setReportWriter(centerMemberNo != null ? centerMemberNo : memberNo);

        adoptService.adoptContentReport(adoptReportDTO);

        return "redirect:/adopt/adopt/adoptdetail?adoptNo=" + adoptNo;
    }

    //입양글수정
    @GetMapping("/adopt/adoptmodify")
    public String adoptModify() {
        return "adopt/adopt/adopt-adoptmodify";
    }

    // 입양 신청서 페이지 열기
    @GetMapping("/adopt/adoptrequest")
    public String adoptRequest(@RequestParam(value = "centerMemberNo") Long centerMemberNo,
                               HttpSession session, Model model) {
        Long memberNo = (Long) session.getAttribute("memberNo");

        model.addAttribute("memberNo", memberNo);
        model.addAttribute("centerMemberNo", centerMemberNo); // 이 부분이 중요합니다.

        return "adopt/adopt/adopt-adoptrequest";
    }

    // 입양 신청서 등록
    @PostMapping("/adopt/adoptrequestRegi")
    public String adoptRequestRegi(@DateTimeFormat(pattern = "yyyy-MM-dd") AdoptRequestDTO adoptRequestDTO) {
        adoptService.registerRequest(adoptRequestDTO);
        return "redirect:/adopt/adopt";
    }
    
    //입양 댓글 등록
    @GetMapping("/adopt/adoptCommentRegi")
    public String adoptCommentRegi(AdoptCommentDTO adoptCommentDTO, HttpSession session) {
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
        Long memberNo = (Long) session.getAttribute("memberNo");

        adoptCommentDTO.setAdoptCommentWriter(centerMemberNo != null ? centerMemberNo : memberNo);

        Long adoptNo = adoptCommentDTO.getAdoptNo();

        adoptService.adoptCommentRegi(adoptCommentDTO);

        return "redirect:/adopt/adopt/adoptdetail?adoptNo=" + adoptNo;
    }

    //입양 댓글 수정
    @PostMapping("/adopt/adoptCommentModi")
    public String adoptCommentModi(AdoptCommentDTO adoptCommentDTO, HttpSession session,
                                   @RequestParam("adoptNo") Long adoptNo) {
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
        Long memberNo = (Long) session.getAttribute("memberNo");

        // 댓글 작성자 설정
        adoptCommentDTO.setAdoptCommentWriter(centerMemberNo != null ? centerMemberNo : memberNo);

        // 댓글 수정 서비스 호출
        adoptService.adoptCommentModi(adoptCommentDTO);

        // 수정 후 상세 페이지로 리다이렉트
        return "redirect:/adopt/adopt/adoptdetail?adoptNo=" + adoptNo;
    }

    //입양 댓글 삭제
    @PostMapping("/adopt/adoptCommentDelete")
    public String adoptCommentDelete(AdoptCommentDTO adoptCommentDTO, HttpSession session,
                                     @RequestParam("adoptNo") Long adoptNo,
                                     @RequestParam("adoptCommentNo") Long adoptCommentNo) {
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
        Long memberNo = (Long) session.getAttribute("memberNo");

        // 댓글 작성자 설정
        adoptCommentDTO.setAdoptCommentWriter(centerMemberNo != null ? centerMemberNo : memberNo);
        adoptCommentDTO.setAdoptCommentNo(adoptCommentNo);

        // 댓글 삭제 서비스 호출
        adoptService.adoptCommentDelete(adoptCommentDTO);

        // 삭제 후 상세 페이지로 리다이렉트
        return "redirect:/adopt/adopt/adoptdetail?adoptNo=" + adoptNo;
    }

    //입양 댓글 신고
    @GetMapping("/adopt/adoptCommentReport")
    public String AdoptCommentReport(@RequestParam("adoptNo") Long adoptNo, @RequestParam("reportComment") String reportComment,
                                     @RequestParam("adoptCommentNo") Long adoptCommentNo,
                                  AdoptReportDTO adoptReportDTO, HttpSession session) {
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
        Long memberNo = (Long) session.getAttribute("memberNo");

        adoptReportDTO.setReportComment(reportComment);
        adoptReportDTO.setReportCommentNo(adoptCommentNo);
        adoptReportDTO.setReportContentNo(adoptNo);
        adoptReportDTO.setReportWriter(centerMemberNo != null ? centerMemberNo : memberNo);

        adoptService.adoptCommentReport(adoptReportDTO);

        return "redirect:/adopt/adopt/adoptdetail?adoptNo=" + adoptNo;
    }


    //후기메인
    @GetMapping("/review")
    public String reviewMain() {
        return "adopt/review/adopt-review";
    }



}
