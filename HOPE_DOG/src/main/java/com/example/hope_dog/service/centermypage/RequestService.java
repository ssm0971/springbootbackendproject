package com.example.hope_dog.service.centermypage;

import com.example.hope_dog.dto.centermypage.request.*;
import com.example.hope_dog.mapper.centermypage.request.RequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor    //final 필드 생성자 자동 주입
public class RequestService {

    private final RequestMapper requestMapper;

//    봉사 신청 목록 조회
    public List<VolunRequestListDTO> volunRequestList(Long centerMemberNo){
        return requestMapper.volunRequestList(centerMemberNo);
    }

//    봉사 신청서 상세페이지
    public VolunRequestDetailDTO volunRequestDetail(VolunRequestListDTO volunRequestListDTO){
        return requestMapper.volunRequestDetailInfo(volunRequestListDTO);
    }

//    봉사 신청서 수락/거절 처리
    public void updateVolunRequestStatus(Long volunRequestNo, String volunRequsetStatus){
        VolunRequestChoiceDTO volunRequestChoiceDTO = new VolunRequestChoiceDTO(volunRequestNo, volunRequsetStatus);
        requestMapper.volunRequestStatusChoice(volunRequestChoiceDTO);
    }


//    입양 신청 목록 조회
    public List<AdoptRequestListDTO> adoptRequestList(Long centerMemberNo){
        return requestMapper.adoptRequestList(centerMemberNo);
    }

//    입양 신청서 상세페이지
    public AdoptRequestDetailDTO adoptRequestDetail(AdoptRequestListDTO adoptRequestListDTO){
        return requestMapper.adoptRequestDetailInfo(adoptRequestListDTO);
    }

//    입양 신청서 수락/거절 처리
    public void updateAdoptRequestStatus(Long adoptRequestNo, String adoptRequsetStatus){
        AdoptRequestChoiceDTO adoptRequestChoiceDTO = new AdoptRequestChoiceDTO(adoptRequestNo, adoptRequsetStatus);
        requestMapper.adoptRequestStatusChoice(adoptRequestChoiceDTO);
    }


//    임시보호 신청 목록 조회
    public List<ProtectRequestListDTO> protectRequestList(Long centerMemberNo){
        return requestMapper.protectRequestList(centerMemberNo);
    }

//    임시보호 신청서 상세페이지
    public ProtectRequestDetailDTO protectRequestDetail(ProtectRequestListDTO protectRequestListDTO){
        return requestMapper.protectRequestDetailInfo(protectRequestListDTO);
    }

//    임시보호 신청서 수락/거절 처리
    public void updateProtectRequestStatus(Long protectRequestNo, String protectRequestStatus) {
        ProtectRequestChoiceDTO protectRequestChoiceDTO = new ProtectRequestChoiceDTO(protectRequestNo, protectRequestStatus);
        requestMapper.protectRequestStatusChoice(protectRequestChoiceDTO);
    }
}
