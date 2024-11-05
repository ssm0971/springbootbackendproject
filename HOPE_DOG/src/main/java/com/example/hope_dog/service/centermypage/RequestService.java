package com.example.hope_dog.service.centermypage;

import com.example.hope_dog.dto.centermypage.request.AdoptRequestListDTO;
import com.example.hope_dog.dto.centermypage.request.ProtectRequestDetailDTO;
import com.example.hope_dog.dto.centermypage.request.ProtectRequestListDTO;
import com.example.hope_dog.dto.centermypage.request.VolunRequestListDTO;
import com.example.hope_dog.mapper.centermypage.request.RequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor    //final 필드 생성자 자동 주입
public class RequestService {

    private final RequestMapper requestMapper;

    public List<VolunRequestListDTO> volRequestList(Long centerMemberNo){
        List<VolunRequestListDTO> volRequestList = requestMapper.volunRequestList(centerMemberNo);
        return requestMapper.volunRequestList(centerMemberNo);
    }

    public List<AdoptRequestListDTO> adoptRequestList(Long centerMemberNo){
        List<AdoptRequestListDTO> adoptRequestList = requestMapper.adoptRequestList(centerMemberNo);
        return requestMapper.adoptRequestList(centerMemberNo);
    }

    public List<ProtectRequestListDTO> protectRequestList(Long centerMemberNo){
        List<ProtectRequestListDTO> protectRequestList = requestMapper.protectRequestList(centerMemberNo);
        return requestMapper.protectRequestList(centerMemberNo);
    }

    public ProtectRequestDetailDTO protectRequestDetail(Long protectRequestNo){
        ProtectRequestDetailDTO protectRequestDetail = requestMapper.protectRequestDetailInfo(protectRequestNo);
        return requestMapper.protectRequestDetailInfo(protectRequestNo);
    }
}
