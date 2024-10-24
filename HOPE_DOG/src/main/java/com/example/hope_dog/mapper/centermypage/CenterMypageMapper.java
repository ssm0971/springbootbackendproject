package com.example.hope_dog.mapper.centermypage;

import com.example.hope_dog.dto.centermypage.CenterMemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CenterMypageMapper {
    void MyProfile(Long userId);


}
