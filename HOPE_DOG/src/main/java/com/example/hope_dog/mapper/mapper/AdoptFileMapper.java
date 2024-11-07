package com.example.hope_dog.mapper.mapper;

import com.example.hope_dog.dto.file.AdoptFileDTO;
import com.example.hope_dog.dto.notice.file.FileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdoptFileMapper {

    //입양 파일 등록
    void insertAdoptFile(AdoptFileDTO adoptFileDTO);

    //입양 파일 삭제
    void deleteAdoptFile(AdoptFileDTO adoptFileDTO);

    //입양 파일 불러오기
    List<FileDTO> selectList(Long adoptNo);
}
