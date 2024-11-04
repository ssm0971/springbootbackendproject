package com.example.hope_dog.service.admin;

import com.example.hope_dog.dto.admin.*;
import com.example.hope_dog.mapper.admin.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminService {
    private final AdminMapper adminMapper;

//    public Long selectId(String adminId, String adminPw) {
//        return adminMapper.selectId(adminId, adminPw).orElseThrow(() -> new IllegalStateException("존재하지 않는 관리자 정보"));
//    }

    public AdminSessionDTO findLoginInfo(String adminId, String adminPw) { return adminMapper.selectLoginInfo(adminId, adminPw).orElseThrow(() -> new IllegalStateException("존재하지 않는 관리자 정보")); }

    public List<AdminMemberDTO> selectMemberList(){
        return adminMapper.selectMemberList();
    }

    public List<AdminMemberDTO> searchMemberByKeyword(String keyword){ return adminMapper.searchMemberByKeyword(keyword); }

    public AdminMemberDTO selectMemberByNo(Long memberNo) {
        return adminMapper.selectMemberByNo(memberNo);
    }

    public void deleteMember(List<Long> memberNoList){ adminMapper.deleteMembers(memberNoList); }

    public void deleteCenterMember(List<Long> memberNoList){ adminMapper.deleteCenterMembers(memberNoList); }

    public List<AdminCenterMemberDTO> selectCenterMemberList(){
        return adminMapper.selectCenterMemberList();
    }

    public AdminCenterMemberDTO selectNotPassedCenterMemberByNo(Long centerMemberNo) { return adminMapper.selectNotPassedCenterMemberByNo(centerMemberNo); }

    public List<AdminCenterMemberDTO> selectPassedCenterMemberList(){ return adminMapper.selectPassedCenterMemberList(); }

    public List<AdminCenterMemberDTO> searchPassedCenterMemberByKeyword(String keyword){ return adminMapper.searchPassedCenterMemberByKeyword(keyword); }

    public AdminCenterMemberDTO selectPassedCenterMemberByNo(Long centerMemberNo) { return adminMapper.selectPassedCenterMemberByNo(centerMemberNo); }

    public List<AdminReportDTO> selectReportList(){
        return adminMapper.selectReportList();
    }

    public List<AdminReportDTO> searchReportByKeyword(String keyword){ return adminMapper.searchReportByKeyword(keyword); }

    public List<AdminPostDTO> selectPostList(){
        return adminMapper.selectPostList();
    }

    public List<AdminPostDTO> searchPostByKeyword(String keyword){ return adminMapper.searchPostByKeyword(keyword); }

    public void deletePost(AdminPostDTO item){adminMapper.deletePost((AdminPostDTO) item);}

    public void deleteComment(AdminCommentDTO item){adminMapper.deleteComment((AdminCommentDTO) item);}

    public AdminPostDTO selectPostDetail(String postType, Long postNo){ return adminMapper.selectPostDetail(postType, postNo); }

    public void deletePostDetail(AdminPostDTO item){adminMapper.deletePostDetail((AdminPostDTO) item);}

    public void deleteCommentDetail(AdminCommentDTO item){adminMapper.deleteCommentDetail((AdminCommentDTO) item);}

    public List<AdminCommentDTO> selectCommentList(){ return adminMapper.selectCommentList(); }

    public List<AdminCommentDTO> searchCommentByKeyword(String keyword){ return adminMapper.searchCommentByKeyword(keyword); }

    public List<AdminCommentDTO> selectCommentListByPostNo(String postType, Long postNo){ return adminMapper.selectCommentListByPostNo(postType, postNo); }

    public List<AdminNoticeDTO> selectNoticeList(){
        return adminMapper.selectNoticeList();
    }

    public AdminNoticeDTO selectNoticeDetail(Long noticeNo){ return adminMapper.selectNoticeDetail(noticeNo); }

    public List<AdminNoticeDTO> searchNoticeByKeyword(String keyword){ return adminMapper.searchNoticeByKeyword(keyword); }

    public List<AdminAdoptRequestDTO> selectAdoptRequestList(){
        return adminMapper.selectAdoptRequestList();
    }

    public List<AdminAdoptRequestDTO> searchAdoptRequestByKeyword(String keyword){ return adminMapper.searchAdoptRequestByKeyword(keyword); }

    public AdminAdoptRequestDTO selectAdoptRequestDetail(Long adoptRequestNo){ return adminMapper.selectAdoptRequestDetail(adoptRequestNo); }

    public List<AdminProtectRequestDTO> selectProtectRequestList(){
        return adminMapper.selectProtectRequestList();
    }

    public List<AdminProtectRequestDTO> searchProtectRequestByKeyword(String keyword){ return adminMapper.searchProtectRequestByKeyword(keyword); }

    public AdminProtectRequestDTO selectProtectRequestDetail(Long protectRequestNo){return adminMapper.selectProtectRequestDetail(protectRequestNo);}

    public List<AdminVolunRequestDTO> selectVolunRequestList(){
        return adminMapper.selectVolunRequestList();
    }

    public List<AdminVolunRequestDTO> searchVolunRequestList(String keyword){ return adminMapper.searchVolunRequestByKeyword(keyword); }

    public AdminVolunRequestDTO selectVolunRequestDetail(Long volunNo){ return adminMapper.selectVolunRequestDetail(volunNo); }

    public List<AdminNoteSendDTO> selectNoteSendList(){
        return adminMapper.selectNoteSendList();
    }

    public List<AdminNoteReceiveDTO> selectNoteReceiveList(){
        return adminMapper.selectNoteReceiveList();
    }

    public AdminNoteReceiveDTO selectNoteReceiveDetail(Long noteboxReceiveNo){ return adminMapper.selectNoteReceiveDetail(noteboxReceiveNo);}

    public AdminNoteSendDTO selectNoteSendDetail(Long noteboxSendNo){ return adminMapper.selectNoteSendDetail(noteboxSendNo);}

    public void insertNoteWriteReceive(String title, String content, String receiver){adminMapper.insertNoteWriteReceive(title, content, receiver); }

    public void insertNoteWriteSend(String title, String content, String receiver){adminMapper.insertNoteWriteSend(title, content, receiver); }

    public List<AdminNoteReceiveDTO> searchNoteInByKeyword(String keyword){ return adminMapper.searchNoteInByKeyword(keyword);}

    public List<AdminNoteSendDTO> searchNoteOutByKeyword(String keyword){ return adminMapper.searchNoteOutByKeyword(keyword); }
}
