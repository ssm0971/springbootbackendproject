package com.example.hope_dog.service.mypage;

import com.example.hope_dog.dto.adopt.adopt.MainDTO;
import com.example.hope_dog.dto.member.MemberSessionDTO;
import com.example.hope_dog.dto.mypage.*;
import com.example.hope_dog.mapper.member.MemberMapper;
import com.example.hope_dog.mapper.mypage.MypageMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class MypageService {
    private final MypageMapper mypageMapper;

    //profile
    public List<MypageDTO> getMypageProfile(Long memberNo) {
        return mypageMapper.mypageProfile(memberNo);
    }

    //adopt
    public List<MypageAdoptListDTO> getMypageAdoptProfile(Long memberNo) {
        return mypageMapper.mypageAdoptList(memberNo);
    }

    //protect
    public List<MypageProtectDTO> getMypageProtectProfile(Long memberNo) {
        return mypageMapper.mypageProtectList(memberNo);
    }

    //volun
    public List<MypageVolunDTO> getMypageVolunProfile(Long memberNo) {
        return mypageMapper.mypageVolunList(memberNo);
    }

    //posts
    public List<MypagePostsDTO> getMypagePostsProfile(Long memberNo) {
        return mypageMapper.mypagePostsList(memberNo);
    }

    //noteR
    public List<MypageNoteReceiveDTO> getMypageNoteReceiveProfile(Long memberNo) {
        return mypageMapper.mypageNoteReceiveList(memberNo);
    }

    //noteS
    public List<MypageNoteSendDTO> getMypageNoteSendProfile(Long memberNo) {
        return mypageMapper.mypageNoteSendList(memberNo);
    }

    // 프로필 조회
//    public MypageDTO getMypageProfile(String memberId) {
//        return mypageMapper.mypageProfile(@Param(Long memberNo));
//    }

//    @Autowired
//    public MypageService(MemberMapper memberMapper, MypageMapper mypageMapper) {
//        this.memberMapper = memberMapper;
//        this.mypageMapper = mypageMapper;
//    }

//    public MemberSessionDTO getMemberInfo(String memberId) {
//        return memberMapper.findMemberById(memberId);
//        return mypageMapper.findMemberByName(memberId);
//        return mypageMapper.findMemberById(memberId);

//        return memberMapper.selectMemberNo(getMemberInfo());
//    }


        // 회원의 입양 목록을 가져오는 메서드
//        public List<MypageAdoptListDTO> mypageAdoptList(Long memberNo) {
//            // 매퍼의 메서드를 호출하여 입양 목록을 조회
//            return mypageMapper.mypageAdoptList(memberNo);
//        }
}


