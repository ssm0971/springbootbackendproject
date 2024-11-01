package com.example.hope_dog.mapper.centermypage.writeinfo;

import com.example.hope_dog.dto.centermypage.writeinfo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WriteInfoMapper {

    //커뮤니티 게시판 조회
    List<WriteInfoCommuListDTO> writeInfoCommuList(Long centerMemberNo);

    //센터회원 이름 조회
    String writerInfo(Long centerMemberNo);

}
