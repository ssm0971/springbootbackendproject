package com.example.hope_dog.service.mypage;

import com.example.hope_dog.dto.mypage.*;
import com.example.hope_dog.mapper.mypage.MypageMapper;
import lombok.RequiredArgsConstructor;
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

    // 임시보호 신청서 조회
    public MpProtectRequestDTO protectRequestInfo(Long protectRequestNo){
        return mypageMapper.protectRequestInfo(protectRequestNo);
    }

    // 임시보호 업데이트 메서드
//    public void updateProtectRequest(UpdateProtectRequestDTO updateProtectRequestDTO) {
//        mypageMapper.updateProtectRequest(updateProtectRequestDTO);
//    }

    public void updateProtectRequest(MpProtectRequestDTO mpProtectRequestDTO) {
        mypageMapper.updateProtectRequest(mpProtectRequestDTO);
    }

    //volun
    public List<MypageVolunDTO> getMypageVolunProfile(Long memberNo) {
        return mypageMapper.mypageVolunList(memberNo);
    }

    //posts
    public List<MypagePostsDTO> getMypagePostsProfile(Long memberNo) {
        return mypageMapper.mypagePostsList(memberNo);
    }

    // 프로필 조회
    public MypageViewProfileDTO getMypageViewProfile(Long memberNo) {
        return mypageMapper.viewProfile(memberNo);
    }

    // 닉네임 중복체크
    public boolean checkedNickname(String newNickname, String currentNickname) {
        if (newNickname.equals(currentNickname)) {
            return true;
        }

        int count = mypageMapper.checkedNickname(newNickname, currentNickname);
        return count == 0;
    }

    // 이메일 중복체크
    public boolean updateCheckedEmail(String newEmail, String currentEmail) {
        // 입력한 이메일이 현재 이메일과 같다면 중복 검사 없이 사용 가능으로 처리
        if (newEmail.equals(currentEmail)) {
            return true;  // 중복 검사 없이 사용 가능
        }

        // 입력한 이메일이 현재 이메일과 다를 때만 중복 체크 수행
        int count = mypageMapper.updateCheckedEmail(newEmail, currentEmail);
        return count == 0;  // 중복된 이메일이 없으면 true 반환, 중복되면 false 반환
    }


    // 프로필 업데이트 메서드
    public int updateProfile(MypageUpdateProfileDTO mypageUpdateProfileDTO) {
        return mypageMapper.updateProfile(mypageUpdateProfileDTO);
    }

    // 페이지네이션
//    public List<MypagePostsDTO> findAll() {
//        return mypageMapper.selectAll();
//    }
//
//    public int findTotal(){
//        return mypageMapper.selectTotal();
//    }
//
//    public List<MypagePostsDTO> findAllPage(Criteria criteria) {
//        return mypageMapper.selectAllPage(criteria);
//    }





}


