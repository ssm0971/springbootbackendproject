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

    public List<AdoptDetailDTO> getAdoptDetail(Long adoptNo) {
        return adoptMapper.adoptDetail(adoptNo); // adoptMapper의 메서드 호출
    }

    //페이지네이션 관련 service
    public List<AdoptMainDTO> findAll() {
        return adoptMapper.selectAll();
    }

    public int findTotal(){
        return adoptMapper.selectTotal();
    }

    public List<AdoptMainDTO> findAllPage(Criteria criteria){
        return adoptMapper.selectAllPage(criteria);
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

    //댓글불러오기
    public List<AdoptCommentDTO> getAdoptComment(Long adoptNo) {
        return adoptMapper.adoptComment(adoptNo); // adoptMapper의 메서드 호출
    }


}

