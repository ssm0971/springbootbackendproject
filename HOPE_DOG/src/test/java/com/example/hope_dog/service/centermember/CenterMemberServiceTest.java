package com.example.hope_dog.service.centermember;

import com.example.hope_dog.dto.centerMember.CenterMemberDTO;
import com.example.hope_dog.dto.centerMember.CenterMemberSessionDTO;
import com.example.hope_dog.mapper.centermember.CenterMemberMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CenterMemberServiceTest {

    @Mock
    private CenterMemberMapper centerMemberMapper;

    @InjectMocks
    private CenterMemberService centerMemberService;

    /**
     * 테스트용 DTO 생성 메서드
     */
    private CenterMemberDTO createTestMemberDTO() {
        CenterMemberDTO memberDTO = new CenterMemberDTO();
        memberDTO.setCenterMemberId("testCenter");
        memberDTO.setCenterMemberPw("test123!");
        memberDTO.setCenterMemberName("테스트센터");
        memberDTO.setCenterMemberEmail("test@center.com");
        memberDTO.setCenterMemberPhoneNumber("010-1234-5678");
        memberDTO.setCenterMemberZipcode("12345");
        memberDTO.setCenterMemberAddress("테스트시 테스트구");
        memberDTO.setCenterMemberDetailAddress("테스트동 123");

        // Mock 파일 생성
        MockMultipartFile file = new MockMultipartFile(
                "businessFile",
                "test.jpg",
                "image/jpeg",
                "test file content".getBytes()
        );
        memberDTO.setBusinessFile(file);

        return memberDTO;
    }

    @Test
    @DisplayName("회원가입 성공 테스트")
    void joinSuccess() {
        // given
        CenterMemberDTO memberDTO = createTestMemberDTO();
        when(centerMemberMapper.checkCenterEmail(any())).thenReturn(0);
        when(centerMemberMapper.checkCenterId(any())).thenReturn(0);

        // when
        centerMemberService.join(memberDTO);

        // then
        verify(centerMemberMapper, times(1)).insertCenterMember(any());
        verify(centerMemberMapper, times(1)).insertCenterFile(any());
    }

    @Test
    @DisplayName("이메일 중복 시 회원가입 실패 테스트")
    void joinFailDueToEmailDuplication() {
        // given
        CenterMemberDTO memberDTO = createTestMemberDTO();
        when(centerMemberMapper.checkCenterEmail(any())).thenReturn(1);

        // when & then
        assertThatThrownBy(() -> centerMemberService.join(memberDTO))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 사용 중인 이메일입니다.");
    }

    @Test
    @DisplayName("로그인 성공 테스트")
    void loginSuccess() {
        // given
        String centerId = "testCenter";
        String centerPw = "test123!";
        CenterMemberSessionDTO sessionDTO = new CenterMemberSessionDTO();
        sessionDTO.setCenterMemberId(centerId);

        when(centerMemberMapper.selectCenterLoginInfo(centerId, centerPw)).thenReturn(sessionDTO);

        // when
        CenterMemberSessionDTO result = centerMemberService.login(centerId, centerPw);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getCenterMemberId()).isEqualTo(centerId);
    }

    @Test
    @DisplayName("로그인 실패 테스트")
    void loginFail() {
        // given
        String centerId = "wrongId";
        String centerPw = "wrongPw";
        when(centerMemberMapper.selectCenterLoginInfo(centerId, centerPw)).thenReturn(null);

        // when & then
        assertThatThrownBy(() -> centerMemberService.login(centerId, centerPw))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("아이디 또는 비밀번호가 일치하지 않습니다.");
    }
}