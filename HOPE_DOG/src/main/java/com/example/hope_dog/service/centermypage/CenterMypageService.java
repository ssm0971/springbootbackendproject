package com.example.hope_dog.service.centermypage;

import com.example.hope_dog.dto.centermypage.CenterProfileDTO;
import com.example.hope_dog.dto.centermypage.CenterUpdateProfileDTO;
import com.example.hope_dog.dto.centermypage.CenterViewProfileDTO;
import com.example.hope_dog.mapper.centermypage.CenterMypageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
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

    // 이메일 중복체크
    public boolean updateCheckCenterEmail(String newEmail, String currentEmail) {
        // 입력한 이메일이 현재 이메일과 같다면 중복 검사 없이 사용 가능으로 처리
        if (newEmail.equals(currentEmail)) {
            return true;  // 중복 검사 없이 사용 가능
        }

        // 입력한 이메일이 현재 이메일과 다를 때만 중복 체크 수행
        int count = centerMypageMapper.updateCheckCenterEmail(newEmail, currentEmail);
        return count == 0;  // 중복된 이메일이 없으면 true 반환, 중복되면 false 반환
    }


    // 프로필 업데이트
    public int updateCenterProfile(CenterUpdateProfileDTO centerUpdateProfileDTO) {
        return centerMypageMapper.updateCenterProfile(centerUpdateProfileDTO);
    }

    // 회원 탈퇴
    public boolean withdraw(Long centerMemberNo) {
        try {
            // 회원 정보를 삭제합니다.
            int rowsAffected = centerMypageMapper.deleteCenterMember(centerMemberNo);
            if (rowsAffected > 0) {
                log.info("회원 탈퇴 성공. 회원 번호: {}", centerMemberNo);
                return true; // 삭제 성공
            } else {
                log.warn("탈퇴할 회원이 존재하지 않음. 회원 번호: {}", centerMemberNo);
                return false; // 삭제할 회원이 없음
            }
        } catch (DataAccessException e) {
            log.error("데이터베이스 오류 발생. 회원 번호: {}. 오류: {}", centerMemberNo, e);
            return false; // 데이터베이스 오류 발생
        } catch (Exception e) {
            log.error("회원 탈퇴 중 예기치 않은 오류 발생. 회원 번호: {}. 오류: {}", centerMemberNo, e);
            return false; // 일반 오류 발생
        }
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
