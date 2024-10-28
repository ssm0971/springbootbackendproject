package com.example.hope_dog.mapper.volun.car;

import com.example.hope_dog.controller.centermember.CenterMemberController;
import com.example.hope_dog.dto.centerMember.CenterMemberDTO;
import com.example.hope_dog.dto.member.MemberDTO;
import com.example.hope_dog.dto.volun.car.CarDTO;
import org.apache.ibatis.annotations.Mapper;

import java.lang.reflect.Member;
import java.util.List;

@Mapper
public interface CarMapper {
    //카풀 게시판 목록
   List<CarDTO> selectAllCars();

    //게시판 상세페이지
   CarDTO selectCarById(Long carNo);

    //카테고리 분류별 게시글 조회
    List<CarDTO> selectCate(String cate);
    //일반회원 조회
    MemberDTO selectMemberByNo(Long no);
    //센터회원 조회
    CenterMemberDTO selectCenterMemberByNo(Long no);
}
