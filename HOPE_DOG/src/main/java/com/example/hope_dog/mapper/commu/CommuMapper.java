package com.example.hope_dog.mapper.commu;

import com.example.hope_dog.dto.centerMember.CenterMemberDTO;
import com.example.hope_dog.dto.commu.CommuCommentDTO;
import com.example.hope_dog.dto.commu.CommuDTO;
import com.example.hope_dog.dto.commu.CommuDetailDTO;
import com.example.hope_dog.dto.commu.CommuReportDTO;
import com.example.hope_dog.dto.member.MemberDTO;
import com.example.hope_dog.dto.page.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Map;

@Mapper
public interface CommuMapper {
    //커뮤니티 게시판 목록
    List<CommuDTO> commuCatalog();

    //카테고리별 게시글 조회
    List<CommuDTO> findCate(@Param("cate")String cate);

    //커뮤니티 클릭시마다 조회수 +1
    void commuGood(CommuDTO commuDTO);

    //커뮤니티 인기 카테고리
    List<CommuDTO> findCateByGood();

    // 커뮤니티 검색
    List<CommuDetailDTO> commuSearch(@Param("commuTitle") String commuTitle,
                               @Param("memberNickname")String memberNickname,
                               @Param("centerMemberName") String centerMemberName);



//게시글 상세

    // 게시글 번호로 게시글 상세 정보 조회
    List<CommuDetailDTO> selectCommuByNo(Long commuNo);


    //게시글 작성
    void insertWrite(CommuDTO commuDTO);

//    게시글 수정
    void commuModify(CommuDetailDTO commuDetailDTO);

    //게시글삭제
    void commuDelete(CommuDetailDTO commuDetailDTO);

    //게시글 신고
    void commuReport(CommuReportDTO commuReportDTO);



    //댓글 리스트 조회
    List<CommuCommentDTO>commuComment(Long commuNo);

    //댓글 등록
    void commuCommentRegi(CommuCommentDTO commuCommentDTO);

    //댓글 수정
    void commuCommentModi(CommuCommentDTO commuCommentDTO);

    //댓글 삭제
    void commuCommentDelete(CommuCommentDTO commuCommentDTO);

    //댓글 신고
    void commuCommentReport(CommuReportDTO commuReportDTO);



    //일반회원 조회
    MemberDTO commuMemberByNo(Long memberNo);
    //센터회원 조회
    CenterMemberDTO commuCenterMemberByNo(Long centerMemberNo);
}
