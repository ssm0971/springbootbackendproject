package com.example.hope_dog.service.admin;

import com.example.hope_dog.dto.admin.*;
import com.example.hope_dog.mapper.admin.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminService {
    private final AdminMapper adminMapper;

    public Long selectId(String adminId, String adminPw) {
        return adminMapper.selectId(adminId, adminPw).orElseThrow(() -> new IllegalStateException("존재하지 않는 관리자 정보"));
    }

    public AdminSessionDTO findLoginInfo(String adminId, String adminPw) {
        return adminMapper.selectLoginInfo(adminId, adminPw).orElseThrow(() -> new IllegalStateException("존재하지 않는 관리자 정보"));
    }

    public List<AdminMemberDTO> selectMemberList(){
        return adminMapper.selectMemberList();
    }

    public List<AdminReportDTO> selectReportList(){
        return adminMapper.selectReportList();
    }

    public List<AdminPostDTO> selectPostList(){
        return adminMapper.selectPostList();
    }

    public List<AdminCommentDTO> selectCommentList(){
        return adminMapper.selectCommentList();
    }
}
