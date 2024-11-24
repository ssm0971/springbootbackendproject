package com.example.hope_dog.mapper.adopt.adopt;

import com.example.hope_dog.dto.adopt.adopt.*;
import com.example.hope_dog.dto.page.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdoptMapper {
    //입양/임시보호/후기 메인
    List<MainDTO> main();

    //입양 상세글
    List<AdoptDetailDTO> adoptDetail(Long adoptNo);
    
    //입양 게시글(페이지네이션 포함)
    List<AdoptMainDTO> selectAll();

    int selectTotal();

    List<AdoptMainDTO> selectAllPage(Criteria criteria);

    //입양 모집중 게시글(페이지네이션 포함)
    List<AdoptMainDTO> selectAllKeep();

    int selectTotalKeep();

    List<AdoptMainDTO> selectAllPageKeep(Criteria criteria);
    
    //센터회원 상태조회
    List<AdoptMainDTO> centerMemberStatus(Long centerMemberNo);

    //입양글작성
    void adoptWrite(AdoptWriteDTO adoptWriteDTO);

    //입양글수정
    void adoptModify(AdoptWriteDTO adoptWriteDTO);

    //입양글마감처리
    void adoptEnd(AdoptDetailDTO adoptDetailDTO);

    //입양글삭제처리
    void adoptDelete(AdoptDetailDTO adoptDetailDTO);

    //입양글신고처리
    void adoptContentReport(AdoptReportDTO adoptReportDTO);

    //댓글불러오기
    List<AdoptCommentDTO> adoptComment(Long adoptNo);

    //댓글 등록
    void adoptCommentRegi(AdoptCommentDTO adoptCommentDTO);

    //댓글 수정
    void adoptCommentModi(AdoptCommentDTO adoptCommentDTO);

    //댓글 삭제
    void adoptCommentDelete(AdoptCommentDTO adoptCommentDTO);

    //댓글 신고
    void adoptCommentReport(AdoptReportDTO adoptReportDTO);

    //입양 신청서
    void adoptRequest(AdoptRequestDTO adoptRequestDTO);


}
