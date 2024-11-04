package com.example.hope_dog.mapper.volun.car;

import com.example.hope_dog.dto.centerMember.CenterMemberDTO;
import com.example.hope_dog.dto.member.MemberDTO;
import com.example.hope_dog.dto.page.Criteria;
import com.example.hope_dog.dto.volun.car.CarCommentDTO;
import com.example.hope_dog.dto.volun.car.CarDTO;
import com.example.hope_dog.dto.volun.car.CarDetailDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CarMapper {
    //카풀 게시판 목록
   List<CarDTO> selectAllCars();

    // 카테고리 분류별 게시글 조회 (페이지네이션 포함)
    List<CarDTO> selectCate(@Param("cate") String cate, @Param("criteria") Criteria criteria);

    //카풀 전체 게시판(페이지네이션)
    List<CarDTO> selectCarMain();
    // 카테고리별 게시글 수를 카운트
    int countCarsByCategory(@Param("cate") String cate);

    //전체 게시글 수 조회
    int carTotal();
    //페이지별 게시글 조회
    List<CarDTO> selectCarPage(Criteria criteria);

   // 게시글 상세페이지 조회 (게시글과 댓글 정보 포함)
   CarDetailDTO selectCarDetail(@Param("carNo") Long carNo);

    // 댓글 조회 (특정 게시글의 댓글 리스트)
    List<CarCommentDTO> selectCommentsByCarNo(@Param("carNo") Long carNo);

    // 검색 결과 조회
    List<CarDTO> searchCars(@Param("params") Map<String, Object> params);

    // 검색 조건에 따른 총 게시글 수 조회
//    int countCarsBySearch(@Param("params") Map<String, Object> params);

    // 검색 조건을 기반으로 총 개수 조회
//    int countCars(@Param("params") Map<String, Object> params);

 int countCarsByTitle(@Param("keyword") String keyword);
 int countCarsByNickname(@Param("keyword") String keyword);

 List<CarDTO> searchCarsByTitle(@Param("carTitle") String carTitle, @Param("criteria") Criteria criteria);
 List<CarDTO> searchCarsByNickname(@Param("nickname") String nickname, @Param("criteria") Criteria criteria);

    //일반회원 조회
    MemberDTO selectMemberByNo(Long memberNo);
    //센터회원 조회
    CenterMemberDTO selectCenterMemberByNo(Long centerMemberNo);




}
