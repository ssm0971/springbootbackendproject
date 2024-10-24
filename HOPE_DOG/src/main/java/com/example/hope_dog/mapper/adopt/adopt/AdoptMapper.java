package com.example.hope_dog.mapper.adopt.adopt;

import com.example.hope_dog.dto.adopt.adopt.AdoptMainDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdoptMapper {
    List<AdoptMainDTO> adoptMain();

    public void AdoptMain(AdoptMainDTO adoptMainDTO);
}
