package com.example.hope_dog.service.centermypage;

import com.example.hope_dog.dto.centermypage.writeinfo.WriteInfoCommuListDTO;
import com.example.hope_dog.mapper.centermypage.writeinfo.WriteInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WriteInfoService {

    private final WriteInfoMapper writeInfoMapper;

    // 커뮤니티 작성글 조회
    public List<WriteInfoCommuListDTO> commuList(Long centerMemberNO) {
        List<WriteInfoCommuListDTO> commuList = writeInfoMapper.writeInfoCommuList(centerMemberNO);

        // 센터 회원 이름을 각 DTO에 설정
        String centerMemberName = writeInfoMapper.writerInfo(centerMemberNO);
        for (WriteInfoCommuListDTO dto : commuList) {
            dto.setCommuCenterName(centerMemberName);  // 각 DTO에 센터 회원 이름 설정
        }

        return commuList;
    }
}
