package com.example.hope_dog.service.centermember;

import com.example.hope_dog.dto.centerMember.CenterFileDTO;
import com.example.hope_dog.dto.centerMember.CenterMemberDTO;
import com.example.hope_dog.dto.centerMember.CenterMemberSessionDTO;
import com.example.hope_dog.mapper.centermember.CenterMemberMapper;
import com.example.hope_dog.mapper.member.MemberMapper;
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

// Mockito를 사용하기 위한 확장 설정
@ExtendWith(MockitoExtension.class)
class CenterMemberServiceTest {

    // CenterMemberMapper Mock 객체 생성
    @Mock
    private CenterMemberMapper centerMemberMapper;

    // MemberMapper Mock 객체 생성
    @Mock
    private MemberMapper memberMapper;

    // 테스트 대상 서비스에 Mock 객체 주입
    @InjectMocks
    private CenterMemberService centerMemberService;

    /**
     * 테스트용 회원 DTO 생성 메서드
     * @return 테스트에 필요한 기본 정보가 설정된 DTO
     */
    private CenterMemberDTO createTestMemberDTO() {
        // 테스트용 DTO 생성
        CenterMemberDTO memberDTO = new CenterMemberDTO();
        memberDTO.setCenterMemberId("testCenter");
        memberDTO.setCenterMemberPw("test123!");
        memberDTO.setCenterMemberName("테스트센터");
        memberDTO.setCenterMemberEmail("test@center.com");
        memberDTO.setCenterMemberPhoneNumber("010-1234-5678");
        memberDTO.setCenterMemberZipcode("12345");
        memberDTO.setCenterMemberAddress("테스트시 테스트구");
        memberDTO.setCenterMemberDetailAddress("테스트동 123");

        // 테스트용 파일 생성 및 설정
        MockMultipartFile file = new MockMultipartFile(
                "businessFile",
                "test.jpg",
                "image/jpeg",
                "test file content".getBytes()
        );
        memberDTO.setBusinessFile(file);

        return memberDTO;
    }

    /**
     * 회원가입 성공 테스트
     * 모든 필수 정보가 정상적으로 입력된 경우
     */
    @Test
    @DisplayName("회원가입 성공 테스트")
    void joinSuccessTest() {
        // given
        CenterMemberDTO memberDTO = createTestMemberDTO();
        doAnswer(invocation -> {
            CenterMemberDTO dto = invocation.getArgument(0);
            dto.setCenterMemberNo(1L);
            return null;
        }).when(centerMemberMapper).insertCenterMember(any(CenterMemberDTO.class));

        // when
        Long result = centerMemberService.join(memberDTO);

        // then
        assertThat(result).isNotNull();
        verify(centerMemberMapper, times(1)).insertCenterMember(any(CenterMemberDTO.class));
        verify(centerMemberMapper, times(1)).insertCenterFile(any(CenterFileDTO.class));
    }

    /**
     * 로그인 성공 테스트
     * 올바른 아이디와 비밀번호로 로그인하는 경우
     */
    @Test
    @DisplayName("로그인 성공 테스트")
    void loginSuccessTest() {
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
        verify(centerMemberMapper, times(1)).selectCenterLoginInfo(centerId, centerPw);
    }

    /**
     * 로그인 실패 테스트
     * 잘못된 자격 증명으로 로그인 시도하는 경우
     */
    @Test
    @DisplayName("로그인 실패 테스트")
    void loginFailTest() {
        // given
        String centerId = "wrongId";
        String centerPw = "wrongPw";
        when(centerMemberMapper.selectCenterLoginInfo(centerId, centerPw)).thenReturn(null);

        // when & then
        assertThatThrownBy(() -> centerMemberService.login(centerId, centerPw))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("아이디 또는 비밀번호가 일치하지 않습니다.");
    }

    /**
     * 이메일 중복 체크 테스트 - 사용 가능한 경우
     */
    @Test
    @DisplayName("이메일 중복 체크 - 사용 가능한 경우")
    void checkEmailAvailableTest() {
        // given
        String email = "test@center.com";
        when(centerMemberMapper.checkCenterEmail(email)).thenReturn(0);

        // when
        boolean result = centerMemberService.checkEmail(email);

        // then
        assertThat(result).isTrue();
        verify(centerMemberMapper, times(1)).checkCenterEmail(email);
    }

    /**
     * 이메일 중복 체크 테스트 - 이미 사용 중인 경우
     */
    @Test
    @DisplayName("이메일 중복 체크 - 이미 사용 중인 경우")
    void checkEmailNotAvailableTest() {
        // given
        String email = "used@center.com";
        when(centerMemberMapper.checkCenterEmail(email)).thenReturn(1);

        // when
        boolean result = centerMemberService.checkEmail(email);

        // then
        assertThat(result).isFalse();
        verify(centerMemberMapper, times(1)).checkCenterEmail(email);
    }

    /**
     * 회원 정보 검증 실패 테스트 - 필수 정보 누락
     */
    @Test
    @DisplayName("회원 정보 검증 - 필수 정보 누락 테스트")
    void validateMemberDTOFailTest() {
        // given
        CenterMemberDTO memberDTO = new CenterMemberDTO();
        // 필수 정보를 설정하지 않음

        // when & then
        assertThatThrownBy(() -> centerMemberService.join(memberDTO))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("필수입니다");
    }
}