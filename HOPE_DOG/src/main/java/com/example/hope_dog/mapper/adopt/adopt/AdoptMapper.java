package com.example.hope_dog.mapper.adopt.adopt;

import com.example.hope_dog.dto.adopt.adopt.*;
import com.example.hope_dog.dto.page.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    //입양글작성
    void adoptWrite(AdoptWriteDTO adoptWriteDTO);

    //입양글마감처리
    void adoptEnd(AdoptDetailDTO adoptDetailDTO);

    //입양글삭제처리
    void adoptDelete(AdoptDetailDTO adoptDetailDTO);

    //입양글신고처리
    void adoptReport(AdoptReportDTO adoptReportDTO);

    //댓글불러오기
    List<AdoptCommentDTO> adoptComment(Long adoptNo);

    //입양 신청서
    void adoptRequest(AdoptRequestDTO adoptRequestDTO);


}
