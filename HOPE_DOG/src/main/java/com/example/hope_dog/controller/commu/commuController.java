package com.example.hope_dog.controller.commu;


import com.example.hope_dog.dto.commu.CommuCommentDTO;
import com.example.hope_dog.dto.commu.CommuDTO;
import com.example.hope_dog.dto.commu.CommuDetailDTO;
import com.example.hope_dog.dto.commu.CommuReportDTO;
import com.example.hope_dog.dto.page.Criteria;
import com.example.hope_dog.service.commu.CommuService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/commu") // localhost:8060/commu
public class commuController {
    private final CommuService commuService;

    @GetMapping("/main") // /commu/list 경로로 GET 요청을 받을 때
    public String getCommuList(HttpSession session, Model model) {
        // 세션에서 memberNo와 centerMemberNo를 가져옴
        Long memberNo = (Long) session.getAttribute("memberNo");
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");

        // 서비스에서 게시글 목록을 가져옴
        List<CommuDTO> commuList = commuService.getCommuList(session);

        // commuList가 비어있는지 확인
        if (commuList.isEmpty()) {
            System.out.println("게시글 목록이 비어있습니다.");
        } else {
            // 각 게시글의 작성자 정보를 출력하여 확인
            for (CommuDTO commu : commuList) {
                System.out.println("게시글 작성자 ID: " + commu.getCommuWriter() +
                        ", 일반회원 닉네임: " + commu.getMemberNickname() +
                        ", 센터회원 이름: " + commu.getCenterMemberName());
            }
        }

        model.addAttribute("commuList", commuList); // 모델에 데이터 추가

        System.out.println("컨트롤러 글목록회원" + memberNo + ", 센터회원" + centerMemberNo);
        return "commu/commu-main"; // 뷰 이름을 반환
    }

    //카테고리 분류
    @GetMapping("/filter")
    public String filterCommu(@RequestParam("cate") String cate, Model model) {
        List<CommuDTO> commuList = commuService.getCommuListByCate(cate);
        model.addAttribute("commuList", commuList);
        model.addAttribute("selectedCate", cate);

        // 콘솔에 출력
        System.out.println("Selected Category: " + cate);
        System.out.println("Posts Count: " + commuList.size());

        return "commu/commu-main"; // 뷰 이름 반환
    }

    //인기게시글
    @GetMapping("/good")
    public String getGoodCommuList(Model model) {
        List<CommuDTO> commuList = commuService.cateCommuGood();
        model.addAttribute("commuList", commuList);


        return "commu/commu-main";
    }


    //거지같은 검색 더럽게 안됨
//    @GetMapping("/commu/main/commuSearch")
//    public String commuSearch(@RequestParam("page") int page, @RequestParam("searchtype") String searchType, @RequestParam("keyword") String keyword, Model model) {
//        Criteria criteria = new Criteria(page, 10);  // 페이지와 한 페이지당 게시글 수
////        List<CommuDTO> commuList = commuService.searchCommuList(searchType, keyword, criteria);
////        int totalItems = commuService.getTotalItems(searchType, keyword);
//
//        model.addAttribute("commuList", commuList);
//        model.addAttribute("totalItems", totalItems);
//
//        return "commu/commu-mail";  // 검색 결과가 포함된 페이지를 반환
//    }

    //게시글 상세
    @GetMapping("/post/{commuNo}")
    public String getCommuDetail(@PathVariable("commuNo") Long commuNo, Model model, HttpSession session) {

        // 조회수 증가
        CommuDTO commuDTO = new CommuDTO();
        commuDTO.setCommuNo(commuNo);
        commuService.commuGood(commuDTO);

        // 세션에서 memberNo 및 centerMemberNo 가져오기
        Long memberNo = (Long) session.getAttribute("memberNo");
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
        System.out.println("컨트롤러 게시글 멤버 : " + memberNo);
        System.out.println("컨트롤러 게시글 센터:" + centerMemberNo);
        List<CommuDetailDTO> commuDetailList = commuService.selectCommuByNo(commuNo);

        //댓글
        List<CommuCommentDTO> commuCommentList = commuService.getcommuComment(commuNo);
        System.out.println("컨트롤러 댓글 commuCommentList : " + commuCommentList);


        // 사용자 정보 추가
        model.addAttribute("memberNo", memberNo);
        model.addAttribute("centerMemberNo", centerMemberNo);
        model.addAttribute("commuDetailList", commuDetailList);
        model.addAttribute("commuCommentList", commuCommentList);

        return "commu/commu-post"; // 게시글 상세 페이지로 이동
    }

    //글작성
    @GetMapping("/commuWrite")
    public String commuWrite(HttpSession session, Model model) {
        // 세션에서 centerMemberNo와 memberNo 가져오기
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
        Long memberNo = (Long) session.getAttribute("memberNo"); // 일반회원 ID 가져오기

        // 모델에 센터회원 번호와 일반회원 번호 추가
        model.addAttribute("centerMemberNo", centerMemberNo);
        model.addAttribute("memberNo", memberNo); // 일반회원 번호 추가
        System.out.println("컨트롤러 센터멤버" + centerMemberNo);
        System.out.println("컨트롤러멤버" + memberNo);

        return "commu/commu-post-write"; // 게시글 작성 페이지 템플릿 반환
    }

    // 커뮤니티 글 등록 처리
    @PostMapping("/commurequestRegi")
    public String postCommuWrite(@SessionAttribute(name = "memberNo", required = false) Long memberNo,
                                 @SessionAttribute(name = "centerMemberNo", required = false) Long centerMemberNo,
                                 //세션에 저장된 memberNo를 조회
                                 CommuDTO commuDTO) {
        Long writerNo = memberNo != null ? memberNo : centerMemberNo;
        if (writerNo == null) {
            throw new IllegalArgumentException("로그인 상태가 필요합니다.");
        }

        commuDTO.setCommuWriter(writerNo);  // commuWriter에 세션에서 가져온 ID 설정
        System.out.println("컨트롤러 writerNo :" + writerNo);
        commuService.writePost(commuDTO);
        return "redirect:/commu/main";
    }

//    //커뮤니티 글 수정
//    @GetMapping("/rewrite/{commNO}")
//    public String rewriteCommu(@PathVariable("commNO") Long commNO, Model model) {
//        CommuDTO commuDTO = commuService.getCommuDetail(commNO);
//
//        model.addAttribute("commuDTO", commuDTO);
//
//        return "commu/commu-rewrite";
//    }

    //글 삭제
    @DeleteMapping("/commuDelete/{commuNo}")
    public ResponseEntity<String> commuDelete(@PathVariable("commuNo") Long commuNo) {
        System.out.println("컨트롤러 글삭제:" + commuNo);

        CommuDetailDTO commuDetailDTO = new CommuDetailDTO();
        commuDetailDTO.setCommuNo(commuNo);

        boolean isDeleted = commuService.commuDelete(commuDetailDTO);  // 삭제 성공 여부 확인

        if (isDeleted) {
            return ResponseEntity.ok("삭제되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("삭제에 실패했습니다.");
        }
    }

    //글 신고
    @GetMapping("/commuReport")
    public String postCommuReport(@RequestParam("commuNo") Long commuNo,
                                  @RequestParam("reportContent") String reportContent,
                                  HttpSession session,
                                  Model model) {
        //@RequestParam -commuNo을 쉽게 파라미터에 전달할 수 있게 해줌
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
        Long memberNo = (Long) session.getAttribute("memberNo");

        System.out.println("확인" + reportContent);
        System.out.println("확인" + commuNo);

        // 신고 DTO 생성 및 필드 설정
        CommuReportDTO commuReportDTO = new CommuReportDTO();
        commuReportDTO.setReportWriter(centerMemberNo != null ? centerMemberNo : memberNo);
//        commuReportDTO.setReportCate("커뮤니티");
        commuReportDTO.setReportContent(reportContent);
        commuReportDTO.setReportContentNo(commuNo); // 신고 대상 글 번호 설정

        // 신고 서비스 호출
        commuService.commuReport(commuReportDTO);

        // 신고 후 커뮤니티 메인 페이지로 리다이렉트
        return "redirect:/commu/main";
    }

    //댓글 등록
    @PostMapping("/commuCommentRegi")
    public String commuComentWrite(HttpSession session, CommuCommentDTO commuCommentDTO) {
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
        Long memberNo = (Long) session.getAttribute("memberNo");

        commuCommentDTO.setCommuCommentWriter(centerMemberNo != null ? centerMemberNo : memberNo);

        Long commuNo = commuCommentDTO.getCommuNo();

        commuService.commuCommentRegi(commuCommentDTO);
        return "redirect:/commu/post/" + commuNo;
    }

    //댓글 수정
    @PostMapping("/commuCommentModi")
    public String commuCommentModi(HttpSession session, CommuCommentDTO commuCommentDTO,
                                   @RequestParam("commuNo") Long commuNo) {
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
        Long memberNo = (Long) session.getAttribute("memberNo");

        // 댓글 작성자 설정
        commuCommentDTO.setCommuCommentWriter(centerMemberNo != null ? centerMemberNo : memberNo);

        // 댓글 수정 서비스 호출
        commuService.commuCommentModi(commuCommentDTO);

        // 댓글 수정 후 상세페이지로 리디렉션 (댓글 수정 후 댓글 목록을 갱신하려면 redirect가 아닌 새 댓글을 가져오는 방식도 고려)
        return "redirect:/commu/post/" + commuNo;
    }

    //댓글 삭제
    @PostMapping("/commuCommentDelete")
    public String commuCommentDelete(HttpSession session, CommuCommentDTO commuCommentDTO,
                                    @RequestParam("commuNo")Long commuNo,
                                    @RequestParam("commuCommentNo") Long commuCommentNo) {
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
        Long memberNo = (Long) session.getAttribute("memberNo");

        //댓글작성자설정
        commuCommentDTO.setCommuCommentWriter(centerMemberNo != null ? centerMemberNo : memberNo);
        commuCommentDTO.setCommuCommentNo(commuCommentNo);

        commuService.commuCommentDelete(commuCommentDTO);

        return "redirect:/commu/post/" + commuNo;
    }

    //댓글 신고
    @GetMapping("/commuCommentReport")
    public String commuCommentReport(HttpSession session, CommuReportDTO commuReportDTO,
                                    @RequestParam("commuCommentNo") Long commuCommentNo,
                                    @RequestParam("commuNo") Long commuNo,
                                    @RequestParam("reportComment") String reportComment) {
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
        Long memberNo = (Long) session.getAttribute("memberNo");

        commuReportDTO.setReportWriter(centerMemberNo != null ? centerMemberNo : memberNo);
        commuReportDTO.setReportComment(reportComment);
        commuReportDTO.setReportCommentNo(commuCommentNo);
        commuReportDTO.setReportContentNo(commuNo);

        commuService.commuCommentReport(commuReportDTO);

        return "redirect:/commu/post/" + commuNo;
    }

}


