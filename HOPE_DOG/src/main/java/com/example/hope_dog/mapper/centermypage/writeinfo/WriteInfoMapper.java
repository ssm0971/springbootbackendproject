package com.example.hope_dog.mapper.centermypage.writeinfo;

import com.example.hope_dog.dto.centermypage.writeinfo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WriteInfoMapper {

    //센터회원 이름 조회
    String writerInfo(Long centerMemberNo);

    //커뮤니티 게시판 조회
    List<WriteInfoCommuListDTO> writeInfoCommuList(Long centerMemberNo);

    //봉사 게시판 조회
    List<WriteInfoVolListDTO> writeInfoVolList(Long centerMemberNo);

    //입양 게시판 조회
    List<WriteInfoAdoptListDTO> writeInfoAdoptList(Long centerMemberNo);
}
