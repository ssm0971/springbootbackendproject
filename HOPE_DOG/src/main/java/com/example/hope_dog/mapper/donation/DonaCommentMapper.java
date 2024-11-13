package com.example.hope_dog.mapper.donation;

import com.example.hope_dog.dto.donation.DonaCommentListDTO;
//import com.example.hope_dog.dto.donation.DonaCommentReportDTO;
import com.example.hope_dog.dto.donation.DonaCommentReportDTO;
import com.example.hope_dog.dto.donation.DonaCommentUpdateDTO;
import com.example.hope_dog.dto.donation.DonaCommentWriteDTO;
import com.example.hope_dog.dto.page.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DonaCommentMapper {

    void insertComment(DonaCommentWriteDTO donaCommentWriteDTO);

    List<DonaCommentListDTO> selectCommentList(Long donaNo);

    void updateComment(DonaCommentUpdateDTO donaCommentWriteDTO);

    void deleteComment(Long donaNo);

    List<DonaCommentListDTO> selectCommentSlice(@Param("criteria") Criteria criteria, @Param("donaNo") Long donaNo);

    //댓글신고
    void insertCommentReport(DonaCommentReportDTO donaCommentReportDTO);
}
