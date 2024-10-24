package com.example.hope_dog.mapper.member;

import com.example.hope_dog.dto.member.MemberDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberMapperTest {
    @Autowired
    MemberMapper memberMapper;

    MemberDTO memberDTO;

    @BeforeEach
    void setUp() {
        memberDTO = new MemberDTO();
        memberDTO.setMemberId("test");
        memberDTO.setMemberEmail("test@naver.com");
        memberDTO.setMemberPw("1234");
        memberDTO.setMemberName("테스트");
        memberDTO.setMemberNickname("닉닉닉");
        memberDTO.setMemberPhoneNumber("010-1234-5678");
        memberDTO.setMemberGender("M");
        memberDTO.setMemberZipcode("12345");
        memberDTO.setMemberAddress("서울시 테스트구 테스트동");
        memberDTO.setMemberDetailAddress("101동 101호");
        memberDTO.setMemberTwoFactorEnabled("N");
        memberDTO.setMemberStatus("1");
        memberDTO.setMemberLoginStatus("HOPEDOG");
    }

    @Test
    void insertMemberTest() {
        // given
        memberMapper.insertMember(memberDTO);

        // when
        Long memberNo = memberMapper.selectMemberNo(memberDTO.getMemberId(), memberDTO.getMemberPw());

        // then
        assertThat(memberNo).isNotNull();
    }

//    닉네임이 중복되지 않았을 때
    @Test
    void checkNickname_NotExists() {
        // given
        String newNickname = "newNickname";

        // when
        int result = memberMapper.checkNickname(newNickname);

        // then
        assertThat(result).isZero();
    }

    //    닉네임이 중복되었을 때
    @Test
    void checkNickname_Exists() {
        // given
        memberMapper.insertMember(memberDTO);

        // when
        int result = memberMapper.checkNickname(memberDTO.getMemberNickname());

        // then
        assertThat(result).isOne();
    }

    //    이메일이 중복되지 않았을 때
    @Test
    void checkEmail_NotExists() {
        // given
        String newEmail = "new@example.com";

        // when
        int result = memberMapper.checkEmail(newEmail);

        // then
        assertThat(result).isZero();
    }

    //    이메일이 중복되었을 때
    @Test
    void checkEmail_Exists() {
        // given
        memberMapper.insertMember(memberDTO);

        // when
        int result = memberMapper.checkEmail(memberDTO.getMemberEmail());

        // then
        assertThat(result).isOne();
    }


}