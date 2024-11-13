package com.example.hope_dog.service.mypage;

import com.example.hope_dog.dto.centermypage.CenterUpdateProfileDTO;
import com.example.hope_dog.dto.member.MemberDTO;
import com.example.hope_dog.dto.mypage.*;
import com.example.hope_dog.mapper.mypage.MypageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
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

    // 입양 신청서 조회
    public MpAdoptRequestDTO adoptRequestInfo(Long adoptRequestNo) {
        return mypageMapper.adoptRequestInfo(adoptRequestNo);
    }

    // 입양신청서 업데이트
    public void updateAdoptRequest(MpAdoptRequestDTO mpAdoptRequestDTO) {
        mypageMapper.updateAdoptRequest(mpAdoptRequestDTO);
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
    public void updateProtectRequest(MpProtectRequestDTO mpProtectRequestDTO) {
        mypageMapper.updateProtectRequest(mpProtectRequestDTO);
    }

    //volun
    public List<MypageVolunDTO> getMypageVolunProfile(Long memberNo) {
        return mypageMapper.mypageVolunList(memberNo);
    }

    // 봉사 신청서 조회
    public MpVolunRequestDTO volunRequestInfo(Long volunRequestNo) {
        return mypageMapper.volunRequestInfo(volunRequestNo);
    }

    // 봉사 신청서 업데이트
    public void updateVolunRequest(MpVolunRequestDTO mpVolunRequestDTO) {
        mypageMapper.updateVolunRequest(mpVolunRequestDTO);
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


    // 회원 탈퇴
    public boolean withdrawal(Long memberNo) {
        try {
            // 회원 정보를 삭제합니다.
            int rowsAffected = mypageMapper.deleteMember(memberNo);
            if (rowsAffected > 0) {
                log.info("회원 탈퇴 성공. 회원 번호: {}", memberNo);
                return true; // 삭제 성공
            } else {
                log.warn("탈퇴할 회원이 존재하지 않음. 회원 번호: {}", memberNo);
                return false; // 삭제할 회원이 없음
            }
        } catch (DataAccessException e) {
            log.error("데이터베이스 오류 발생. 회원 번호: {}. 오류: {}", memberNo, e);
            return false; // 데이터베이스 오류 발생
        } catch (Exception e) {
            log.error("회원 탈퇴 중 예기치 않은 오류 발생. 회원 번호: {}. 오류: {}", memberNo, e);
            return false; // 일반 오류 발생
        }
    }



}


