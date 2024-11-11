package com.example.hope_dog.controller.adopt;

import com.example.hope_dog.dto.adopt.adopt.*;
import com.example.hope_dog.dto.adopt.protect.*;
import com.example.hope_dog.dto.adopt.review.*;
import com.example.hope_dog.dto.page.Criteria;
import com.example.hope_dog.dto.page.Page;
import com.example.hope_dog.service.adopt.adopt.AdoptService;
import com.example.hope_dog.service.adopt.protect.ProtectService;
import com.example.hope_dog.service.adopt.review.ReviewService;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/adopt")
public class ReviewController {
    private final ReviewService reviewService;

    //    후기메인
    @GetMapping("/review")
    public String reviewList(Criteria criteria, Model model, HttpSession session){
        List<ReviewMainDTO> reviewMainList = reviewService.findAllPage(criteria);
        int total = reviewService.findTotal();
        Page page = new Page(criteria, total);
        Long memberNo = (Long) session.getAttribute("memberNo"); //이것도 무시 세션값 자겨와서 저장

        model.addAttribute("ReviewMainList", reviewMainList);
        model.addAttribute("page", page);
        model.addAttribute("memberNo", memberNo); //이건 나만 쓰는거 무시 세션값 html에서 쓸수있게 model추가
        model.addAttribute("All", true);

        return "adopt/review/adopt-review";
    }

    //후기상세글
    @GetMapping("/review/reviewdetail")
    public String reviewDetail(@RequestParam("reviewNo") Long reviewNo, Model model, HttpSession session) {
        List<ReviewDetailDTO> reviewDetailList = reviewService.getReviewDetail(reviewNo);
        List<ReviewCommentDTO> reviewCommentlList = reviewService.getReviewComment(reviewNo);
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
        Long memberNo = (Long) session.getAttribute("memberNo");

        model.addAttribute("reviewDetailList", reviewDetailList);
        model.addAttribute("reviewCommentList", reviewCommentlList);
        model.addAttribute("centerMemberNo", centerMemberNo);
        model.addAttribute("memberNo", memberNo);

        return "adopt/review/adopt-reviewdetail";
    }

    //후기글작성페이지이동
    @GetMapping("/review/reviewwrite")
    public String reviewWrite(HttpSession session, Model model) {
        // 세션에서 memberNo 가져오기
        Long memberNo = (Long) session.getAttribute("memberNo");

        // 모델에 memberNo 추가
        model.addAttribute("memberNo", memberNo);

        return "adopt/review/adopt-reviewwrite"; // 템플릿 이름
    }

    // 후기 글 등록 처리
    @PostMapping("/review/reviewWriteRegi")
    public String postReviewWrite(
            @DateTimeFormat(pattern = "yyyy-MM-dd") ReviewWriteDTO reviewWriteDTO,
            HttpSession session) {
        // 서비스 호출하여 데이터베이스에 저장
        System.out.println(reviewWriteDTO + "확인");
        reviewService.registerReviewtion(reviewWriteDTO);

        // 리다이렉트
        return "redirect:/adopt/review";
    }



    //후기 글수정페이지이동
    @GetMapping("/review/reviewmodify")
    public String reviewModify(@RequestParam("reviewNo") Long reviewNo, Model model, HttpSession session) {
        List<ReviewDetailDTO> reviewDetailList = reviewService.getReviewDetail(reviewNo);
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
        Long memberNo = (Long) session.getAttribute("memberNo");

        model.addAttribute("reviewDetailList", reviewDetailList);
        model.addAttribute("centerMemberNo", centerMemberNo);
        model.addAttribute("memberNo", memberNo);

        return "adopt/review/adopt-reviewmodify";
    }

    //후기글수정
    @PostMapping("/review/reviewModifyRegi")
    public String postReviewModifyRegi(
            @DateTimeFormat(pattern = "yyyy-MM-dd") ReviewWriteDTO reviewWriteDTO,
            HttpSession session) {
        // 서비스 호출하여 데이터베이스에 저장
        reviewService.reviewModify(reviewWriteDTO);

        System.out.println(reviewWriteDTO + "수정값확인");

        // 리다이렉트
        return "redirect:/adopt/review"; // 리다이렉트
    }

    //후기글삭제처리
    @GetMapping("/review/reviewDelete")
    public String reviewDelete(@RequestParam("reviewNo") Long reviewNo) {
        System.out.println(reviewNo + " 확인"); // adoptNo 확인 로그

        // AdoptDetailDTO 생성 및 adoptNo 설정
        ReviewDetailDTO reviewDetailDTO = new ReviewDetailDTO();
        reviewDetailDTO.setReviewNo(reviewNo);

        // 서비스 호출
        reviewService.reviewDelete(reviewDetailDTO);

        return "redirect:/adopt/review"; // 리다이렉트
    }

    // 후기 글 신고 처리
    @GetMapping("/review/reviewContentReport")
    public String ReviewContentReport(@RequestParam("reviewNo") Long reviewNo, @RequestParam("reportContent") String reportContent,
                                      ReviewReportDTO reviewReportDTO, HttpSession session,
                                      RedirectAttributes redirectAttributes) {
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
        Long memberNo = (Long) session.getAttribute("memberNo");

        reviewReportDTO.setReportContent(reportContent);
        reviewReportDTO.setReportContentNo(reviewNo);
        reviewReportDTO.setReportWriter(centerMemberNo != null ? centerMemberNo : memberNo);

        reviewService.reviewContentReport(reviewReportDTO);

        // 성공 메시지를 플래시 속성으로 추가
        redirectAttributes.addFlashAttribute("ContentreportSuccess", true);

        return "redirect:/adopt/review/reviewdetail?reviewNo=" + reviewNo;
    }
    

    //후기 댓글 등록
    @GetMapping("/review/reviewCommentRegi")
    public String reviewCommentRegi(ReviewCommentDTO reviewCommentDTO, HttpSession session) {
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
        Long memberNo = (Long) session.getAttribute("memberNo");

        reviewCommentDTO.setReviewCommentWriter(centerMemberNo != null ? centerMemberNo : memberNo);

        Long reviewNo = reviewCommentDTO.getReviewNo();

        reviewService.reviewCommentRegi(reviewCommentDTO);

        return "redirect:/adopt/review/reviewdetail?reviewNo=" + reviewNo;
    }

    //후기 댓글 수정
    @PostMapping("/review/reviewCommentModi")
    public String reviewCommentModi(ReviewCommentDTO reviewCommentDTO, HttpSession session,
                                     @RequestParam("reviewNo") Long reviewNo) {
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
        Long memberNo = (Long) session.getAttribute("memberNo");

        // 댓글 작성자 설정
        reviewCommentDTO.setReviewCommentWriter(centerMemberNo != null ? centerMemberNo : memberNo);

        // 댓글 수정 서비스 호출
        reviewService.reviewCommentModi(reviewCommentDTO);

        // 수정 후 상세 페이지로 리다이렉트
        return "redirect:/adopt/review/reviewdetail?reviewNo=" + reviewNo;
    }

    //후기 댓글 삭제
    @PostMapping("/review/reviewCommentDelete")
    public String reviewCommentDelete(ReviewCommentDTO reviewCommentDTO, HttpSession session,
                                       @RequestParam("reviewNo") Long reviewNo,
                                       @RequestParam("reviewCommentNo") Long reviewCommentNo) {
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
        Long memberNo = (Long) session.getAttribute("memberNo");

        // 댓글 작성자 설정
        reviewCommentDTO.setReviewCommentWriter(centerMemberNo != null ? centerMemberNo : memberNo);
        reviewCommentDTO.setReviewCommentNo(reviewCommentNo);

        // 댓글 삭제 서비스 호출
        reviewService.reviewCommentDelete(reviewCommentDTO);

        // 삭제 후 상세 페이지로 리다이렉트
        return "redirect:/adopt/review/reviewdetail?reviewNo=" + reviewNo;
    }

    //후기 댓글 신고
    @GetMapping("/review/reviewCommentReport")
    public String ReviewCommentReport(@RequestParam("reviewNo") Long reviewNo, @RequestParam("reportComment") String reportComment,
                                       @RequestParam("reviewCommentNo") Long reviewCommentNo,
                                       ReviewReportDTO reviewReportDTO, HttpSession session,
                                      RedirectAttributes redirectAttributes) {
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
        Long memberNo = (Long) session.getAttribute("memberNo");

        reviewReportDTO.setReportComment(reportComment);
        reviewReportDTO.setReportCommentNo(reviewCommentNo);
        reviewReportDTO.setReportContentNo(reviewNo);
        reviewReportDTO.setReportWriter(centerMemberNo != null ? centerMemberNo : memberNo);

        reviewService.reviewCommentReport(reviewReportDTO);

        // 성공 메시지를 플래시 속성으로 추가
        redirectAttributes.addFlashAttribute("CommentreportSuccess", true);

        return "redirect:/adopt/review/reviewdetail?reviewNo=" + reviewNo;
    }
}
