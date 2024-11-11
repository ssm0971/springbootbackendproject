package com.example.hope_dog.controller.volun.volun;

import com.example.hope_dog.dto.adopt.adopt.*;
import com.example.hope_dog.dto.page.Criteria;
import com.example.hope_dog.dto.page.Page;
import com.example.hope_dog.dto.volun.volun.*;
import com.example.hope_dog.service.volun.volun.VolunService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/volun")
public class VolunController {
    private final VolunService volunService;

    //봉사열기
    @GetMapping("/main")        //열릴페도메인 localhost:8060/adopt/main
    public String VolunMain(Model model) {
        List<VolunMainDTO> VolunMainList = volunService.getVolunMainList();

        System.out.println(VolunMainList + "확인");

        model.addAttribute("VolunMainList", VolunMainList);
        return "volun/volun/volun-main";  //localhost:8060/adopt/main로 접속했을시 열릴 html
    }

    //  봉사 전체게시글
    @GetMapping("/volun")
    public String volunList(Criteria criteria, Model model, HttpSession session){
        List<VolunMainDTO> volunMainList = volunService.findAllPage(criteria);
        int total = volunService.findTotal();
        Page page = new Page(criteria, total);
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo"); //이것도 무시 세션값 자겨와서 저장

        List<VolunMainDTO> centerMemberStatus = volunService.centerMemberStatus(centerMemberNo);

        model.addAttribute("VolunMainList", volunMainList);
        model.addAttribute("centerMemberStatus", centerMemberStatus);
        model.addAttribute("page", page);
        model.addAttribute("centerMemberNo", centerMemberNo); //이건 나만 쓰는거 무시 세션값 html에서 쓸수있게 model추가
        model.addAttribute("All", true);
        return "volun/volun/volun-volun-main";
    }

    //봉사 모집중인 게시글
    @GetMapping("/volunKeep")
    public String volunListKeep(Criteria criteria, Model model, HttpSession session){
        List<VolunMainDTO> volunMainList = volunService.findAllPageKeep(criteria);
        int total = volunService.findTotalKeep();
        Page page = new Page(criteria, total);
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo"); //이것도 무시 세션값 자겨와서 저장

        model.addAttribute("VolunMainList", volunMainList);
        model.addAttribute("page", page);
        model.addAttribute("centerMemberNo", centerMemberNo); //이건 나만 쓰는거 무시 세션값 html에서 쓸수있게 model추가
        model.addAttribute("Keep", true);

        return "volun/volun/volun-volun-main";
    }

    //봉사상세글
    @GetMapping("/volun/volundetail")
    public String volunDetail(@RequestParam("volunNo") Long volunNo, Model model, HttpSession session) {
        List<VolunDetailDTO> volunDetailList = volunService.getVolunDetail(volunNo);
        List<VolunCommentDTO> volunCommentlList = volunService.getVolunComment(volunNo);
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
        Long memberNo = (Long) session.getAttribute("memberNo");

        model.addAttribute("volunDetailList", volunDetailList);
        model.addAttribute("volunCommentList", volunCommentlList);
        model.addAttribute("centerMemberNo", centerMemberNo);
        model.addAttribute("memberNo", memberNo);

        return "volun/volun/volun-volundetail";
    }

    //봉사글작성페이지이동
    @GetMapping("/volunwrite")
    public String adoptWrite(HttpSession session, Model model) {
        // 세션에서 memberNo 가져오기
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");

        // 모델에 memberNo 추가
        model.addAttribute("centerMemberNo", centerMemberNo);

        return "volun/volun/volun-volunwrite"; // 템플릿 이름
    }

    // 봉사 글 등록 처리
    @PostMapping("/volun/volunWriteRegi")
    public String postVolunWrite(
            @DateTimeFormat(pattern = "yyyy-MM-dd") VolunWriteDTO volunWriteDTO,
            HttpSession session) {
        // 서비스 호출하여 데이터베이스에 저장
        volunService.registerVolun(volunWriteDTO);

        // 리다이렉트
        return "redirect:/volun/volun";
    }

    //봉사글삭제처리
    @GetMapping("/volun/volunDelete")
    public String volunDelete(@RequestParam("volunNo") Long volunNo) {

        // AdoptDetailDTO 생성 및 adoptNo 설정
        VolunDetailDTO volunDetailDTO = new VolunDetailDTO();
        volunDetailDTO.setVolunNo(volunNo);

        // 서비스 호출
        volunService.volunDelete(volunDetailDTO);

        return "redirect:/volun/volun"; // 리다이렉트
    }

    //봉사글수정페이지이동
    @GetMapping("/volun/volunmodify")
    public String volunModify(@RequestParam("volunNo") Long volunNo, Model model, HttpSession session) {
        List<VolunDetailDTO> volunDetailList = volunService.getVolunDetail(volunNo);
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");

        model.addAttribute("volunDetailList", volunDetailList);
        model.addAttribute("centerMemberNo", centerMemberNo);

        return "volun/volun/volun-volunmodify";
    }

    //봉사글 수정
    @PostMapping("/volun/volunModifyRegi")
    public String postVolunModifyRegi(
            @DateTimeFormat(pattern = "yyyy-MM-dd") VolunWriteDTO volunWriteDTO,
            HttpSession session) {
        // 서비스 호출하여 데이터베이스에 저장
        volunService.volunModify(volunWriteDTO);

        // 리다이렉트
        return "redirect:/volun/volun"; // 리다이렉트
    }

    //봉사글마감처리
    @GetMapping("/volun/volunStatusEnd")
    public String adoptEnd(@RequestParam("volunNo") Long volunNo) {

        VolunDetailDTO volunDetailDTO = new VolunDetailDTO();
        volunDetailDTO.setVolunNo(volunNo);

        // 서비스 호출
        volunService.volunEnd(volunDetailDTO);

        return "redirect:/volun/volun/volundetail?volunNo=" + volunNo;
    }

    // 봉사 글 신고 처리
    @GetMapping("/volun/volunContentReport")
    public String AdoptContentReport(@RequestParam("volunNo") Long volunNo, @RequestParam("reportContent") String reportContent,
                                     VolunReportDTO volunReportDTO, HttpSession session,
                                     RedirectAttributes redirectAttributes) {
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
        Long memberNo = (Long) session.getAttribute("memberNo");

        volunReportDTO.setReportContent(reportContent);
        volunReportDTO.setReportContentNo(volunNo);
        volunReportDTO.setReportWriter(centerMemberNo != null ? centerMemberNo : memberNo);

        volunService.volunContentReport(volunReportDTO);

        // 게시글 신고 메시지를 플래시 속성으로 추가
        redirectAttributes.addFlashAttribute("ContentreportSuccess", true);

        return "redirect:/volun/volun/volundetail?volunNo=" + volunNo;
    }

    // 봉사 신청서 페이지 열기
    @GetMapping("/volun/volunrequest")
    public String volunRequest(@RequestParam(value = "centerMemberNo") Long centerMemberNo,
                               HttpSession session, Model model, @RequestParam("volunNo") Long volunNo) {
        Long memberNo = (Long) session.getAttribute("memberNo");

        model.addAttribute("volunNo", volunNo);
        model.addAttribute("memberNo", memberNo);
        model.addAttribute("centerMemberNo", centerMemberNo); // 이 부분이 중요합니다.

        return "volun/volun/volun-volunrequest";
    }

    // 봉사 신청서 등록
    @PostMapping("/volun/volunrequestRegi")
    public String volunRequestRegi(@DateTimeFormat(pattern = "yyyy-MM-dd") VolunRequestDTO volunRequestDTO,
                                   RedirectAttributes redirectAttributes) {
        volunService.registerRequest(volunRequestDTO);

        Long volunNo = volunRequestDTO.getVolunNo();

        // 성공 메시지를 플래시 속성으로 추가
        redirectAttributes.addFlashAttribute("requestSuccess", true);

        return "redirect:/volun/volun/volundetail?volunNo=" + volunNo;
    }

    //봉사 댓글 등록
    @GetMapping("/volun/volunCommentRegi")
    public String adoptCommentRegi(VolunCommentDTO volunCommentDTO, HttpSession session) {
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
        Long memberNo = (Long) session.getAttribute("memberNo");

        volunCommentDTO.setVolunCommentWriter(centerMemberNo != null ? centerMemberNo : memberNo);

        Long volunNo = volunCommentDTO.getVolunNo();

        volunService.volunCommentRegi(volunCommentDTO);

        return "redirect:/volun/volun/volundetail?volunNo=" + volunNo;
    }

    //봉사 댓글 수정
    @PostMapping("/volun/volunCommentModi")
    public String volunCommentModi(VolunCommentDTO volunCommentDTO, HttpSession session,
                                   @RequestParam("volunNo") Long volunNo) {
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
        Long memberNo = (Long) session.getAttribute("memberNo");

        // 댓글 작성자 설정
        volunCommentDTO.setVolunCommentWriter(centerMemberNo != null ? centerMemberNo : memberNo);

        // 댓글 수정 서비스 호출
        volunService.volunCommentModi(volunCommentDTO);

        // 수정 후 상세 페이지로 리다이렉트
        return "redirect:/volun/volun/volundetail?volunNo=" + volunNo;
    }

    //봉사댓글삭제 딜리트매핑사용
    @DeleteMapping("/volun/volunCommentDelete")
    public ResponseEntity<String> volunCommentDelete(
            VolunCommentDTO volunCommentDTO, HttpSession session,
            @RequestParam("volunNo") Long volunNo,
            @RequestParam("volunCommentNo") Long volunCommentNo) {

        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
        Long memberNo = (Long) session.getAttribute("memberNo");

        volunCommentDTO.setVolunCommentWriter(centerMemberNo != null ? centerMemberNo : memberNo);
        volunCommentDTO.setVolunCommentNo(volunCommentNo);

        volunService.volunCommentDelete(volunCommentDTO);

        return ResponseEntity.ok("댓글이 삭제되었습니다."); // JSON 응답 반환
    }

    //봉사 댓글 신고
    @GetMapping("/volun/volunCommentReport")
    public String AdoptCommentReport(@RequestParam("volunNo") Long volunNo, @RequestParam("reportComment") String reportComment,
                                     @RequestParam("volunCommentNo") Long volunCommentNo,
                                     VolunReportDTO volunReportDTO, HttpSession session,
                                     RedirectAttributes redirectAttributes) {
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
        Long memberNo = (Long) session.getAttribute("memberNo");

        volunReportDTO.setReportComment(reportComment);
        volunReportDTO.setReportCommentNo(volunCommentNo);
        volunReportDTO.setReportContentNo(volunNo);
        volunReportDTO.setReportWriter(centerMemberNo != null ? centerMemberNo : memberNo);

        volunService.volunCommentReport(volunReportDTO);

        // 성공 메시지를 플래시 속성으로 추가
        redirectAttributes.addFlashAttribute("CommentreportSuccess", true);

        return "redirect:/volun/volun/volundetail?volunNo=" + volunNo;
    }
}
