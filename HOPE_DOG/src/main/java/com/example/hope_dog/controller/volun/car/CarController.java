package com.example.hope_dog.controller.volun.car;

import com.example.hope_dog.dto.commu.CommuCommentDTO;
import com.example.hope_dog.dto.commu.CommuDetailDTO;
import com.example.hope_dog.dto.page.Criteria;
import com.example.hope_dog.dto.page.Page;
import com.example.hope_dog.dto.volun.car.CarCommentDTO;
import com.example.hope_dog.dto.volun.car.CarDTO;
import com.example.hope_dog.dto.volun.car.CarDetailDTO;
import com.example.hope_dog.dto.volun.car.CarReportDTO;
import com.example.hope_dog.service.volun.car.CarService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/car")// 시작 도메인 localhost:8060/car
//생성자 주입
public class CarController {
    private final CarService carService;

    //글 목록
    @GetMapping("/main")
    public String carList(HttpSession session,Model model){
        Long memberNo =(Long) session.getAttribute("memberNo");
        Long centerMemberNo =(Long) session.getAttribute("centerMemberNo");

      List<CarDTO> carList = carService.getCarList(session);
        System.out.println("컨트롤러 리스트 : "+carList);

      if(carList.isEmpty()) {
          System.out.println("게시글 목록이 비어있습니다");
      }else {
          for(CarDTO car : carList){
              System.out.println("게시글 작성자 id : " + car.getCarWriter() +
                      ",일반회원 닉네임 : " + car.getMemberNickname() +
                      ",센터회원 이름 : " + car.getCenterMemberName());
          }
      }

      model.addAttribute("carList", carList);
        model.addAttribute("memberNo", memberNo);
        model.addAttribute("centerMemberNo", centerMemberNo);

        return "volun/car/volun-car-main";

    }


    // 카테고리 분류에 따른 게시글 조회
    @GetMapping("/filter")
    public String filterCarList(@RequestParam("cate") String cate, Model model) {

        List<CarDTO> carList = carService.getCarListByCate(cate);
        model.addAttribute("carList", carList);
        model.addAttribute("cate", cate);

        System.out.println("cate : " + cate);
        System.out.println("carList : " + carList
        );
        return "volun/car/volun-car-main";
    }

    // 검색
    @GetMapping("/main/search")
    public String search(@RequestParam(value = "searchType") String searchType,
                         @RequestParam(value = "keyword") String keyword,
                         Model model) {
        // 검색 조건에 맞는 결과 가져오기
        List<CarDetailDTO> carList = null;

        // 검색 조건에 따라 처리
        if ("carTitle".equals(searchType)) {
            carList = carService.carSearch(keyword, null, null);  // 제목 검색
        } else if ("memberNickname".equals(searchType)) {
            carList = carService.carSearch(null, keyword, null);  // 닉네임 검색
        } else if ("centerMemberName".equals(searchType)) {
            carList = carService.carSearch(null, null, keyword);  // 센터명 검색
        }

        // 모델에 결과 추가
        model.addAttribute("carList", carList);
        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);

        return "volun/car/volun-car-main"; // search.html 페이지를 반환
    }

    //게시글 상세
    @GetMapping("/post/{carNo}")
    public String selectCarDetail(@PathVariable("carNo") Long carNo, Model model,HttpSession session) {

        //세션에서 일반회원, 센터회원 가져오기
        Long memberNo = (Long) session.getAttribute("memberNo");
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
        List<CarDetailDTO> carDetailList = carService.selectCarDetail (carNo);
        System.out.println("컨트롤러 carDetailList : " + carDetailList);
        System.out.println("컨트롤러 글 상세 carNO:" + carNo);

        //댓글
        List<CarCommentDTO> carCommentList = carService.carComment(carNo);
        System.out.println("컨트롤러 carCommentList : " + carCommentList);

        //사용자 정보 추가
        model.addAttribute("memberNo", memberNo);
        model.addAttribute("centerMemberNo", centerMemberNo);
        model.addAttribute("carDetailList", carDetailList);
        model.addAttribute("carCommentList", carCommentList);

        return "volun/car/volun-car-post";
    }

    //글 작성
    @GetMapping("/carWrite")
    public String carWriter(HttpSession session, Model model) {
        //일반회원 , 센터회원 가져오기
        Long memberNo = (Long) session.getAttribute("memberNo");
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");

        //모델에 센터회원, 일반회원 번호 추가
        model.addAttribute("memberNo", memberNo);
        model.addAttribute("centerMemberNo", centerMemberNo);
        return "volun/car/volun-car-post-write";
    }

    //글 등록
    @PostMapping("/carrequestRegi")
    public String postCarWrite(@SessionAttribute(name = "memberNo", required = false) Long memberNo,
                               @SessionAttribute(name = "centerMemberNo", required = false) Long centerMemberNo,
                               //세션에 저장된 memberNo를 조회
                                CarDTO carDTO) {
        Long writerNo = memberNo != null ? memberNo : centerMemberNo;
        if(writerNo == null) {
            throw new IllegalArgumentException("로그인 상태가 필요합니다");
        }

        carDTO.setCarWriter(writerNo);
        carService.carWriter(carDTO);
        // 글 등록 후 carNo를 얻어와서 리다이렉트 URL에 포함시킴
        Long carNo = carDTO.getCarNo(); // carDTO에서 carNo를 가져옴
        return "redirect:/car/post/" + carNo; // 상세페이지로 이동
    }

    //글 수정페이지로 이동
    @GetMapping("/carmodify")
    public String carModify(@RequestParam("carNo") Long carNo,HttpSession session,Model model){
        List<CarDetailDTO> carDetailList = carService.selectCarDetail(carNo);
        Long centerMemberNo =(Long) session.getAttribute("centerMemberNo");
        Long memberNo =(Long) session.getAttribute("memberNo");
        System.out.println("컨트롤러 DTO :" + carDetailList);

        model.addAttribute("carDetailList",carDetailList);
        model.addAttribute("centerMemberNo",centerMemberNo);
        model.addAttribute("memberNo", memberNo);

        return "volun/car/volun-car-post-rewrite";
    }

    //글 수정등록
    @PostMapping("/carModifyRegi")
    public String carModifyRegi(@DateTimeFormat(pattern = "yyyy-MM-dd") CarDetailDTO carDetailDTO, HttpSession session){

        carService.carModify(carDetailDTO);
        // 글 등록 후 carNo를 얻어와서 리다이렉트 URL에 포함시킴
        Long carNo = carDetailDTO.getCarNo(); // carDTO에서 carNo를 가져옴
        return "redirect:/car/post/" + carNo; // 상세페이지로 이동
    }

    //글 삭제
    @GetMapping("/carDelete")
        public String carDelete(@RequestParam("carNo") Long carNo){

        CarDetailDTO carDetailDTO = new CarDetailDTO();
        carDetailDTO.setCarNo(carNo);

        carService.carDelete((carDetailDTO));

        return "redirect:/car/main";
        }

        //글 신고
    @GetMapping("/carContentReport")
    public String carContentReport(@RequestParam("carNo") Long carNo, @RequestParam("reportContent") String reportContent,
                                   HttpSession session, CarReportDTO carReportDTO, RedirectAttributes redirectAttributes){
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
        Long memberNo = (Long) session.getAttribute("memberNo");

        carReportDTO.setReportWriter(centerMemberNo != null ? centerMemberNo : memberNo);
        carReportDTO.setReportContent(reportContent);
        carReportDTO.setReportContentNo(carNo);

        System.out.println(carNo + "확인");


        carService.carContentReport(carReportDTO);
        Long reportNo = carReportDTO.getReportNo(); // carDTO에서 carNo를 가져옴


        // 게시글 신고 메시지를 플래시 속성으로 추가
        redirectAttributes.addFlashAttribute("ContentreportSuccess", true);

        return "redirect:/car/post/" + carNo; // 상세페이지로 이동

    }

    //댓글등록
    @PostMapping("/carCommentRegi")
    public String carComentWrite(HttpSession session, CarCommentDTO carCommentDTO){
        Long centerMemberNo =(Long) session.getAttribute("centerMemberNo");
        Long memberNo =(Long) session.getAttribute("memberNo");
        System.out.println("컨트롤러 댓글 센터 :" + centerMemberNo);
        System.out.println("컨트롤러 댓글 일반:" + memberNo);

        carCommentDTO.setCarCommentWriter(centerMemberNo!= null ? centerMemberNo : memberNo);

        Long carNo = carCommentDTO.getCarNo();
        System.out.println("컨트롤러 댓글 carNo: "+ carNo);
        carService.carCommentRegi(carCommentDTO);
        return "redirect:/car/post/" + carNo;
    }

    //댓글 수정
    @PostMapping("/carCommentModi")
    public String carCommentModi(HttpSession session,CarCommentDTO carCommentDTO,
                                 @RequestParam("carNo")Long carNo){
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
        Long memberNo = (Long) session.getAttribute("memberNo");

        //댓글작성자 설정
        carCommentDTO.setCarCommentWriter(centerMemberNo != null ? centerMemberNo : memberNo);

        //댓글 수정 서비스 호출
        carService.carCommentModi(carCommentDTO);

        return "redirect:/car/post/" + carNo;
    }

    //댓글 삭제
    @PostMapping("/carCommentDelete")
    public String carCommentDelete(CarCommentDTO carCommentDTO,HttpSession session,
                                   @RequestParam("carNo") Long carNo,
                                   @RequestParam("carCommentNo") Long carCommentNo){
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
        Long memberNo = (Long) session.getAttribute("memberNo");

        carCommentDTO.setCarCommentWriter(centerMemberNo != null ? centerMemberNo : memberNo);
        carCommentDTO.setCarCommentNo(carCommentNo);

        carService.carCommentDelete(carCommentDTO);

        return "redirect:/car/post/"+ carNo;
    }

    //댓글 신고
    @GetMapping("/carCommentReport")
    public String carCommentDelete(HttpSession session,
                                   @RequestParam("carCommentNo") Long carCommentNo,
                                   @RequestParam("carNo")Long carNo,
                                   @RequestParam("reportComment") String reportComment,RedirectAttributes redirectAttributes){
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");
        Long memberNo = (Long) session.getAttribute("memberNo");

        System.out.println("댓글신고 centerMemberNo: " + centerMemberNo);
        System.out.println("댓글신고 memberNo: " + memberNo);

        CarReportDTO carReportDTO = new CarReportDTO();
        carReportDTO.setReportComment(reportComment);
        carReportDTO.setReportCommentNo(carCommentNo);
        carReportDTO.setReportContentNo(carNo);
        carReportDTO.setReportWriter(centerMemberNo != null ? centerMemberNo : memberNo);

        carService.carCommentReport(carReportDTO);

        // 게시글 신고 메시지를 플래시 속성으로 추가
        redirectAttributes.addFlashAttribute("ContentreportSuccess", true);


        return "redirect:/car/post/" + carNo;
    }


}