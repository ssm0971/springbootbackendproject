package com.example.hope_dog.controller.donation;

import com.example.hope_dog.dto.adopt.adopt.AdoptCommentDTO;
import com.example.hope_dog.dto.adopt.adopt.AdoptDetailDTO;
import com.example.hope_dog.dto.donation.*;
import com.example.hope_dog.dto.page.Criteria;
import com.example.hope_dog.dto.page.Page;
import com.example.hope_dog.mapper.donation.DonationMapper;
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

    private final DonationService donationService;
    private final DonationMapper donationMapper;

    // 글 목록
    @GetMapping("/list")
    public String donationList(Criteria criteria, Model model, HttpSession session) {
        List<DonationListDTO> donationList = donationService.findAllPage(criteria);
        int total = donationService.findTotal();
        Page page = new Page(criteria, total);

        model.addAttribute("donationList", donationList);
        model.addAttribute("page", page);

        return "donation/donation-main-center";
    }

    // 글 상세
    @GetMapping("/view")
    public String view(@RequestParam("donaNo") Long donaNo, Model model, HttpSession session) {
        List<DonationViewDTO> donationViewList = donationService.getDonationViewList(donaNo);

        System.out.println("DonationViewList: " + donationViewList);  // 로그 추가
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
        Long memberNo = (Long) session.getAttribute("memberNo");

        model.addAttribute("centerMemberNo", centerMemberNo);
        model.addAttribute("memberNo", memberNo);
        model.addAttribute("donationViewList", donationViewList);

        return "donation/donation-detail";
    }

    // 글 작성
    @GetMapping("/write")
    public String donationWrite(HttpSession session, Model model) {
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");

        model.addAttribute("centerMemberNo", centerMemberNo);
        return "donation/donation-write";
    }

    // 글 등록
    @PostMapping("/writeRegi")
    public String registerDonation(DonationWriteDTO donationWriteDTO) {
        donationService.registerDonation(donationWriteDTO);

        return "redirect:/dona/list";
    }

    //글 삭제
    @GetMapping("/delete")
    public String delete(@RequestParam("donaNo") Long donaNo) {
        System.out.println(donaNo + " 확인"); // adoptNo 확인 로그

        // DonationViewDTO 생성 및 donaNo 설정
        DonationViewDTO donationViewDTO = new DonationViewDTO();
        donationViewDTO.setDonaNo(donaNo);

        // 서비스 호출
        donationService.donationDelete(donaNo);

        return "redirect:/dona/list"; // 리다이렉트
    }



    // 글 수정 페이지 이동
    @GetMapping("/modify")
    public String donationModify(@RequestParam("donaNo") Long donaNo, Model model, HttpSession session) {

        List<DonationViewDTO> donation = donationService.getDonationViewList(donaNo);
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");

        System.out.println(donation + "확인");

        model.addAttribute("centerMemberNo", centerMemberNo);
        model.addAttribute("donation", donation);

        return "donation/donation-modify"; // 수정 페이지 템플릿 이름
    }


    // 글 수정 등록
    @PostMapping("/modifyRegi")
    public String donationUpdate(DonationWriteDTO donationWriteDTO) {
        donationService.donationUpdate(donationWriteDTO);
        return "redirect:/dona/list"; // 수정 후 목록으로 리다이렉트
    }



}
