package com.example.hope_dog.mapper.volun.car;

import com.example.hope_dog.dto.centerMember.CenterMemberDTO;
import com.example.hope_dog.dto.commu.CommuReportDTO;
import com.example.hope_dog.dto.member.MemberDTO;
import com.example.hope_dog.dto.page.Criteria;
import com.example.hope_dog.dto.volun.car.CarCommentDTO;
import com.example.hope_dog.dto.volun.car.CarDTO;
import com.example.hope_dog.dto.volun.car.CarDetailDTO;
import com.example.hope_dog.dto.volun.car.CarReportDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CarMapper {
    //카풀 게시판 목록
   List<CarDTO>selectAllCars();

    // 카테고리 분류별 게시글 조회
    List<CarDTO> findCarByCate(@Param("cate") String cate);


   // 게시글 상세페이지 조회 (게시글과 댓글 정보 포함)
 List<CarDetailDTO> selectCarDetail(Long carNo);

 //카풀 검색
    List<CarDetailDTO> carSearch(@Param("carTitle") String carTitle,
                                 @Param("memberNickname")String memberNickname,
                                 @Param("centerMemberName") String centerMemberName);


    //게시글 작성
    void carWriter(CarDTO carDTO);

    //게시글 수정
    void carModify(CarDetailDTO carDetailDTO);


    //게시글 삭제
    void carDelete(CarDetailDTO carDetailDTO);

    //게시글 신고
    void carContentReport(CarReportDTO carReportDTO);

    // 댓글 조회 (특정 게시글의 댓글 리스트)
    List<CarCommentDTO> carComment(Long carNo);

    //댓글 등록
    void carCommentRegi(CarCommentDTO carCommentDTO);

//    댓글 수정
    void carCommentModi(CarCommentDTO carCommentDTO);

//    댓글 삭제
    void carCommentDelete(CarCommentDTO carCommentDTO);

    //댓글 신고
    void carCommentReport(CarReportDTO carReportDTO);

    //일반회원 조회
    MemberDTO selectMemberByNo(Long memberNo);
    //센터회원 조회
    CenterMemberDTO selectCenterMemberByNo(Long centerMemberNo);




}
