package com.example.hope_dog.controller.donation;

import com.example.hope_dog.dto.donation.DonationListDTO;
import com.example.hope_dog.dto.donation.DonationUpdateDTO;
import com.example.hope_dog.dto.donation.DonationViewDTO;
import com.example.hope_dog.dto.donation.DonationWriteDTO;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/dona")
public class DonationController {

    private final DonationService donationService;
    private final DonationMapper donationMapper;

//    @GetMapping("/list")
//    public String List(Model model) {
//        List<DonationListDTO> donationList = donationService.getDonationList();
//        model.addAttribute("donationList", donationList);
//
//        return "donation/donation-main-center";
//    }

    @GetMapping("/list")
    public String donationList(Criteria criteria, Model model, HttpSession session) {
        List<DonationListDTO> donationList = donationService.findAllPage(criteria);
        int total = donationService.findTotal();
        Page page = new Page(criteria, total);

        model.addAttribute("donationList", donationList);
        model.addAttribute("page", page);

        return "donation/donation-main-center";
    }


//    @GetMapping("/list")
//    public String List(Criteria criteria, Model model, HttpSession session){
////        List<DonationListDTO> donationList = donationService.getDonationList();
//        List<DonationListDTO> donationList = donationService.findAllPage(criteria);
//        int total = donationService.findTotal();
//        Page page = new Page(criteria, total);
////        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo"); //이것도 무시 세션값 가져와서 저장
//
//        model.addAttribute("DonationList", donationList);
//        model.addAttribute("page", page);
////        model.addAttribute("centerMemberNo", centerMemberNo); //이건 나만 쓰는거 무시 세션값 html에서 쓸수있게 model추가
//
//        return "donation/donation-main-center";
//    }

    @GetMapping("/view/{donaNo}")
    public String view(@PathVariable("donaNo") Long donaNo, Model model, HttpSession session) {
        List<DonationViewDTO> donationViewList = donationService.getDonationViewList(donaNo);
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");

        model.addAttribute("centerMemberNo", centerMemberNo);
        model.addAttribute("donationViewList", donationViewList);

        return "donation/donation-detail";
    }


    // 게시글 조회
//    @GetMapping("/detail/{boardId}")
//    public String view(@PathVariable Long boardId, Model model, @AuthenticationPrincipal CustomOAuth2User customUser) {
//        BoardDTO board = boardService.selectBoardDetail(boardId, customUser);
//        List<FileVO> files = fileService.getFileListByBoardId(boardId);
//        model.addAttribute("board", board);
//        model.addAttribute("files", files);
//        return "board/detail";
//    }



    @GetMapping("/write")
    public String donationWrite(HttpSession session, Model model) {
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");

        model.addAttribute("centerMemberNo", centerMemberNo);
        return "donation/donation-write";
    }


    @PostMapping("/writeRegi")
    public String registerDonation(DonationWriteDTO donationWriteDTO) {
        donationService.registerDonation(donationWriteDTO);
        return "redirect:/dona/list";
    }

//    @PostMapping("/write")
//    public String boardWrite(BoardWriteDTO boardWriteDTO, @SessionAttribute("userId") Long userId
//            , RedirectAttributes redirectAttributes, @RequestParam("boardFile") List<MultipartFile> files) {
//        boardWriteDTO.setUserId(userId);
//        log.info("boardWriteDTO = ", boardWriteDTO);
//
//        try {
//            boardService.registerBoardWithFiles(boardWriteDTO, files);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

    @PostMapping("/delete/{donaNo}")
    public String delete(@PathVariable Long donaNo, RedirectAttributes redirectAttributes) {
        System.out.println(donaNo + "컨트롤러 들어옴");
        donationService.donationDelete(donaNo);
        redirectAttributes.addFlashAttribute("message", "게시글이 성공적으로 삭제되었습니다.");
        return "redirect:/dona/list";
    }


    @GetMapping("/modify")
    public String donationModify(@RequestParam Long donaNo, Model model) {
        DonationViewDTO donation = donationService.findById(donaNo);
        model.addAttribute("donation", donation);
        return "/donation/donation-modify";
    }

}
