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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    MemberMapper memberMapper;

    @InjectMocks
    MemberService memberService;

    private MemberDTO memberDTO;
    private final String TEST_ID = "testId";
    private final String TEST_PW = "testPw123!";

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

    @Test
    void joinTest() {
        // given
        doNothing().when(memberMapper).insertMember(any());

        // when
        memberService.join(memberDTO);

        // then
        verify(memberMapper, times(1)).insertMember(any());
    }

    @Test
    void findMemberNoSuccessTest() {
        // given
        Long expectedMemberNo = 1L;
        when(memberMapper.selectMemberNo(TEST_ID, TEST_PW)).thenReturn(expectedMemberNo);

        // when
        Long memberNo = memberService.findMemberNo(TEST_ID, TEST_PW);

        // then
        assertThat(memberNo).isEqualTo(expectedMemberNo);
        verify(memberMapper, times(1)).selectMemberNo(TEST_ID, TEST_PW);
    }

    @Test
    void findMemberNoFailTest() {
        // given
        when(memberMapper.selectMemberNo(TEST_ID, TEST_PW)).thenReturn(null);

        // when & then
        assertThrows(IllegalStateException.class,
                () -> memberService.findMemberNo(TEST_ID, TEST_PW));
        verify(memberMapper, times(1)).selectMemberNo(TEST_ID, TEST_PW);
    }

    @Test
    void loginSuccessTest() {
        // given
        MemberSessionDTO expectedSession = new MemberSessionDTO();
        expectedSession.setMemberId(TEST_ID);
        when(memberMapper.selectLoginInfo(TEST_ID, TEST_PW)).thenReturn(expectedSession);

        // when
        MemberSessionDTO loginInfo = memberService.login(TEST_ID, TEST_PW);

        // then
        assertThat(loginInfo).isNotNull();
        assertThat(loginInfo.getMemberId()).isEqualTo(TEST_ID);
        verify(memberMapper, times(1)).selectLoginInfo(TEST_ID, TEST_PW);
    }

    @Test
    void loginFailTest() {
        // given
        when(memberMapper.selectLoginInfo(TEST_ID, TEST_PW)).thenReturn(null);

        // when & then
        assertThrows(IllegalStateException.class,
                () -> memberService.login(TEST_ID, TEST_PW));
        verify(memberMapper, times(1)).selectLoginInfo(TEST_ID, TEST_PW);
    }


    @Test
    void checkNickname_Available() {
        // given
        String nickname = "testNick";
        when(memberMapper.checkNickname(nickname)).thenReturn(0);

        // when
        boolean result = memberService.checkNickname(nickname);

        // then
        assertThat(result).isTrue();
        verify(memberMapper).checkNickname(nickname);
    }

    @Test
    void checkNickname_NotAvailable() {
        // given
        String nickname = "testNick";
        when(memberMapper.checkNickname(nickname)).thenReturn(1);

        // when
        boolean result = memberService.checkNickname(nickname);

        // then
        assertThat(result).isFalse();
        verify(memberMapper).checkNickname(nickname);
    }

    @Test
    void checkNickname_EmptyString() {
        // given
        String nickname = "";

        // when & then
        assertThatThrownBy(() -> memberService.checkNickname(nickname))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("닉네임은 필수 입력값입니다.");
    }

    @Test
    void checkEmail_Available() {
        // given
        String email = "test@example.com";
        when(memberMapper.checkEmail(email)).thenReturn(0);

        // when
        boolean result = memberService.checkEmail(email);

        // then
        assertThat(result).isTrue();
        verify(memberMapper).checkEmail(email);
    }

    @Test
    void checkEmail_NotAvailable() {
        // given
        String email = "test@example.com";
        when(memberMapper.checkEmail(email)).thenReturn(1);

        // when
        boolean result = memberService.checkEmail(email);

        // then
        assertThat(result).isFalse();
        verify(memberMapper).checkEmail(email);
    }

    @Test
    void checkEmail_InvalidFormat() {
        // given
        String email = "invalid-email";

        // when & then
        assertThatThrownBy(() -> memberService.checkEmail(email))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 이메일 형식입니다.");
    }
}