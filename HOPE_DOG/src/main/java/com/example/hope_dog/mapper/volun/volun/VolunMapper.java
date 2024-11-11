package com.example.hope_dog.mapper.volun.volun;

import com.example.hope_dog.dto.adopt.adopt.*;
import com.example.hope_dog.dto.page.Criteria;
import com.example.hope_dog.dto.volun.volun.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VolunMapper {

    //봉사메인
    List<VolunMainDTO> main();

    //봉사 게시글(페이지네이션 포함)
    List<VolunMainDTO> selectAll();

    int selectTotal();

    List<VolunMainDTO> selectAllPage(Criteria criteria);

    //센터회원 상태조회
    List<VolunMainDTO> centerMemberStatus(Long centerMemberNo);

    //봉사 모집중인 게시글(페이지네이션 포함)
    List<VolunMainDTO> selectAllKeep();

    int selectTotalKeep();

    List<VolunMainDTO> selectAllPageKeep(Criteria criteria);

    //봉사 상세글
    List<VolunDetailDTO> volunDetail(Long volunNo);

    //댓글불러오기
    List<VolunCommentDTO> volunComment(Long volunNo);

    //봉사글작성
    void volunWrite(VolunWriteDTO volunWriteDTO);

    //봉사글삭제처리
    void volunDelete(VolunDetailDTO volunDetailDTO);

    //봉사글수정
    void volunModify(VolunWriteDTO volunWriteDTO);

    //봉사글마감처리
    void volunEnd(VolunDetailDTO volunDetailDTO);

    //봉사글신고처리
    void volunContentReport(VolunReportDTO volunReportDTO);

    //입양 신청서
    void volunRequest(VolunRequestDTO volunRequestDTO);

    //댓글 등록
    void volunCommentRegi(VolunCommentDTO volunCommentDTO);

    //댓글 수정
    void volunCommentModi(VolunCommentDTO volunCommentDTO);

    //댓글 삭제
    void volunCommentDelete(VolunCommentDTO volunCommentDTO);

    //댓글 신고
    void volunCommentReport(VolunReportDTO volunReportDTO);


}
