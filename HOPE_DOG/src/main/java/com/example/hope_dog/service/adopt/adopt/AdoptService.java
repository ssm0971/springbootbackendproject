package com.example.hope_dog.service.adopt.adopt;

import com.example.hope_dog.dto.adopt.adopt.AdoptMainDTO;
import com.example.hope_dog.mapper.adopt.adopt.AdoptMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdoptService {

    private final AdoptMapper adoptMapper;

    public List<AdoptMainDTO> getAdoptList() {
        return adoptMapper.adoptMain();
    }
}