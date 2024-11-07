package com.example.hope_dog.service.adopt.review;

import com.example.hope_dog.dto.adopt.protect.*;
import com.example.hope_dog.dto.adopt.review.*;
import com.example.hope_dog.dto.page.Criteria;

import com.example.hope_dog.mapper.adopt.review.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewMapper reviewMapper;

    //게시글 조회
    public List<ReviewDetailDTO> getReviewDetail(Long reviewNo) {
        return reviewMapper.reviewDetail(reviewNo); // adoptMapper의 메서드 호출
    }

    //후기 전체게시글 조회 + 페이지네이션 포함
    public List<ReviewMainDTO> findAll() {
        return reviewMapper.selectAll();
    }

    public int findTotal(){
        return reviewMapper.selectTotal();
    }

    public List<ReviewMainDTO> findAllPage(Criteria criteria){
        return reviewMapper.selectAllPage(criteria);
    }

    //게시글 작성
    public void registerReviewtion(ReviewWriteDTO reviewWriteDTO) {
        reviewMapper.reviewWrite(reviewWriteDTO);
    }

    //후기글 마감처리
    public void reviewDelete(ReviewDetailDTO reviewDetailDTO) {
        reviewMapper.reviewDelete(reviewDetailDTO); // 매퍼 호출
    }

    //후기글 신고처리
    public void reviewContentReport(ReviewReportDTO reviewReportDTO) {
        reviewMapper.reviewContentReport(reviewReportDTO); // 매퍼 호출
    }

    //댓글불러오기
    public List<ReviewCommentDTO> getReviewComment(Long reviewNo) {
        return reviewMapper.reviewComment(reviewNo); // adoptMapper의 메서드 호출
    }

    //댓글 등록
    public void reviewCommentRegi(ReviewCommentDTO reviewCommentDTO) {
        reviewMapper.reviewCommentRegi(reviewCommentDTO);
    }

    //댓글 수정
    public void reviewCommentModi(ReviewCommentDTO reviewCommentDTO) {
        reviewMapper.reviewCommentModi(reviewCommentDTO);
    }

    //댓글 삭제
    public void reviewCommentDelete(ReviewCommentDTO reviewCommentDTO) {
        reviewMapper.reviewCommentDelete(reviewCommentDTO);
    }

    //댓글 신고
    public void reviewCommentReport(ReviewReportDTO reviewReportDTO) {
        reviewMapper.reviewCommentReport(reviewReportDTO);
    }
}
