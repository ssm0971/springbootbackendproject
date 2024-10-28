package com.example.hope_dog.mapper.admin;

import com.example.hope_dog.dto.admin.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AdminMapper {
    Optional<Long> selectId(@Param("adminId") String adminId, @Param("adminPw") String adminPw);

    Optional<AdminSessionDTO> selectLoginInfo(@Param("adminId") String adminId, @Param("adminPw") String adminPw);

    List<AdminMemberDTO> selectMemberList();

    List<AdminReportDTO> selectReportList();

    List<AdminPostDTO> selectPostList();

    List<AdminCommentDTO> selectCommentList();
}