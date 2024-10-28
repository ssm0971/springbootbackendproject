package com.example.hope_dog.service.mypage;

import com.example.hope_dog.dto.member.MemberSessionDTO;
import com.example.hope_dog.mapper.member.MemberMapper;
import com.example.hope_dog.mapper.mypage.MypageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MypageService {

    private final MemberMapper memberMapper;
    private final MypageMapper mypageMapper;

    @Autowired
    public MypageService(MemberMapper memberMapper, MypageMapper mypageMapper) {
        this.memberMapper = memberMapper;
        this.mypageMapper = mypageMapper;
    }

    public MemberSessionDTO getMemberInfo(String memberId) {
//        return memberMapper.findMemberById(memberId);
//        return mypageMapper.findMemberByName(memberId);
        return mypageMapper.findMemberById(memberId);

//        return memberMapper.selectMemberNo(getMemberInfo());
    }
}

