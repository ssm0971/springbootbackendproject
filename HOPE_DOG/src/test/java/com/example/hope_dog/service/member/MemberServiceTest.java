<<<<<<< HEAD
package com.example.hope_dog.service.member;

import com.example.hope_dog.dto.member.MemberDTO;
import com.example.hope_dog.dto.member.MemberSessionDTO;
import com.example.hope_dog.mapper.member.MemberMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

// 회원 서비스 테스트 클래스
// 회원 관련 비즈니스 로직을 테스트
@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

<<<<<<< HEAD
//    @Mock
//    MemberMapper memberMapper;
//
//    @InjectMocks
//    MemberService memberService;
//
//    private MemberDTO memberDTO;
//    private final String TEST_ID = "testId";
//    private final String TEST_PW = "testPw123!";
//
=======
//package com.example.hope_dog.service.member;
//
//import com.example.hope_dog.dto.member.MemberDTO;
//import com.example.hope_dog.dto.member.MemberSessionDTO;
//import com.example.hope_dog.mapper.member.MemberMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.never;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//// 회원 서비스 테스트 클래스
//// 회원 관련 비즈니스 로직을 테스트
//@ExtendWith(MockitoExtension.class)
//class MemberServiceTest {
//
//    @Mock
//    MemberMapper memberMapper;
//
//    // 비밀번호 암호화를 위한 Mock
//    @Mock
//    private PasswordEncoder passwordEncoder;
//
//    // 이메일 서비스를 위한 Mock
//    @Mock
//    private EmailService emailService;
//
//    // 테스트 대상 서비스
//    @InjectMocks
//    MemberService memberService;
//
//    // 테스트용 회원 DTO
//    private MemberDTO memberDTO;
//    // 테스트용 ID와 비밀번호
//    private final String TEST_ID = "testId";
//    private final String TEST_PW = "testPw123!";
//
//    // 각 테스트 실행 전 테스트 데이터 초기화
>>>>>>> main
//    @BeforeEach
//    void setUp() {
//        memberDTO = new MemberDTO();
//        memberDTO.setMemberId(TEST_ID);
//        memberDTO.setMemberPw(TEST_PW);
//        memberDTO.setMemberEmail("test@example.com");
//        memberDTO.setMemberName("테스트");
//        memberDTO.setMemberNickname("테스트닉네임");
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
<<<<<<< HEAD
//    @Test
//    void joinTest() {
//        // given
//        doNothing().when(memberMapper).insertMember(any());
//
//        // when
//        memberService.join(memberDTO);
//
//        // then
//        verify(memberMapper, times(1)).insertMember(any());
//    }
//
//    @Test
//    void findMemberNoSuccessTest() {
//        // given
//        Long expectedMemberNo = 1L;
//        when(memberMapper.selectMemberNo(TEST_ID, TEST_PW)).thenReturn(expectedMemberNo);
//
//        // when
//        Long memberNo = memberService.findMemberNo(TEST_ID, TEST_PW);
//
//        // then
=======
//    // 회원가입 테스트
//    // 회원 정보가 정상적으로 저장되는지 확인
//    @Test
//    void joinTest() {
//        // given - 회원가입 동작 정의
//        doNothing().when(memberMapper).insertMember(any());
//
//        // when - 회원가입 실행
//        memberService.join(memberDTO);
//
//        // then - insertMember 메서드가 한 번 호출되었는지 검증
//        verify(memberMapper, times(1)).insertMember(any());
//    }
//
//    // 회원번호 조회 성공 테스트
//    // 올바른 아이디와 비밀번호로 회원번호를 조회할 수 있는지 확인
//    @Test
//    void findMemberNoSuccessTest() {
//        // given - 예상되는 회원번호 설정
//        Long expectedMemberNo = 1L;
//        when(memberMapper.selectMemberNo(TEST_ID, TEST_PW)).thenReturn(expectedMemberNo);
//
//        // when - 회원번호 조회
//        Long memberNo = memberService.findMemberNo(TEST_ID, TEST_PW);
//
//        // then - 예상 회원번호와 일치하는지 검증
>>>>>>> main
//        assertThat(memberNo).isEqualTo(expectedMemberNo);
//        verify(memberMapper, times(1)).selectMemberNo(TEST_ID, TEST_PW);
//    }
//
<<<<<<< HEAD
//    @Test
//    void findMemberNoFailTest() {
//        // given
//        when(memberMapper.selectMemberNo(TEST_ID, TEST_PW)).thenReturn(null);
//
//        // when & then
=======
//    // 회원번호 조회 실패 테스트
//    // 존재하지 않는 회원 정보로 조회 시 예외가 발생하는지 확인
//    @Test
//    void findMemberNoFailTest() {
//        // given - null 반환하도록 설정
//        when(memberMapper.selectMemberNo(TEST_ID, TEST_PW)).thenReturn(null);
//
//        // when & then - 예외 발생 확인
>>>>>>> main
//        assertThrows(IllegalStateException.class,
//                () -> memberService.findMemberNo(TEST_ID, TEST_PW));
//        verify(memberMapper, times(1)).selectMemberNo(TEST_ID, TEST_PW);
//    }
//
<<<<<<< HEAD
//    @Test
//    void loginSuccessTest() {
//        // given
=======
//    // 로그인 성공 테스트
//    // 올바른 아이디와 비밀번호로 로그인이 성공하는지 확인
//    @Test
//    void loginSuccessTest() {
//        // given - 예상되는 세션 정보 설정
>>>>>>> main
//        MemberSessionDTO expectedSession = new MemberSessionDTO();
//        expectedSession.setMemberId(TEST_ID);
//        when(memberMapper.selectLoginInfo(TEST_ID, TEST_PW)).thenReturn(expectedSession);
//
<<<<<<< HEAD
//        // when
//        MemberSessionDTO loginInfo = memberService.login(TEST_ID, TEST_PW);
//
//        // then
=======
//        // when - 로그인 시도
//        MemberSessionDTO loginInfo = memberService.login(TEST_ID, TEST_PW);
//
//        // then - 세션 정보 검증
>>>>>>> main
//        assertThat(loginInfo).isNotNull();
//        assertThat(loginInfo.getMemberId()).isEqualTo(TEST_ID);
//        verify(memberMapper, times(1)).selectLoginInfo(TEST_ID, TEST_PW);
//    }
//
<<<<<<< HEAD
//    @Test
//    void loginFailTest() {
//        // given
//        when(memberMapper.selectLoginInfo(TEST_ID, TEST_PW)).thenReturn(null);
//
//        // when & then
=======
//    // 로그인 실패 테스트
//    // 잘못된 로그인 정보로 로그인 시 예외가 발생하는지 확인
//    @Test
//    void loginFailTest() {
//        // given - null 반환하도록 설정
//        when(memberMapper.selectLoginInfo(TEST_ID, TEST_PW)).thenReturn(null);
//
//        // when & then - 예외 발생 확인
>>>>>>> main
//        assertThrows(IllegalStateException.class,
//                () -> memberService.login(TEST_ID, TEST_PW));
//        verify(memberMapper, times(1)).selectLoginInfo(TEST_ID, TEST_PW);
//    }
//
<<<<<<< HEAD
//
//    @Test
//    void checkNickname_Available() {
//        // given
//        String nickname = "testNick";
//        when(memberMapper.checkNickname(nickname)).thenReturn(0);
//
//        // when
//        boolean result = memberService.checkNickname(nickname);
//
//        // then
=======
//    // 닉네임 사용 가능 여부 테스트
//    // 사용 가능한 닉네임인 경우 true 반환 확인
//    @Test
//    void checkNickname_Available() {
//        // given - 중복되지 않은 닉네임 설정
//        String nickname = "testNick";
//        when(memberMapper.checkNickname(nickname)).thenReturn(0);
//
//        // when - 닉네임 중복 체크
//        boolean result = memberService.checkNickname(nickname);
//
//        // then - 사용 가능 여부 검증
>>>>>>> main
//        assertThat(result).isTrue();
//        verify(memberMapper).checkNickname(nickname);
//    }
//
<<<<<<< HEAD
//    @Test
//    void checkNickname_NotAvailable() {
//        // given
//        String nickname = "testNick";
//        when(memberMapper.checkNickname(nickname)).thenReturn(1);
//
//        // when
//        boolean result = memberService.checkNickname(nickname);
//
//        // then
=======
//    // 닉네임 중복 테스트
//    // 이미 사용 중인 닉네임인 경우 false 반환 확인
//    @Test
//    void checkNickname_NotAvailable() {
//        // given - 중복된 닉네임 설정
//        String nickname = "testNick";
//        when(memberMapper.checkNickname(nickname)).thenReturn(1);
//
//        // when - 닉네임 중복 체크
//        boolean result = memberService.checkNickname(nickname);
//
//        // then - 사용 불가능 여부 검증
>>>>>>> main
//        assertThat(result).isFalse();
//        verify(memberMapper).checkNickname(nickname);
//    }
//
<<<<<<< HEAD
//    @Test
//    void checkNickname_EmptyString() {
//        // given
//        String nickname = "";
//
//        // when & then
=======
//    // 빈 닉네임 입력 테스트
//    // 빈 문자열 입력 시 예외 발생 확인
//    @Test
//    void checkNickname_EmptyString() {
//        // given - 빈 닉네임 설정
//        String nickname = "";
//
//        // when & then - 예외 발생 확인
>>>>>>> main
//        assertThatThrownBy(() -> memberService.checkNickname(nickname))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessage("닉네임은 필수 입력값입니다.");
//    }
//
<<<<<<< HEAD
//    @Test
//    void checkEmail_Available() {
//        // given
//        String email = "test@example.com";
//        when(memberMapper.checkEmail(email)).thenReturn(0);
//
//        // when
//        boolean result = memberService.checkEmail(email);
//
//        // then
//        assertThat(result).isTrue();
//        verify(memberMapper).checkEmail(email);
//    }
//
//    @Test
//    void checkEmail_NotAvailable() {
//        // given
//        String email = "test@example.com";
//        when(memberMapper.checkEmail(email)).thenReturn(1);
//
//        // when
//        boolean result = memberService.checkEmail(email);
//
//        // then
//        assertThat(result).isFalse();
//        verify(memberMapper).checkEmail(email);
//    }
//
//    @Test
//    void checkEmail_InvalidFormat() {
//        // given
//        String email = "invalid-email";
//
//        // when & then
=======
//    // 이메일 중복 체크 성공 테스트
//    // 사용 가능한 이메일인 경우 true 반환 확인
//    @Test
//    void checkEmail_Available() {
//        // given - 중복되지 않은 이메일 설정
//        String email = "test@example.com";
//        when(memberMapper.checkMemberEmail(email)).thenReturn(0);
//
//        // when - 이메일 중복 체크
//        boolean result = memberService.checkEmail(email);
//
//        // then - 사용 가능 여부 검증
//        assertThat(result).isTrue();
//        verify(memberMapper).checkMemberEmail(email);
//    }
//
//    // 이메일 중복 체크 실패 테스트
//    // 이미 사용 중인 이메일인 경우 false 반환 확인
//    @Test
//    void checkEmail_NotAvailable() {
//        // given - 중복된 이메일 설정
//        String email = "test@example.com";
//        when(memberMapper.checkMemberEmail(email)).thenReturn(1);
//
//        // when - 이메일 중복 체크
//        boolean result = memberService.checkEmail(email);
//
//        // then - 사용 불가능 여부 검증
//        assertThat(result).isFalse();
//        verify(memberMapper).checkMemberEmail(email);
//    }
//
//    // 잘못된 이메일 형식 테스트
//    // 잘못된 형식의 이메일 입력 시 예외 발생 확인
//    @Test
//    void checkEmail_InvalidFormat() {
//        // given - 잘못된 형식의 이메일 설정
//        String email = "invalid-email";
//
//        // when & then - 예외 발생 확인
>>>>>>> main
//        assertThatThrownBy(() -> memberService.checkEmail(email))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessage("유효하지 않은 이메일 형식입니다.");
//    }
<<<<<<< HEAD
=======
>>>>>>> main
    @Mock
    MemberMapper memberMapper;

    // 비밀번호 암호화를 위한 Mock
    @Mock
    private PasswordEncoder passwordEncoder;

    // 이메일 서비스를 위한 Mock
    @Mock
    private EmailService emailService;

    // 테스트 대상 서비스
    @InjectMocks
    MemberService memberService;

    // 테스트용 회원 DTO
    private MemberDTO memberDTO;
    // 테스트용 ID와 비밀번호
    private final String TEST_ID = "testId";
    private final String TEST_PW = "testPw123!";

    // 각 테스트 실행 전 테스트 데이터 초기화
    @BeforeEach
    void setUp() {
        memberDTO = new MemberDTO();
        memberDTO.setMemberId(TEST_ID);
        memberDTO.setMemberPw(TEST_PW);
        memberDTO.setMemberEmail("test@example.com");
        memberDTO.setMemberName("테스트");
        memberDTO.setMemberNickname("테스트닉네임");
        memberDTO.setMemberPhoneNumber("010-1234-5678");
        memberDTO.setMemberGender("M");
        memberDTO.setMemberZipcode("12345");
        memberDTO.setMemberAddress("서울시 테스트구 테스트동");
        memberDTO.setMemberDetailAddress("101동 101호");
        memberDTO.setMemberTwoFactorEnabled("N");
        memberDTO.setMemberStatus("1");
        memberDTO.setMemberLoginStatus("HOPEDOG");
    }

    // 회원가입 테스트
    // 회원 정보가 정상적으로 저장되는지 확인
    @Test
    void joinTest() {
        // given - 회원가입 동작 정의
        doNothing().when(memberMapper).insertMember(any());

        // when - 회원가입 실행
        memberService.join(memberDTO);

        // then - insertMember 메서드가 한 번 호출되었는지 검증
        verify(memberMapper, times(1)).insertMember(any());
    }

    // 회원번호 조회 성공 테스트
    // 올바른 아이디와 비밀번호로 회원번호를 조회할 수 있는지 확인
    @Test
    void findMemberNoSuccessTest() {
        // given - 예상되는 회원번호 설정
        Long expectedMemberNo = 1L;
        when(memberMapper.selectMemberNo(TEST_ID, TEST_PW)).thenReturn(expectedMemberNo);

        // when - 회원번호 조회
        Long memberNo = memberService.findMemberNo(TEST_ID, TEST_PW);

        // then - 예상 회원번호와 일치하는지 검증
        assertThat(memberNo).isEqualTo(expectedMemberNo);
        verify(memberMapper, times(1)).selectMemberNo(TEST_ID, TEST_PW);
    }

    // 회원번호 조회 실패 테스트
    // 존재하지 않는 회원 정보로 조회 시 예외가 발생하는지 확인
    @Test
    void findMemberNoFailTest() {
        // given - null 반환하도록 설정
        when(memberMapper.selectMemberNo(TEST_ID, TEST_PW)).thenReturn(null);

        // when & then - 예외 발생 확인
        assertThrows(IllegalStateException.class,
                () -> memberService.findMemberNo(TEST_ID, TEST_PW));
        verify(memberMapper, times(1)).selectMemberNo(TEST_ID, TEST_PW);
    }

    // 로그인 성공 테스트
    // 올바른 아이디와 비밀번호로 로그인이 성공하는지 확인
    @Test
    void loginSuccessTest() {
        // given - 예상되는 세션 정보 설정
        MemberSessionDTO expectedSession = new MemberSessionDTO();
        expectedSession.setMemberId(TEST_ID);
        when(memberMapper.selectLoginInfo(TEST_ID, TEST_PW)).thenReturn(expectedSession);

        // when - 로그인 시도
        MemberSessionDTO loginInfo = memberService.login(TEST_ID, TEST_PW);

        // then - 세션 정보 검증
        assertThat(loginInfo).isNotNull();
        assertThat(loginInfo.getMemberId()).isEqualTo(TEST_ID);
        verify(memberMapper, times(1)).selectLoginInfo(TEST_ID, TEST_PW);
    }

    // 로그인 실패 테스트
    // 잘못된 로그인 정보로 로그인 시 예외가 발생하는지 확인
    @Test
    void loginFailTest() {
        // given - null 반환하도록 설정
        when(memberMapper.selectLoginInfo(TEST_ID, TEST_PW)).thenReturn(null);

        // when & then - 예외 발생 확인
        assertThrows(IllegalStateException.class,
                () -> memberService.login(TEST_ID, TEST_PW));
        verify(memberMapper, times(1)).selectLoginInfo(TEST_ID, TEST_PW);
    }

    // 닉네임 사용 가능 여부 테스트
    // 사용 가능한 닉네임인 경우 true 반환 확인
    @Test
    void checkNickname_Available() {
        // given - 중복되지 않은 닉네임 설정
        String nickname = "testNick";
        when(memberMapper.checkNickname(nickname)).thenReturn(0);

        // when - 닉네임 중복 체크
        boolean result = memberService.checkNickname(nickname);

        // then - 사용 가능 여부 검증
        assertThat(result).isTrue();
        verify(memberMapper).checkNickname(nickname);
    }

    // 닉네임 중복 테스트
    // 이미 사용 중인 닉네임인 경우 false 반환 확인
    @Test
    void checkNickname_NotAvailable() {
        // given - 중복된 닉네임 설정
        String nickname = "testNick";
        when(memberMapper.checkNickname(nickname)).thenReturn(1);

        // when - 닉네임 중복 체크
        boolean result = memberService.checkNickname(nickname);

        // then - 사용 불가능 여부 검증
        assertThat(result).isFalse();
        verify(memberMapper).checkNickname(nickname);
    }

    // 빈 닉네임 입력 테스트
    // 빈 문자열 입력 시 예외 발생 확인
    @Test
    void checkNickname_EmptyString() {
        // given - 빈 닉네임 설정
        String nickname = "";

        // when & then - 예외 발생 확인
        assertThatThrownBy(() -> memberService.checkNickname(nickname))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("닉네임은 필수 입력값입니다.");
    }

    // 이메일 중복 체크 성공 테스트
    // 사용 가능한 이메일인 경우 true 반환 확인
    @Test
    void checkEmail_Available() {
        // given - 중복되지 않은 이메일 설정
        String email = "test@example.com";
        when(memberMapper.checkMemberEmail(email)).thenReturn(0);

        // when - 이메일 중복 체크
        boolean result = memberService.checkEmail(email);

        // then - 사용 가능 여부 검증
        assertThat(result).isTrue();
        verify(memberMapper).checkMemberEmail(email);
    }

    // 이메일 중복 체크 실패 테스트
    // 이미 사용 중인 이메일인 경우 false 반환 확인
    @Test
    void checkEmail_NotAvailable() {
        // given - 중복된 이메일 설정
        String email = "test@example.com";
        when(memberMapper.checkMemberEmail(email)).thenReturn(1);

        // when - 이메일 중복 체크
        boolean result = memberService.checkEmail(email);

        // then - 사용 불가능 여부 검증
        assertThat(result).isFalse();
        verify(memberMapper).checkMemberEmail(email);
    }

    // 잘못된 이메일 형식 테스트
    // 잘못된 형식의 이메일 입력 시 예외 발생 확인
    @Test
    void checkEmail_InvalidFormat() {
        // given - 잘못된 형식의 이메일 설정
        String email = "invalid-email";

        // when & then - 예외 발생 확인
        assertThatThrownBy(() -> memberService.checkEmail(email))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 이메일 형식입니다.");
    }

    // 아이디 찾기 성공 테스트
    // 이름과 전화번호로 아이디를 찾을 수 있는지 확인
    @Test
    void findMemberId_Success() {
        // given - 테스트 데이터 설정
        String memberName = "테스트";
        String phoneNumber = "010-1234-5678";
        when(memberMapper.findMemberId(memberName, phoneNumber)).thenReturn(TEST_ID);

        // when - 아이디 찾기 실행
        String foundId = memberService.findMemberId(memberName, phoneNumber);

        // then - 찾은 아이디 검증
        assertThat(foundId).isEqualTo(TEST_ID);
        verify(memberMapper).findMemberId(memberName, phoneNumber);
    }

    // 아이디 찾기 실패 테스트
    // 존재하지 않는 회원 정보로 아이디 찾기 시 예외 발생 확인
    @Test
    void findMemberId_NotFound() {
        // given - 존재하지 않는 회원 정보 설정
        String memberName = "존재하지않는이름";
        String phoneNumber = "010-9999-9999";
        when(memberMapper.findMemberId(memberName, phoneNumber)).thenReturn(null);

        // when & then - 예외 발생 확인
        assertThatThrownBy(() -> memberService.findMemberId(memberName, phoneNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당하는 회원 정보를 찾을 수 없습니다.");
        verify(memberMapper).findMemberId(memberName, phoneNumber);
    }

    // 비밀번호 재설정 성공 테스트
    // 회원 정보 확인 후 임시 비밀번호 발급 및 이메일 발송 확인
    @Test
    void resetPassword_Success() {
        // given - 테스트 데이터 설정
        String memberName = "테스트";
        String memberId = TEST_ID;
        String email = "test@example.com";

        MemberDTO foundMember = new MemberDTO();
        foundMember.setMemberNo(1L);
        foundMember.setMemberName(memberName);
        foundMember.setMemberId(memberId);
        foundMember.setMemberEmail(email);

        // Mock 동작 정의
        when(memberMapper.findMemberByNameIdEmail(memberName, memberId, email))
                .thenReturn(foundMember);
        doNothing().when(memberMapper).updateMemberPassword(any(MemberDTO.class));
        doNothing().when(emailService).sendTempPassword(anyString(), anyString());

        // when - 비밀번호 재설정 실행
        memberService.resetPassword(memberName, memberId, email);

        // then - 각 메서드 호출 검증
        verify(memberMapper).findMemberByNameIdEmail(memberName, memberId, email);
        verify(memberMapper).updateMemberPassword(any(MemberDTO.class));
        verify(emailService).sendTempPassword(eq(email), anyString());
    }

    // 비밀번호 재설정 실패 테스트
    // 존재하지 않는 회원 정보로 비밀번호 재설정 시 예외 발생 확인
    @Test
    void resetPassword_MemberNotFound() {
        // given - 존재하지 않는 회원 정보 설정
        String memberName = "존재하지않는이름";
        String memberId = "wrongId";
        String email = "wrong@example.com";

        when(memberMapper.findMemberByNameIdEmail(memberName, memberId, email))
                .thenReturn(null);

        // when & then - 예외 발생 확인 및 메서드 호출 검증
        assertThatThrownBy(() -> memberService.resetPassword(memberName, memberId, email))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력하신 정보와 일치하는 회원이 없습니다.");

        verify(memberMapper).findMemberByNameIdEmail(memberName, memberId, email);
        verify(memberMapper, never()).updateMemberPassword(any());
        verify(emailService, never()).sendTempPassword(anyString(), anyString());
    }
<<<<<<< HEAD
=======

>>>>>>> main
}
=======
//
//    // 아이디 찾기 성공 테스트
//    // 이름과 전화번호로 아이디를 찾을 수 있는지 확인
//    @Test
//    void findMemberId_Success() {
//        // given - 테스트 데이터 설정
//        String memberName = "테스트";
//        String phoneNumber = "010-1234-5678";
//        when(memberMapper.findMemberId(memberName, phoneNumber)).thenReturn(TEST_ID);
//
//        // when - 아이디 찾기 실행
//        String foundId = memberService.findMemberId(memberName, phoneNumber);
//
//        // then - 찾은 아이디 검증
//        assertThat(foundId).isEqualTo(TEST_ID);
//        verify(memberMapper).findMemberId(memberName, phoneNumber);
//    }
//
//    // 아이디 찾기 실패 테스트
//    // 존재하지 않는 회원 정보로 아이디 찾기 시 예외 발생 확인
//    @Test
//    void findMemberId_NotFound() {
//        // given - 존재하지 않는 회원 정보 설정
//        String memberName = "존재하지않는이름";
//        String phoneNumber = "010-9999-9999";
//        when(memberMapper.findMemberId(memberName, phoneNumber)).thenReturn(null);
//
//        // when & then - 예외 발생 확인
//        assertThatThrownBy(() -> memberService.findMemberId(memberName, phoneNumber))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessage("해당하는 회원 정보를 찾을 수 없습니다.");
//        verify(memberMapper).findMemberId(memberName, phoneNumber);
//    }
//
//    // 비밀번호 재설정 성공 테스트
//    // 회원 정보 확인 후 임시 비밀번호 발급 및 이메일 발송 확인
//    @Test
//    void resetPassword_Success() {
//        // given - 테스트 데이터 설정
//        String memberName = "테스트";
//        String memberId = TEST_ID;
//        String email = "test@example.com";
//
//        MemberDTO foundMember = new MemberDTO();
//        foundMember.setMemberNo(1L);
//        foundMember.setMemberName(memberName);
//        foundMember.setMemberId(memberId);
//        foundMember.setMemberEmail(email);
//
//        // Mock 동작 정의
//        when(memberMapper.findMemberByNameIdEmail(memberName, memberId, email))
//                .thenReturn(foundMember);
//        doNothing().when(memberMapper).updateMemberPassword(any(MemberDTO.class));
//        doNothing().when(emailService).sendTempPassword(anyString(), anyString());
//
//        // when - 비밀번호 재설정 실행
//        memberService.resetPassword(memberName, memberId, email);
//
//        // then - 각 메서드 호출 검증
//        verify(memberMapper).findMemberByNameIdEmail(memberName, memberId, email);
//        verify(memberMapper).updateMemberPassword(any(MemberDTO.class));
//        verify(emailService).sendTempPassword(eq(email), anyString());
//    }
//
//    // 비밀번호 재설정 실패 테스트
//    // 존재하지 않는 회원 정보로 비밀번호 재설정 시 예외 발생 확인
//    @Test
//    void resetPassword_MemberNotFound() {
//        // given - 존재하지 않는 회원 정보 설정
//        String memberName = "존재하지않는이름";
//        String memberId = "wrongId";
//        String email = "wrong@example.com";
//
//        when(memberMapper.findMemberByNameIdEmail(memberName, memberId, email))
//                .thenReturn(null);
//
//        // when & then - 예외 발생 확인 및 메서드 호출 검증
//        assertThatThrownBy(() -> memberService.resetPassword(memberName, memberId, email))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessage("입력하신 정보와 일치하는 회원이 없습니다.");
//
//        verify(memberMapper).findMemberByNameIdEmail(memberName, memberId, email);
//        verify(memberMapper, never()).updateMemberPassword(any());
//        verify(emailService, never()).sendTempPassword(anyString(), anyString());
//    }
//
//}
>>>>>>> main
