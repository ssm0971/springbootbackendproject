package com.example.hope_dog.service.adopt.protect;

import com.example.hope_dog.dto.adopt.adopt.*;
import com.example.hope_dog.dto.adopt.protect.*;
import com.example.hope_dog.dto.page.Criteria;
import com.example.hope_dog.mapper.adopt.protect.ProtectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProtectService {
    private final ProtectMapper protectMapper;

    //게시글 조회
    public List<ProtectDetailDTO> getProtectDetail(Long protectNo) {
        return protectMapper.protectDetail(protectNo); // adoptMapper의 메서드 호출
    }

    //임시보호 전체게시글 조회 + 페이지네이션 포함
    public List<ProtectMainDTO> findAll() {
        return protectMapper.selectAll();
    }

    public int findTotal(){
        return protectMapper.selectTotal();
    }

    public List<ProtectMainDTO> findAllPage(Criteria criteria){
        return protectMapper.selectAllPage(criteria);
    }

    //임시보호 모집중 게시글 조회 + 페이지네이션 포함
    public List<ProtectMainDTO> findAllKeep() {
        return protectMapper.selectAllKeep();
    }

    public int findTotalKeep(){
        return protectMapper.selectTotalKeep();
    }

    public List<ProtectMainDTO> findAllPageKeep(Criteria criteria){
        return protectMapper.selectAllPageKeep(criteria);
    }


    //게시글 작성
    public void registerProtection(ProtectWriteDTO protectWriteDTO) {
        // LocalDate를 그대로 사용하기 때문에 변환할 필요 없음
        LocalDate periodStart = protectWriteDTO.getProtectPeriodstart();
        LocalDate periodEnd = protectWriteDTO.getProtectPeriodend();
        LocalDate adoptBirth = protectWriteDTO.getProtectBirth();

        // protectWriteDTO에 값을 설정할 필요 없음
        // 이미 LocalDate로 저장되어 있으므로 그대로 사용
        protectMapper.protectWrite(protectWriteDTO);
    }

    //임시보호글 마감처리
    public void protectEnd(ProtectDetailDTO protectDetailDTO) {
        protectMapper.protectEnd(protectDetailDTO); // 매퍼 호출
    }

    //임시보호글 마감처리
    public void protectDelete(ProtectDetailDTO protectDetailDTO) {
        protectMapper.protectDelete(protectDetailDTO); // 매퍼 호출
    }

    //임시보호글 신고처리
    public void protectContentReport(ProtectReportDTO protectReportDTO) {
        protectMapper.protectContentReport(protectReportDTO); // 매퍼 호출
    }

    //댓글불러오기
    public List<ProtectCommentDTO> getProtectComment(Long protectNo) {
        return protectMapper.protectComment(protectNo); // adoptMapper의 메서드 호출
    }

    //댓글 등록
    public void protectCommentRegi(ProtectCommentDTO protectCommentDTO) {
        protectMapper.protectCommentRegi(protectCommentDTO);
    }

    //댓글 수정
    public void protectCommentModi(ProtectCommentDTO protectCommentDTO) {
        protectMapper.protectCommentModi(protectCommentDTO);
    }

    //댓글 삭제
    public void protectCommentDelete(ProtectCommentDTO protectCommentDTO) {
        protectMapper.protectCommentDelete(protectCommentDTO);
    }

    //댓글 신고
    public void protectCommentReport(ProtectReportDTO protectReportDTO) {
        protectMapper.protectCommentReport(protectReportDTO);
    }

    //임시보호신청서 등록
    public void registerRequest(ProtectRequestDTO protectRequestDTO) {
        LocalDate requestAge = protectRequestDTO.getProtectRequestAge();

        protectMapper.protectRequest(protectRequestDTO);
    }
}
