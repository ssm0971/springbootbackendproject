package com.example.hope_dog.mapper.mypage;

import com.example.hope_dog.dto.adopt.adopt.AdoptDetailDTO;
import com.example.hope_dog.dto.member.MemberDTO;
import com.example.hope_dog.dto.member.MemberSessionDTO;
import com.example.hope_dog.dto.mypage.*;
import com.example.hope_dog.dto.notice.NoticeListDTO;
import com.example.hope_dog.dto.notice.NoticeViewDTO;
import com.example.hope_dog.dto.page.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MypageMapper {

    List<MypageDTO> mypageProfile(Long memberNo);

    List<MypageAdoptListDTO> mypageAdoptList(Long memberNo);

    List<MypageProtectDTO> mypageProtectList(Long memberNo);

    List<MypageVolunDTO> mypageVolunList(Long memberNo);

    List<MypagePostsDTO> mypagePostsList(Long memberNo);

//    List<MypageNoteReceiveDTO> mypageNoteReceiveList(Long memberNo);
//
//    List<MypageNoteSendDTO> mypageNoteSendList(Long memberNo);

    //페이지네이션
//    List<MypagePostsDTO> selectAll();
//
//    int selectTotal();
//
//    List<MypagePostsDTO> selectAllPage(Criteria criteria);

    //
//    MypageDTO mypageProfile(@Param("memberNo") Long memberNo);

//    List<NoticeViewDTO> noticeView(Long noticeNo);

    // 사용자 ID로 회원 정보를 가져오는 메서드
//    MemberSessionDTO findMemberById(@Param("memberId") String memberId);
//
//    MemberSessionDTO findMemberByNo(@Param("memberNo") String memberNo);
//
//    MemberSessionDTO findMemberByEmail(@Param("memberEmail") String memberEmail);
//
//    MemberSessionDTO findMemberByName(@Param("memberName") String memberName);
//
//    MemberSessionDTO findMemberByNickname(@Param("memberNickname") String memberNickname);
    //    public class MemberSessionDTO {
//        private Long memberNo;
//        private String memberId;
//        private String memberEmail;
//        private String memberName;
//        private String memberNickname;
//        private String memberLoginStatus;
//        private String memberTwoFactorEnabled;
//    }


}

