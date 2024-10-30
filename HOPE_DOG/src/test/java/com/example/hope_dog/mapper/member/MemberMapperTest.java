//package com.example.hope_dog.mapper.member;
//
//import com.example.hope_dog.dto.member.MemberDTO;
//import com.example.hope_dog.dto.member.MemberSessionDTO;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Transactional
//class MemberMapperTest {
//
//    @Autowired
//    MemberMapper memberMapper;
//
//    MemberDTO memberDTO;
//
//    @BeforeEach
//    void setUp() {
//        memberDTO = new MemberDTO();
//        memberDTO.setMemberId("test");
//        memberDTO.setMemberEmail("test@naver.com");
//        memberDTO.setMemberPw("1234");
//        memberDTO.setMemberName("테스트");
//        memberDTO.setMemberNickname("닉닉닉");
//        memberDTO.setMemberPhoneNumber("010-1234-5678");
//        memberDTO.setMemberGender("M");
//        memberDTO.setMemberZipcode("12345");
//        memberDTO.setMemberAddress("서울시 테스트구 테스트동");
//        memberDTO.setMemberDetailAddress("101동 101호");
//        memberDTO.setMemberTwoFactorEnabled("N");
//        memberDTO.setMemberStatus("1");
//        memberDTO.setMemberLoginStatus("HOPEDOG");
//    }
//
//    @Test
//    void insertMemberTest() {
//        // given
//        memberMapper.insertMember(memberDTO);
//
//        // when
//        Long memberNo = memberMapper.selectMemberNo(memberDTO.getMemberId(), memberDTO.getMemberPw());
//
//        // then
//        assertThat(memberNo).isNotNull();
//    }
//
//    //    닉네임이 중복되지 않았을 때
//    @Test
//    void checkNickname_NotExists() {
//        // given
//        String newNickname = "newNickname";
//
//        // when
//        int result = memberMapper.checkNickname(newNickname);
//
//        // then
//        assertThat(result).isZero();
//    }
//
//    //    닉네임이 중복되었을 때
//    @Test
//    void checkNickname_Exists() {
//        // given
//        memberMapper.insertMember(memberDTO);
//
//        // when
//        int result = memberMapper.checkNickname(memberDTO.getMemberNickname());
//
//        // then
//        assertThat(result).isOne();
//    }
//
//    //    이메일이 중복되지 않았을 때
//    @Test
//    void checkMemberEmail_NotExists() {
//        // given
//        String newEmail = "new@example.com";
//
//        // when
//        int result = memberMapper.checkMemberEmail(newEmail);
//
//        // then
//        assertThat(result).isZero();
//    }
//
//    //    이메일이 중복되었을 때
//    @Test
//    void checkMemberEmail_Exists() {
//        // given
//        memberMapper.insertMember(memberDTO);
//
//        // when
//        int result = memberMapper.checkMemberEmail(memberDTO.getMemberEmail());
//
//        // then
//        assertThat(result).isOne();
//    }
//
//    // 아이디 찾기 성공 테스트
//    @Test
//    void findMemberId_Success() {
//        // given
//        memberMapper.insertMember(memberDTO);
//
//        // when
//        String foundId = memberMapper.findMemberId(
//                memberDTO.getMemberName(),
//                memberDTO.getMemberPhoneNumber()
//        );
//
//        // then
//        assertThat(foundId).isEqualTo(memberDTO.getMemberId());
//    }
//
//    // 아이디 찾기 실패 테스트
//    @Test
//    void findMemberId_NotFound() {
//        // given
//        String nonExistentName = "존재하지않는이름";
//        String nonExistentPhone = "010-9999-9999";
//
//        // when
//        String foundId = memberMapper.findMemberId(nonExistentName, nonExistentPhone);
//
//        // then
//        assertThat(foundId).isNull();
//    }
//
//    // 비밀번호 찾기를 위한 회원 정보 조회 성공 테스트
//    @Test
//    void findMemberByNameIdEmail_Success() {
//        // given
//        memberMapper.insertMember(memberDTO);
//
//        // when
//        MemberDTO foundMember = memberMapper.findMemberByNameIdEmail(
//                memberDTO.getMemberName(),
//                memberDTO.getMemberId(),
//                memberDTO.getMemberEmail()
//        );
//
//        // then
//        assertThat(foundMember).isNotNull();
//        assertThat(foundMember.getMemberId()).isEqualTo(memberDTO.getMemberId());
//        assertThat(foundMember.getMemberEmail()).isEqualTo(memberDTO.getMemberEmail());
//    }
//
//    // 비밀번호 찾기를 위한 회원 정보 조회 실패 테스트
//    @Test
//    void findMemberByNameIdEmail_NotFound() {
//        // given
//        String wrongName = "wrongName";
//        String wrongId = "wrongId";
//        String wrongEmail = "wrong@email.com";
//
//        // when
//        MemberDTO foundMember = memberMapper.findMemberByNameIdEmail(
//                wrongName,
//                wrongId,
//                wrongEmail
//        );
//
//        // then
//        assertThat(foundMember).isNull();
//    }
//
//    // 비밀번호 업데이트 테스트
//    @Test
//    void updateMemberPassword_Success() {
//        // given
//        memberMapper.insertMember(memberDTO);
//        String newPassword = "newPassword123";
//        memberDTO.setMemberPw(newPassword);
//
//        // when
//        memberMapper.updateMemberPassword(memberDTO);
//
//        // then
//        Long memberNo = memberMapper.selectMemberNo(memberDTO.getMemberId(), newPassword);
//        assertThat(memberNo).isNotNull();
//    }
//
//    // 로그인 정보 조회 테스트 - ID로만 조회
//    @Test
//    void selectLoginInfo_Success() {
//        // given
//        memberMapper.insertMember(memberDTO);
//
//        // when
//        MemberSessionDTO loginInfo = memberMapper.selectLoginInfo(
//                memberDTO.getMemberId(),
//                memberDTO.getMemberPw()  // 비밀번호는 서비스 레이어에서 검증하므로 실제로 사용되지 않음
//        );
//
//        // then
//        assertThat(loginInfo).isNotNull();
//        assertThat(loginInfo.getMemberId()).isEqualTo(memberDTO.getMemberId());
//        assertThat(loginInfo.getMemberEmail()).isEqualTo(memberDTO.getMemberEmail());
//        assertThat(loginInfo.getMemberName()).isEqualTo(memberDTO.getMemberName());
//        // ... 다른 필드들도 검증
//    }
//
//    // 로그인 정보 조회 실패 테스트 - 존재하지 않는 ID
//    @Test
//    void selectLoginInfo_Failed() {
//        // given
//        memberMapper.insertMember(memberDTO);
//
//        // when
//        MemberSessionDTO loginInfo = memberMapper.selectLoginInfo(
//                "nonexistentId",  // 존재하지 않는 ID
//                "anyPassword"     // 비밀번호는 실제로 사용되지 않음
//        );
//
//        // then
//        assertThat(loginInfo).isNull();
//    }
//
//    // memberId와 memberPw로 회원번호 조회 테스트
//    @Test
//    void selectMemberNo_Success() {
//        // given
//        memberMapper.insertMember(memberDTO);
//
//        // when
//        Long memberNo = memberMapper.selectMemberNo(
//                memberDTO.getMemberId(),
//                memberDTO.getMemberPw()
//        );
//
//        // then
//        assertThat(memberNo).isNotNull();
//        assertThat(memberNo).isEqualTo(memberDTO.getMemberNo());
//    }
//
//    // 잘못된 비밀번호로 회원번호 조회 테스트
//    @Test
//    void selectMemberNo_WrongPassword() {
//        // given
//        memberMapper.insertMember(memberDTO);
//
//        // when
//        Long memberNo = memberMapper.selectMemberNo(
//                memberDTO.getMemberId(),
//                "wrongPassword"
//        );
//
//        // then
//        assertThat(memberNo).isNull();
//    }
//}