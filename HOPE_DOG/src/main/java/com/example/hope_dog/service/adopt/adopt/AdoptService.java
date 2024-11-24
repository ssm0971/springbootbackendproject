package com.example.hope_dog.service.adopt.adopt;

import com.example.hope_dog.dto.adopt.adopt.*;
import com.example.hope_dog.dto.page.Criteria;
import com.example.hope_dog.mapper.adopt.adopt.AdoptMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdoptService {

    private final AdoptMapper adoptMapper;

    public List<MainDTO> getMainList() {
        return adoptMapper.main();
    }

    //게시글 조회
    public List<AdoptDetailDTO> getAdoptDetail(Long adoptNo) {
        return adoptMapper.adoptDetail(adoptNo); // adoptMapper의 메서드 호출
    }

    //입양 전체게시글 조회 + 페이지네이션 포함
    public List<AdoptMainDTO> findAll() {
        return adoptMapper.selectAll();
    }

    public int findTotal(){
        return adoptMapper.selectTotal();
    }

    public List<AdoptMainDTO> findAllPage(Criteria criteria){
        return adoptMapper.selectAllPage(criteria);
    }

    //입양 모집중 게시글 조회 + 페이지네이션 포함
    public List<AdoptMainDTO> findAllKeep() {
        return adoptMapper.selectAllKeep();
    }

    public int findTotalKeep(){
        return adoptMapper.selectTotalKeep();
    }

    public List<AdoptMainDTO> findAllPageKeep(Criteria criteria){
        return adoptMapper.selectAllPageKeep(criteria);
    }

    //센터회원상태조회
    public List<AdoptMainDTO> centerMemberStatus(Long centerMemberNo){
        return adoptMapper.centerMemberStatus(centerMemberNo);
    }

    
    //게시글 작성
    public void registerAdoption(AdoptWriteDTO adoptWriteDTO) {
        // LocalDate를 그대로 사용하기 때문에 변환할 필요 없음
        LocalDate periodStart = adoptWriteDTO.getAdoptPeriodstart();
        LocalDate periodEnd = adoptWriteDTO.getAdoptPeriodend();
        LocalDate adoptBirth = adoptWriteDTO.getAdoptBirth();

        // adoptWriteDTO에 값을 설정할 필요 없음
        // 이미 LocalDate로 저장되어 있으므로 그대로 사용
        adoptMapper.adoptWrite(adoptWriteDTO);
    }

    //입양글 마감처리
    public void adoptEnd(AdoptDetailDTO adoptDetailDTO) {
        adoptMapper.adoptEnd(adoptDetailDTO); // 매퍼 호출
    }

    //입양글 삭제 처리
    public void adoptDelete(AdoptDetailDTO adoptDetailDTO) {
        adoptMapper.adoptDelete(adoptDetailDTO); // 매퍼 호출
    }

    //입양글 수정 처리
    public void adoptModify(AdoptWriteDTO adoptWriteDTO) {
        LocalDate periodStart = adoptWriteDTO.getAdoptPeriodstart();
        LocalDate periodEnd = adoptWriteDTO.getAdoptPeriodend();
        LocalDate adoptBirth = adoptWriteDTO.getAdoptBirth();

        adoptMapper.adoptModify(adoptWriteDTO); // 매퍼 호출
    }

    //입양글 신고처리
    public void adoptContentReport(AdoptReportDTO adoptReportDTO) {
        adoptMapper.adoptContentReport(adoptReportDTO); // 매퍼 호출
    }

    //댓글불러오기
    public List<AdoptCommentDTO> getAdoptComment(Long adoptNo) {
        return adoptMapper.adoptComment(adoptNo); // adoptMapper의 메서드 호출
    }

    //댓글 등록
    public void adoptCommentRegi(AdoptCommentDTO adoptCommentDTO) {
        adoptMapper.adoptCommentRegi(adoptCommentDTO);
    }
    
    //댓글 수정
    public void adoptCommentModi(AdoptCommentDTO adoptCommentDTO) {
        adoptMapper.adoptCommentModi(adoptCommentDTO);
    }

    //댓글 삭제
    public void adoptCommentDelete(AdoptCommentDTO adoptCommentDTO) {
        adoptMapper.adoptCommentDelete(adoptCommentDTO);
    }
    
    //댓글 신고
    public void adoptCommentReport(AdoptReportDTO adoptReportDTO) {
        adoptMapper.adoptCommentReport(adoptReportDTO);
    }

    //입양신청서 등록
    public void registerRequest(AdoptRequestDTO adoptRequestDTO) {
        LocalDate requestAge = adoptRequestDTO.getAdoptRequestAge();

        adoptMapper.adoptRequest(adoptRequestDTO);
    }

}

