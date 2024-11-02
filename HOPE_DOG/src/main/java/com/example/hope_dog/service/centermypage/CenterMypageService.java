package com.example.hope_dog.service.centermypage;

import com.example.hope_dog.dto.centermypage.CenterProfileDTO;
import com.example.hope_dog.dto.centermypage.CenterUpdateProfileDTO;
import com.example.hope_dog.dto.centermypage.CenterViewProfileDTO;
import com.example.hope_dog.mapper.centermypage.CenterMypageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CenterMypageService {
    private final CenterMypageMapper centerMypageMapper;

    // 프로필 조회 페이지
    public CenterProfileDTO centerProfile(Long centerMemberNo) {
        return centerMypageMapper.centerProfile(centerMemberNo);
    }

    // 프로필 조회
    public CenterViewProfileDTO getCenterViewProfile(Long centerMemberNo) {
        return centerMypageMapper.viewProfile(centerMemberNo);
    }


    // 프로필 업데이트 메서드
    public int updateCenterProfile(CenterUpdateProfileDTO centerUpdateProfileDTO) {
        return centerMypageMapper.updateCenterProfile(centerUpdateProfileDTO);
    }

//
//    // 비밀번호 확인 후 프로필 수정
//    public boolean updateCenterProfile(CenterUpdateProfileDTO centerUpdateProfileDTO) {
//        // 현재 비밀번호가 일치하는지 확인
//        boolean isPasswordMatch = centerMypageMapper.checkPasswordMatch(
//                centerUpdateProfileDTO.getCenterMemberNo(),
//                centerUpdateProfileDTO.getCenterMemberPw()
//        );
//
//        // 비밀번호가 일치하지 않으면 false 반환
//        if (!isPasswordMatch) {
//            return false;
//        }
//
//        // 새 비밀번호가 존재하고 새 비밀번호 확인란과 일치하는지 확인
//        if (centerUpdateProfileDTO.getCenterMemberNewPw() != null
//                && !centerUpdateProfileDTO.getCenterMemberNewPw().isEmpty()
//                && centerUpdateProfileDTO.getCenterMemberNewPw().equals(centerUpdateProfileDTO.getConfirmNewPw())) {
//            // 비밀번호 업데이트를 위해 DTO에 새 비밀번호 설정
//            centerUpdateProfileDTO.setCenterMemberPw(centerUpdateProfileDTO.getCenterMemberNewPw());
//        }
//
//        // 업데이트 쿼리 실행
//        centerMypageMapper.updateCenterProfile(centerUpdateProfileDTO);
//        return true;
//    }

//    // 프로필 수정 페이지 정보 조회
//    public CenterViewProfileDTO getCenterViewProfile(Long centerMemberNo) {
//        return centerMypageMapper.viewProfile(centerMemberNo);
//    }
//
//    // 비밀번호 확인 후 프로필 수정
//    public boolean updateCenterProfile(CenterUpdateProfileDTO centerUpdateProfileDTO) {
//        // 현재 비밀번호가 일치하는지 확인
//        boolean isPasswordMatch = centerMypageMapper.checkPasswordMatch(centerUpdateProfileDTO.getCenterMemberNo(), centerUpdateProfileDTO.getCenterMemberPassword());
//
//        // 비밀번호가 일치하지 않으면 false 반환
//        if (!isPasswordMatch) {
//            return false;
//        }
//
//        // 새 비밀번호가 있으면 기존 비밀번호를 새 비밀번호로 설정
//        if (centerUpdateProfileDTO.getCenterMemberNewPassword() != null && !centerUpdateProfileDTO.getCenterMemberNewPassword().isEmpty()) {
//            centerUpdateProfileDTO.setCenterMemberPassword(centerUpdateProfileDTO.getCenterMemberNewPassword()); // 새 비밀번호로 업데이트
//        }
//
//        // 업데이트 쿼리 실행
//        centerMypageMapper.updateCenterProfile(centerUpdateProfileDTO);
//        return true;
//    }
}
