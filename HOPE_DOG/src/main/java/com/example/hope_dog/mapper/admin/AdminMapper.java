package com.example.hope_dog.mapper.admin;

import com.example.hope_dog.dto.admin.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AdminMapper {
    Optional<Long> selectId(@Param("adminId") String adminId, @Param("adminPw") String adminPw);

    Optional<AdminSessionDTO> selectLoginInfo(@Param("adminId") String adminId, @Param("adminPw") String adminPw);

    List<AdminMemberDTO> selectMemberList();

    List<AdminMemberDTO> searchMemberByKeyword(@Param("keyword") String keyword);

    AdminMemberDTO selectMemberByNo(@Param("memberNo") Long memberNo);

    void deleteMembers(@Param("memberNoList") List<Long> memberNoList);

    void deleteCenterMembers(@Param("centerMemberNoList") List<Long> centerMemeberNoList);

    List<AdminCenterMemberDTO> selectCenterMemberList();

    AdminCenterMemberDTO selectNotPassedCenterMemberByNo(@Param("centerMemberNo") Long centerMemberNo);

    List<AdminCenterMemberDTO> selectPassedCenterMemberList();

    List<AdminCenterMemberDTO> searchPassedCenterMemberByKeyword(@Param("keyword") String keyword);

    AdminCenterMemberDTO selectPassedCenterMemberByNo(@Param("centerMemberNo") Long centerMemberNo);

    List<AdminReportDTO> selectReportList();

    List<AdminReportDTO> searchReportByKeyword(@Param("keyword") String keyword);

    List<AdminPostDTO> selectPostList();

    List<AdminPostDTO> searchPostByKeyword(@Param("keyword") String keyword);

    AdminPostDTO selectPostDetail(@Param("postType") String postType,@Param("postNo") Long postNo);

    List<AdminCommentDTO> selectCommentListByPostNo(@Param("postType") String postType, @Param("postNo") Long postNo);

    List<AdminCommentDTO> selectCommentList();

    List<AdminCommentDTO> searchCommentByKeyword(@Param("keyword") String keyword);

    List<AdminNoticeDTO> selectNoticeList();

    AdminNoticeDTO selectNoticeDetail(@Param("noticeNo") Long noticeNo);

    List<AdminNoticeDTO> searchNoticeByKeyword(@Param("keyword") String keyword);

    List<AdminAdoptRequestDTO> selectAdoptRequestList();

    List<AdminAdoptRequestDTO> searchAdoptRequestByKeyword(@Param("keyword") String keyword);

    AdminAdoptRequestDTO selectAdoptRequestDetail(@Param("adoptRequestNo") Long adoptRequestNo);

    List<AdminProtectRequestDTO> selectProtectRequestList();

    List<AdminProtectRequestDTO> searchProtectRequestByKeyword(@Param("keyword") String keyword);

    AdminProtectRequestDTO selectProtectRequestDetail(@Param("protectRequestNo") Long protectRequestNo);

    List<AdminVolunRequestDTO> selectVolunRequestList();

    List<AdminVolunRequestDTO> searchVolunRequestByKeyword(@Param("keyword") String keyword);

    AdminVolunRequestDTO selectVolunRequestDetail(@Param("volunNo") Long volunNo);

    List<AdminNoteSendDTO> selectNoteSendList();

    List<AdminNoteReceiveDTO> selectNoteReceiveList();
}