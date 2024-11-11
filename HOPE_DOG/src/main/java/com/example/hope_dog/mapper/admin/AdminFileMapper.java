package com.example.hope_dog.mapper.admin;

import com.example.hope_dog.dto.admin.AdminCenterFileDTO;
import com.example.hope_dog.dto.admin.AdminFileDTO;
import com.example.hope_dog.dto.admin.AdminPostDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminFileMapper {
    void insertFile(@Param("file") AdminFileDTO file);

    List<AdminFileDTO> selectFileListByNoticeNo(@Param("noticeNo") Long noticeNo);

    AdminFileDTO selectFileByNo(@Param("fileNo") Long fileNo);

    void deleteFileByNoticeNo(@Param("noticeNo") Long noticeNo);

    void deleteFileByFileNo(@Param("fileNo") Long fileNo);

    List<AdminFileDTO> selectFileListByPostNo(@Param("post")AdminPostDTO post);

    AdminCenterFileDTO selectFileByCenterMemberNo(@Param("centerMemberNo") Long centerMemberNo);
}