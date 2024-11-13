package com.example.hope_dog.controller.donation;

import com.example.hope_dog.dto.donation.*;
import com.example.hope_dog.mapper.donation.DonationMapper;
import com.example.hope_dog.service.donation.DonaCommentService;
import com.example.hope_dog.service.donation.DonationService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/dona")
public class DonationController {

    // 의존성 주입: DonationService와 DonationMapper를 private final로 선언하여 주입받음
    private final DonationService donationService;
    private final DonationMapper donationMapper;
    private final DonaCommentService donaCommentService;

    // 글 목록 조회 (페이징 처리)
    @GetMapping("/list")
    public String donationList(Model model, HttpSession session) {
        log.info("donationList");
        // 페이징 처리된 기부 목록 가져오기
        List<DonationListDTO> donationList = donationService.donationList();

//        // 전체 기부글의 수 가져오기
//        int total = donationService.findTotal();
//
//        // 페이지 정보를 담은 객체 생성 (현재 페이지와 전체 페이지 수)
//        Page page = new Page(criteria, total);

        // 세션에서 센터 회원 번호를 가져옴
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");

        // 모델에 세션 값과 기부 목록, 페이지 정보를 추가
        model.addAttribute("centerMemberNo", centerMemberNo);
        model.addAttribute("donationList", donationList);
//        model.addAttribute("page", page);

        // 기부 목록 페이지로 이동
        return "donation/donation-main-center";
    }

    // 기부 글 검색
    @GetMapping("/list/search")
    public String search(@RequestParam(value = "searchType") String searchType,
                         @RequestParam(value = "keyword") String keyword,
                         Model model) {
        // 검색 조건에 맞는 결과 가져오기
        List<DonaDetailDTO> donationList = null;

        // 검색 조건에 따라 다른 방식으로 처리
        if ("donaTitle".equals(searchType)) {
            // 제목으로 검색
            donationList = donationService.donaSearch(keyword, null, null);
        } else if ("donaContent".equals(searchType)) {
            // 내용으로 검색
            donationList = donationService.donaSearch(null, keyword, null);
        } else if ("centerMemberName".equals(searchType)) {
            // 센터명으로 검색
            donationList = donationService.donaSearch(null, null, keyword);
        }

        // 검색 결과를 모델에 추가
        model.addAttribute("donationList", donationList);
        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);

        // 검색 결과를 기부 목록 페이지로 반환
        return "donation/donation-main-center"; // search.html 페이지를 반환
    }

    // 기부 글 상세 조회
    @GetMapping("/view")
    public String view(@RequestParam("donaNo") Long donaNo, Model model, HttpSession session) {
        // 기부 글 번호에 해당하는 상세 정보를 가져옴
        List<DonationViewDTO> donationViewList = donationService.getDonationViewList(donaNo);

        // 기부 상세 정보 출력 (디버깅용 로그)
        System.out.println("DonationViewList: " + donationViewList);

        // 세션에서 센터 회원 번호와 일반 회원 번호를 가져옴
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
        Long memberNo = (Long) session.getAttribute("memberNo");

        // 모델에 세션 값과 상세 기부 정보를 추가
        model.addAttribute("centerMemberNo", centerMemberNo);
        model.addAttribute("memberNo", memberNo);
        model.addAttribute("donationViewList", donationViewList);

        // 기부 상세 페이지로 이동
        return "donation/donation-detail";
    }

    // 기부 글 작성 페이지 이동
    @GetMapping("/write")
    public String donationWrite(HttpSession session, Model model) {
        // 세션에서 센터 회원 번호를 가져옴
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");

        // 모델에 센터 회원 번호를 추가
        model.addAttribute("centerMemberNo", centerMemberNo);

        // 기부 글 작성 페이지로 이동
        return "donation/donation-write";
    }

    // 기부 글 등록
    @PostMapping("/writeRegi")
    public String registerDonation(DonationWriteDTO donationWriteDTO) {
        // 기부 글 등록 서비스 호출
        donationService.registerDonation(donationWriteDTO);

        // 등록 후 기부 목록 페이지로 리다이렉트
        return "redirect:/dona/list";
    }

    // 기부 글 삭제
    @GetMapping("/delete")
    public String delete(@RequestParam("donaNo") Long donaNo) {
        // 삭제할 기부 글 번호 출력 (디버깅용 로그)
        System.out.println(donaNo + " 확인");

        // 기부 글 삭제 서비스 호출
        donationService.donationDelete(donaNo);

        // 삭제 후 기부 목록 페이지로 리다이렉트
        return "redirect:/dona/list";
    }

    // 기부 글 수정 페이지로 이동
    @GetMapping("/modify")
    public String donationModify(@RequestParam("donaNo") Long donaNo, Model model, HttpSession session) {
        // 수정할 기부 글 정보를 가져옴
        List<DonationViewDTO> donation = donationService.getDonationViewList(donaNo);

        // 세션에서 센터 회원 번호를 가져옴
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");

        // 디버깅용 로그 (기부 글 정보 확인)
        System.out.println(donation + "확인");

        // 모델에 세션 값과 기부 글 정보를 추가
        model.addAttribute("centerMemberNo", centerMemberNo);
        model.addAttribute("donation", donation);

        // 기부 글 수정 페이지로 이동
        return "donation/donation-modify"; // 수정 페이지 템플릿 이름
    }

    // 기부 글 수정 등록
    @PostMapping("/modifyRegi")
    public String donationUpdate(DonationWriteDTO donationWriteDTO) {
        // 수정된 기부 글 정보를 서비스에 전달하여 업데이트 수행
        donationService.donationUpdate(donationWriteDTO);

        // 수정 후 기부 목록 페이지로 리다이렉트
        return "redirect:/dona/list";
    }

    //댓글 신고
//    @GetMapping("/donaCommentReport")
//    public String donaCommentReport(HttpSession session, DonaCommentReportDTO donaCommentReportDTO,
//                                     @RequestParam("donaCommentNo") Long donaCommentNo,
//                                     @RequestParam("donaNo") Long donaNo,
//                                     @RequestParam("commentReport") String commentReport,RedirectAttributes redirectAttributes) {
//        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
//        Long memberNo = (Long) session.getAttribute("memberNo");
//
//        donaCommentReportDTO.setCommentReportWriter(centerMemberNo != null ? centerMemberNo : memberNo);
//        donaCommentReportDTO.setCommentReport(commentReport);
//        donaCommentReportDTO.setCommentReport(donaCommentNo);
//        donaCommentReportDTO.setCommentReportNo(donaNo);
//
//        donaCommentService.donaCommentReport(donaCommentReportDTO);
//
//        // 게시글 신고 메시지를 플래시 속성으로 추가
//        redirectAttributes.addFlashAttribute("ContentreportSuccess", true);
//
//        return "redirect:/dona/view/" + donaNo; // 상세페이지로 이동
//    }



}
