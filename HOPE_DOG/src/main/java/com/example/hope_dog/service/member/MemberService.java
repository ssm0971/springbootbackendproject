package com.example.hope_dog.service.member;


import com.example.hope_dog.dto.member.MemberDTO;
import com.example.hope_dog.dto.member.MemberSessionDTO;
import com.example.hope_dog.mapper.member.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;

    /**
     * 회원 가입
     */
    public void join(MemberDTO memberDTO) {
        memberMapper.insertMember(memberDTO);
    }

    /**
     * 회원 번호 조회
     */
    public Long findMemberNo(String memberId, String memberPw) {
        return Optional.ofNullable(memberMapper.selectMemberNo(memberId, memberPw))
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원정보"));
    }

    /**
     * 로그인
     */
    public MemberSessionDTO login(String memberId, String memberPw) {
        return Optional.ofNullable(memberMapper.selectLoginInfo(memberId, memberPw))
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원정보"));
    }

//  닉네임 중복체크
    public boolean checkNickname(String nickname) {
        if (nickname == null || nickname.trim().isEmpty()) {
            throw new IllegalArgumentException("닉네임은 필수 입력값입니다.");
        }
        // 추가 유효성 검사 가능
        return memberMapper.checkNickname(nickname) == 0;
    }
    /**
     * 이메일 중복 체크 (모든 회원 테이블 검사)
     */
    public boolean checkEmail(String email) {
        int count = memberMapper.checkMemberEmail(email);
        return count == 0;  // 중복된 이메일이 없으면 true 반환
    }
}
