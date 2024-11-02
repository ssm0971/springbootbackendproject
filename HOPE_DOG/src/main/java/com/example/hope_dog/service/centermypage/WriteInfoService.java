package com.example.hope_dog.service.centermypage;

import com.example.hope_dog.dto.centermypage.writeinfo.WriteInfoAdoptListDTO;
import com.example.hope_dog.dto.centermypage.writeinfo.WriteInfoCommuListDTO;
import com.example.hope_dog.dto.centermypage.writeinfo.WriteInfoVolListDTO;
import com.example.hope_dog.mapper.centermypage.writeinfo.WriteInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WriteInfoService {

    private final WriteInfoMapper writeInfoMapper;

    // 커뮤니티 작성글 목록 조회
    public List<WriteInfoCommuListDTO> commuList(Long centerMemberNO) {
        List<WriteInfoCommuListDTO> commuList = writeInfoMapper.writeInfoCommuList(centerMemberNO);

        // 센터 회원 이름을 각 DTO에 설정
        String centerMemberName = writeInfoMapper.writerInfo(centerMemberNO);
        for (WriteInfoCommuListDTO dto : commuList) {
            dto.setCenterName(centerMemberName);  // 각 DTO에 센터 회원 이름 설정
        }

        return commuList;
    }

    // 봉사 작성글 목록 조회
    public List<WriteInfoVolListDTO> volunList(Long centerMemberNO) {
        List<WriteInfoVolListDTO> volList = writeInfoMapper.writeInfoVolList(centerMemberNO);

        String centerMemberName = writeInfoMapper.writerInfo(centerMemberNO);
        for (WriteInfoVolListDTO dto : volList) {
            dto.setCenterName(centerMemberName);
        }

        return volList;
    }

    //입양 작성글 목록 조회
    public List<WriteInfoAdoptListDTO> adoptList(Long centerMemberNO) {
        List<WriteInfoAdoptListDTO> adoptList = writeInfoMapper.writeInfoAdoptList(centerMemberNO);

        String centerMemberName = writeInfoMapper.writerInfo(centerMemberNO);
        for (WriteInfoAdoptListDTO dto : adoptList) {
            dto.setCenterName(centerMemberName);
        }

        return adoptList;
    }
}
