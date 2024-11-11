package com.example.hope_dog.service.volun.volun;

import com.example.hope_dog.dto.adopt.adopt.*;
import com.example.hope_dog.dto.page.Criteria;
import com.example.hope_dog.dto.volun.volun.*;
import com.example.hope_dog.mapper.volun.volun.VolunMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class VolunService {
    private final VolunMapper volunMapper;

    //메인 게시글
    public List<VolunMainDTO> getVolunMainList() {
        return volunMapper.main();
    }

    //봉사 전체게시글 조회 + 페이지네이션 포함
    public List<VolunMainDTO> findAll() {
        return volunMapper.selectAll();
    }

    public int findTotal(){
        return volunMapper.selectTotal();
    }

    public List<VolunMainDTO> findAllPage(Criteria criteria){
        return volunMapper.selectAllPage(criteria);
    }

    //봉사 모집중게시글 조회 + 페이지네이션 포함
    public List<VolunMainDTO> findAllKeep() {
        return volunMapper.selectAllKeep();
    }

    public int findTotalKeep(){
        return volunMapper.selectTotalKeep();
    }

    public List<VolunMainDTO> findAllPageKeep(Criteria criteria){
        return volunMapper.selectAllPageKeep(criteria);
    }

    //센터회원상태조회
    public List<VolunMainDTO> centerMemberStatus(Long centerMemberNo){
        return volunMapper.centerMemberStatus(centerMemberNo);
    }

    //게시글 조회
    public List<VolunDetailDTO> getVolunDetail(Long volunNo) {
        return volunMapper.volunDetail(volunNo); // adoptMapper의 메서드 호출
    }

    //댓글불러오기
    public List<VolunCommentDTO> getVolunComment(Long volunNo) {
        return volunMapper.volunComment(volunNo); // adoptMapper의 메서드 호출
    }

    //게시글 작성
    public void registerVolun(VolunWriteDTO volunWriteDTO) {
        // LocalDate를 그대로 사용하기 때문에 변환할 필요 없음
        LocalDate volunPeriodstart = volunWriteDTO.getVolunPeriodstart();
        LocalDate volunPeriodend = volunWriteDTO.getVolunPeriodend();
        LocalDate volunStart = volunWriteDTO.getVolunStart();
        LocalDate volunEnd = volunWriteDTO.getVolunEnd();

        // adoptWriteDTO에 값을 설정할 필요 없음
        // 이미 LocalDate로 저장되어 있으므로 그대로 사용
        volunMapper.volunWrite(volunWriteDTO);
    }

    //봉사글 삭제 처리
    public void volunDelete(VolunDetailDTO volunDetailDTO) {
        volunMapper.volunDelete(volunDetailDTO); // 매퍼 호출
    }

    //봉사글 수정 처리
    public void volunModify(VolunWriteDTO volunWriteDTO) {
        LocalDate volunPeriodstart = volunWriteDTO.getVolunPeriodstart();
        LocalDate volunPeriodend = volunWriteDTO.getVolunPeriodend();
        LocalDate volunStart = volunWriteDTO.getVolunStart();
        LocalDate volunEnd = volunWriteDTO.getVolunEnd();

        volunMapper.volunModify(volunWriteDTO); // 매퍼 호출
    }

    //봉사글 마감처리
    public void volunEnd(VolunDetailDTO volunDetailDTO) {
        volunMapper.volunEnd(volunDetailDTO); // 매퍼 호출
    }

    //봉사글 신고처리
    public void volunContentReport(VolunReportDTO volunReportDTO) {
        volunMapper.volunContentReport(volunReportDTO); // 매퍼 호출
    }

    //봉사신청서 등록
    public void registerRequest(VolunRequestDTO volunRequestDTO) {
        LocalDate requestAge = volunRequestDTO.getVolunRequestAge();

        volunMapper.volunRequest(volunRequestDTO);
    }

    //댓글 등록
    public void volunCommentRegi(VolunCommentDTO volunCommentDTO) {
        volunMapper.volunCommentRegi(volunCommentDTO);
    }

    //댓글 수정
    public void volunCommentModi(VolunCommentDTO volunCommentDTO) {
        volunMapper.volunCommentModi(volunCommentDTO);
    }

    //댓글 삭제
    public void volunCommentDelete(VolunCommentDTO volunCommentDTO) {
        volunMapper.volunCommentDelete(volunCommentDTO);
    }

    //댓글 신고
    public void volunCommentReport(VolunReportDTO volunReportDTO) {
        volunMapper.volunCommentReport(volunReportDTO);
    }
}
