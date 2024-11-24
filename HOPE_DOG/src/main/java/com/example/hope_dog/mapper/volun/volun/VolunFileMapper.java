package com.example.hope_dog.mapper.volun.volun;

import com.example.hope_dog.dto.volun.volun.VolunFileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface VolunFileMapper {
    void insertFile(VolunFileDTO voluFileDTO);

    void deleteFile(Long fileNo);

    List<VolunFileDTO> selectList(Long fileNo);
}
