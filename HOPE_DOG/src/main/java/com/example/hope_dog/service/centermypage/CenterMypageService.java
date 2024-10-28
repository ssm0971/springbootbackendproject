package com.example.hope_dog.service.centermypage;

import com.example.hope_dog.dto.centermypage.CenterProfileDTO;
import com.example.hope_dog.mapper.centermypage.CenterMypageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CenterMypageService {
    private final CenterMypageMapper centerMypageMapper;

    // 프로필 조회 메서드 추가
    public CenterProfileDTO centerProfile(Long centerMemberNo) {
        return centerMypageMapper.centerProfile(centerMemberNo);
    }
}
