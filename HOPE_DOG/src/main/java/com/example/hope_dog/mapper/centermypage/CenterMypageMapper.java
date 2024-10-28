package com.example.hope_dog.mapper.centermypage;

import com.example.hope_dog.dto.centermypage.CenterProfileDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CenterMypageMapper {

    // 프로필 조회 메서드
    CenterProfileDTO centerProfile(Long centerMemberNo);
}