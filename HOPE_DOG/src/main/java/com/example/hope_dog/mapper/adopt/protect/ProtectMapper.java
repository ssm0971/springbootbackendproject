package com.example.hope_dog.mapper.adopt.protect;

import com.example.hope_dog.dto.adopt.adopt.*;
import com.example.hope_dog.dto.adopt.protect.*;
import com.example.hope_dog.dto.page.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProtectMapper {
    //임시보호 상세글
    List<ProtectDetailDTO> protectDetail(Long protectNo);

    //임시보호 게시글(페이지네이션 포함)
    List<ProtectMainDTO> selectAll();

    int selectTotal();

    List<ProtectMainDTO> selectAllPage(Criteria criteria);

    //임시보호 모집중 게시글(페이지네이션 포함)
    List<ProtectMainDTO> selectAllKeep();

    int selectTotalKeep();

    List<ProtectMainDTO> selectAllPageKeep(Criteria criteria);

    //임시보호글작성
    void protectWrite(ProtectWriteDTO protectWriteDTO);

    //임시보호글마감처리
    void protectEnd(ProtectDetailDTO protectDetailDTO);

    //임시보호글삭제처리
    void protectDelete(ProtectDetailDTO protectDetailDTO);

    //임시보호글신고처리
    void protectContentReport(ProtectReportDTO protectReportDTO);

    //댓글불러오기
    List<ProtectCommentDTO> protectComment(Long protectNo);

    //댓글 등록
    void protectCommentRegi(ProtectCommentDTO protectCommentDTO);

    //댓글 수정
    void protectCommentModi(ProtectCommentDTO protectCommentDTO);

    //댓글 삭제
    void protectCommentDelete(ProtectCommentDTO protectCommentDTO);

    //댓글 신고
    void protectCommentReport(ProtectReportDTO protectReportDTO);

    //임시보호 신청서
    void protectRequest(ProtectRequestDTO protectRequestDTO);
}
