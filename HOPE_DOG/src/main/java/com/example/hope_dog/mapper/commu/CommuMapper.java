package com.example.hope_dog.mapper.commu;

import com.example.hope_dog.dto.centerMember.CenterMemberDTO;
import com.example.hope_dog.dto.commu.CommuCommentDTO;
import com.example.hope_dog.dto.commu.CommuDTO;
import com.example.hope_dog.dto.commu.CommuDetailDTO;
import com.example.hope_dog.dto.member.MemberDTO;
import com.example.hope_dog.dto.page.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
//    List<CommuDTO> findCateByGood(@Param("cate") String cate);

    // 커뮤니티 검색
    List<CommuDTO> searchCommu(@Param("params") Map<String, Object> params);

//게시글 상세

    // 게시글 번호로 게시글 상세 정보 조회
    CommuDetailDTO selectCommuByNo(@Param("commuNo") Long commuNo);

    // 댓글 리스트 조회
    List<CommuCommentDTO> selectCommentsByCommuNo(@Param("commuNo") Long commuNo);

    //글 작성
    void insertWrite(CommuDTO commuDTO);




    //일반회원 조회
    MemberDTO commuMemberByNo(Long memberNo);
    //센터회원 조회
    CenterMemberDTO commuCenterMemberByNo(Long centerMemberNo);
}
