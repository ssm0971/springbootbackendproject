package com.example.hope_dog.mapper.notice.file;

import com.example.hope_dog.dto.notice.file.FileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {
//    void insertFile(FileDTO fileDTO);

//    void deleteFile(Long boardId);

    List<FileDTO> selectList(Long noticeNo);
}
