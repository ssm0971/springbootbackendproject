package com.example.hope_dog.mapper.centermypage.request;

import com.example.hope_dog.dto.centermypage.request.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RequestMapper {

//    봉사신청서 목록 조회
    List<VolunRequestListDTO> volunRequestList(Long centerMemberNo);

//    입양신청서 목록 조회
    List<AdoptRequestListDTO> adoptRequestList(Long centerMemberNo);

//    임시보호신청서 목록 조회
    List<ProtectRequestListDTO> protectRequestList(Long centerMemberNo);

//    임시보호신청서 상세페이지 정보 조회
    ProtectRequestDetailDTO protectRequestDetailInfo(Long protectRequestNo);

//    임시보호신청서 수락/거절
    void protectRequestStatusChoice(ProtectRequestChoiceDTO protectRequestChoiceDTO);

}
