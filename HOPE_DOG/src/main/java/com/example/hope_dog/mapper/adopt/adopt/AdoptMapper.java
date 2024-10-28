package com.example.hope_dog.mapper.adopt.adopt;

import com.example.hope_dog.dto.adopt.adopt.AdoptDetailDTO;
import com.example.hope_dog.dto.adopt.adopt.AdoptMainDTO;
import com.example.hope_dog.dto.adopt.adopt.AdoptRequestDTO;
import com.example.hope_dog.dto.adopt.adopt.MainDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdoptMapper {
    //입양/임시보호/후기 메인
    List<MainDTO> main();

    //입양 상세글
    List<AdoptDetailDTO> adoptDetail(Long adoptNo);

    //입양 신청서
    List<AdoptRequestDTO> adoptRequest();

    //입양 메인
    List<AdoptMainDTO> adoptMain();






}
