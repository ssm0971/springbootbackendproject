package com.example.hope_dog.controller.commu;


import com.example.hope_dog.dto.commu.CommuCommentDTO;
import com.example.hope_dog.dto.commu.CommuDTO;
import com.example.hope_dog.dto.commu.CommuDetailDTO;
import com.example.hope_dog.service.commu.CommuService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
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

    //글목록
    @GetMapping("/main") // /commu/list 경로로 GET 요청을 받을 때
    public String getCommuList(Model model) {
        List<CommuDTO> commuList = commuService.getCommuList(); // 서비스에서 게시글 목록을 가져옴
        model.addAttribute("commuList", commuList); // 모델에 데이터 추가
        System.out.println(commuList + "확인");
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
//    @GetMapping("/good")
//    public String getGoodCommuList(@RequestParam("cate") String cate, Model model) {
//        List<CommuDTO> goodCommuList = commuService.cateCommuGood(cate);
//        model.addAttribute("goodCommuList", goodCommuList);
//        model.addAttribute("selectedCate", cate);
//
//        return "commu/commu-main"; // 인기 게시글을 보여줄 뷰 이름으로 수정 (다른 뷰를 사용)
//    }



    //거지같은 검색 더럽게 안됨
    @GetMapping("/commuSearch")
    public String searchCommu(@RequestParam("keyword") String keyword, Model model) {
        System.out.println("컨트롤러 검색 키워드: " + keyword);
        List<CommuDTO> results = commuService.searchCommu(keyword);
        model.addAttribute("commuList", results); // 검색 결과를 commuList로 추가
        return "commu/commu-main"; // 결과를 commu-main 뷰로 반환
    }

    //게시글 상세
    // 게시글 상세 정보 조회
    @GetMapping("/post/{commuNo}")
    public String getCommuDetail(@PathVariable("commuNo") Long commuNo, Model model, CommuDTO commuDTO) {
        //조회수증가
        commuService.commuGood(commuDTO);

        // 게시글 상세 정보 조회
        CommuDetailDTO commuDetail = commuService.getCommuDetail(commuNo);
        model.addAttribute("commuDetail", commuDetail);

        return "commu/commu-post"; // 게시글 상세 페이지로 이동
    }

    // 커뮤니티 글 작성 페이지 이동
    @GetMapping("/commuWrite")
    public String commuWrite(HttpSession session, Model model) {
        // 세션에서 memberNo 가져오기 (필요시)
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
        model.addAttribute("centerMemberNo", centerMemberNo);
        System.out.println("컨트롤러 작성페이지 들어왔");

        return "commu/commu-post-write"; // 템플릿 이름
    }

    // 커뮤니티 글 등록 처리
    @PostMapping("/commurequestRegi")
    public String postCommuWrite(@DateTimeFormat(pattern = "yyyy-MM-dd") CommuDTO commuDTO) {
        // 서비스 호출하여 데이터베이스에 저장
        commuService.writePost(commuDTO);
        return "redirect:commu/commu-main"; // 커뮤니티 메인 페이지로 리다이렉트
    }



}
