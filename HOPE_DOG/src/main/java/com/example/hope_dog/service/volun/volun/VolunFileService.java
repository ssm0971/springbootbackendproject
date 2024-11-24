package com.example.hope_dog.service.volun.volun;

import com.example.hope_dog.dto.volun.volun.VolunFileDTO;
import com.example.hope_dog.mapper.volun.volun.VolunFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class VolunFileService {
    private final VolunFileMapper volunFileMapper;

    public void registerFile(VolunFileDTO volunFileDTO) {
        volunFileMapper.insertFile(volunFileDTO);
    }

    public void removeFile(Long fileNo){
        volunFileMapper.deleteFile(fileNo);
    }

    public List<VolunFileDTO> findList(Long fileNo){
        return volunFileMapper.selectList(fileNo);
    }
}
