package com.example.hope_dog.mapper.adopt.review;

import com.example.hope_dog.dto.adopt.protect.*;
import com.example.hope_dog.dto.adopt.review.*;
import com.example.hope_dog.dto.page.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    //후기 상세글
    List<ReviewDetailDTO> reviewDetail(Long reviewNo);

    //후기 게시글(페이지네이션 포함)
    List<ReviewMainDTO> selectAll();

    int selectTotal();

    List<ReviewMainDTO> selectAllPage(Criteria criteria);

    //후기글작성
    void reviewWrite(ReviewWriteDTO reviewWriteDTO);

    //후기글삭제처리
    void reviewDelete(ReviewDetailDTO reviewDetailDTO);

    //후기글신고처리
    void reviewContentReport(ReviewReportDTO reviewReportDTO);

    //댓글불러오기
    List<ReviewCommentDTO> reviewComment(Long reviewNo);

    //댓글 등록
    void reviewCommentRegi(ReviewCommentDTO reviewCommentDTO);

    //댓글 수정
    void reviewCommentModi(ReviewCommentDTO reviewCommentDTO);

    //댓글 삭제
    void reviewCommentDelete(ReviewCommentDTO reviewCommentDTO);

    //댓글 신고
    void reviewCommentReport(ReviewReportDTO reviewReportDTO);
}
