package com.example.hope_dog.controller.member;

import com.example.hope_dog.dto.member.MemberDTO;
import com.example.hope_dog.dto.member.MemberSessionDTO;
import com.example.hope_dog.service.member.MemberService;
import com.example.hope_dog.service.member.SmsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.doThrow;
import com.example.hope_dog.dto.member.FindPwRequestDTO;

@WebMvcTest(MemberController.class)
@AutoConfigureMockMvc(addFilters = false)  // Spring Security 필터 비활성화
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MemberService memberService;

    @MockBean
    private SmsService smsService;

    // 1. 회원가입 페이지 이동 테스트
    @Test
    void testJoinPage() throws Exception {
        mockMvc.perform(get("/member/join"))
                .andExpect(status().isOk())
                .andExpect(view().name("member/join"))
                .andDo(print());
    }

    // 2. 로그인 페이지 이동 테스트
    @Test
    void testLoginPage() throws Exception {
        mockMvc.perform(get("/member/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("member/login"))
                .andDo(print());
    }

    // 3. 회원가입 처리 테스트
    @Test
    void testJoin() throws Exception {
        // given
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberId("testUser");
        memberDTO.setMemberPw("Test123!");
        memberDTO.setMemberName("테스트");
        memberDTO.setMemberNickname("테스트닉네임");
        memberDTO.setMemberEmail("test@test.com");
        memberDTO.setMemberPhoneNumber("01012345678");
        memberDTO.setMemberGender("M");
        memberDTO.setMemberZipcode("12345");
        memberDTO.setMemberAddress("테스트 주소");
        memberDTO.setMemberDetailAddress("상세주소");

        // when & then
        mockMvc.perform(post("/member/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberDTO)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    // 4. 회원가입 완료 페이지 테스트
    @Test
    void testJoinOk() throws Exception {
        // given
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("memberNickname", "테스트닉네임");

        // when & then
        mockMvc.perform(get("/member/joinOk")
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("member/joinOk"))
                .andExpect(model().attributeExists("memberNickname"))
                .andDo(print());
    }

    // 5. 로그인 처리 테스트
    @Test
    void testLogin() throws Exception {
        // given
        MemberSessionDTO sessionDTO = new MemberSessionDTO();
        sessionDTO.setMemberNo(1L);
        sessionDTO.setMemberId("testUser");
        sessionDTO.setMemberName("테스트");
        sessionDTO.setMemberNickname("테스트닉네임");
        sessionDTO.setMemberEmail("test@test.com");
        sessionDTO.setMemberLoginStatus("HOPEDOG");
        sessionDTO.setMemberTwoFactorEnabled("N");

        when(memberService.login(anyString(), anyString())).thenReturn(sessionDTO);

        // when & then
        mockMvc.perform(post("/member/login")
                        .param("memberId", "testUser")
                        .param("memberPw", "Test123!"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/main/main"))
                .andDo(print());
    }

    // 6. 로그아웃 테스트
    @Test
    void testLogout() throws Exception {
        mockMvc.perform(get("/member/logout"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/main/main"))
                .andDo(print());
    }

    // 7. 약관동의 페이지 테스트
    @Test
    void testTerms() throws Exception {
        mockMvc.perform(get("/member/terms"))
                .andExpect(status().isOk())
                .andExpect(view().name("member/terms"))
                .andExpect(model().attributeExists("terms"))
                .andDo(print());
    }

    // 8. 닉네임 중복 체크 테스트
    @Test
    void testCheckNickname() throws Exception {
        // given
        when(memberService.checkNickname(anyString())).thenReturn(true);

        // when & then
        mockMvc.perform(get("/member/check-nickname")
                        .param("nickname", "테스트닉네임"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.available").value(true))
                .andDo(print());
    }

    // 9. 이메일 중복 체크 테스트
    @Test
    void testCheckEmail() throws Exception {
        // given
        when(memberService.checkEmail(anyString())).thenReturn(true);

        // when & then
        mockMvc.perform(get("/member/check-email")
                        .param("email", "test@test.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.available").value(true))
                .andDo(print());
    }

    // 10. 로그인 선택 페이지 테스트
    @Test
    void testLoginSelect() throws Exception {
        mockMvc.perform(get("/member/login-select"))
                .andExpect(status().isOk())
                .andExpect(view().name("member/login-select"))
                .andDo(print());
    }

    // 11. 회원가입 선택 페이지 테스트
    @Test
    void testJoinSelect() throws Exception {
        mockMvc.perform(get("/member/join-select"))
                .andExpect(status().isOk())
                .andExpect(view().name("member/join-select"))
                .andDo(print());
    }

    // 12. 닉네임 중복 체크 실패 테스트
    @Test
    void testCheckNicknameFail() throws Exception {
        // given
        when(memberService.checkNickname(anyString())).thenReturn(false);

        // when & then
        mockMvc.perform(get("/member/check-nickname")
                        .param("nickname", "이미존재하는닉네임"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.available").value(false))
                .andDo(print());
    }

    // 13. 이메일 중복 체크 실패 테스트
    @Test
    void testCheckEmailFail() throws Exception {
        // given
        when(memberService.checkEmail(anyString())).thenReturn(false);

        // when & then
        mockMvc.perform(get("/member/check-email")
                        .param("email", "existing@test.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.available").value(false))
                .andDo(print());
    }

    // 14. 로그인 실패 테스트
    @Test
    void testLoginFail() throws Exception {
        // given
        when(memberService.login(anyString(), anyString()))
                .thenThrow(new IllegalArgumentException("Invalid credentials"));

        // when & then
        mockMvc.perform(post("/member/login")
                        .param("memberId", "wrongId")
                        .param("memberPw", "wrongPw"))
                .andExpect(status().is3xxRedirection())  // 리다이렉션 상태 확인
                .andExpect(redirectedUrl("/member/login"))  // 로그인 페이지로 리다이렉트
                .andExpect(flash().attributeExists("loginError"))  // 에러 메시지 확인
                .andDo(print());
    }


    // 15. 아이디 찾기 페이지 이동 테스트
    @Test
    void testFindIdPage() throws Exception {
        mockMvc.perform(get("/member/find-id"))
                .andExpect(status().isOk())
                .andExpect(view().name("member/find-id"))
                .andDo(print());
    }

    // 16. 아이디 찾기 성공 테스트
    @Test
    void testFindIdSuccess() throws Exception {
        // given - 아이디 찾기 성공 시나리오 설정
        when(memberService.findMemberId(anyString(), anyString()))
                .thenReturn("testUser");

        // when & then
        mockMvc.perform(post("/member/find-id")
                        .param("memberName", "테스트")
                        .param("memberPhoneNumber", "01012345678"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.memberId").value("testUser"))
                .andDo(print());
    }

    // 17. 아이디 찾기 실패 테스트
    @Test
    void testFindIdFail() throws Exception {
        // given - 아이디 찾기 실패 시나리오 설정
        when(memberService.findMemberId(anyString(), anyString()))
                .thenThrow(new IllegalArgumentException("회원 정보를 찾을 수 없습니다."));

        // when & then
        mockMvc.perform(post("/member/find-id")
                        .param("memberName", "존재하지않는이름")
                        .param("memberPhoneNumber", "01099999999"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").exists())
                .andDo(print());
    }

    // 18. 아이디 찾기 완료 페이지 테스트
    @Test
    void testFindIdOk() throws Exception {
        // given - 찾은 아이디와 이름 파라미터 설정
        String memberName = "테스트";
        String memberId = "testUser";

        // when & then
        mockMvc.perform(get("/member/find-idOk")
                        .param("memberName", memberName)
                        .param("memberId", memberId))
                .andExpect(status().isOk())
                .andExpect(view().name("member/find-idOk"))
                .andExpect(model().attribute("memberName", memberName))
                .andExpect(model().attribute("memberId", memberId))
                .andDo(print());
    }

    // 19. 비밀번호 찾기 페이지 이동 테스트
    @Test
    void testFindPwPage() throws Exception {
        mockMvc.perform(get("/member/find-pw"))
                .andExpect(status().isOk())
                .andExpect(view().name("member/find-pw"))
                .andDo(print());
    }

    // 20. 비밀번호 재설정 성공 테스트
    @Test
    void testFindPwSuccess() throws Exception {
        // given - 비밀번호 재설정 요청 DTO 생성
        FindPwRequestDTO requestDTO = new FindPwRequestDTO();
        requestDTO.setMemberName("테스트");
        requestDTO.setMemberId("testUser");
        requestDTO.setMemberEmail("test@test.com");

        // when & then
        mockMvc.perform(post("/member/find-pw")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.memberName").value("테스트"))
                .andDo(print());
    }

    // 21. 비밀번호 재설정 실패 테스트
    @Test
    void testFindPwFail() throws Exception {
        // given - 잘못된 회원 정보로 DTO 생성
        FindPwRequestDTO requestDTO = new FindPwRequestDTO();
        requestDTO.setMemberName("존재하지않는이름");
        requestDTO.setMemberId("wrongId");
        requestDTO.setMemberEmail("wrong@test.com");

        // 서비스에서 예외 발생하도록 설정
        doThrow(new IllegalArgumentException("회원 정보를 찾을 수 없습니다."))
                .when(memberService)
                .resetPassword(anyString(), anyString(), anyString());

        // when & then
        mockMvc.perform(post("/member/find-pw")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    // 22. 비밀번호 찾기 완료 페이지 테스트
    @Test
    void testFindPwOk() throws Exception {
        // given - 회원 이름 파라미터 설정
        String memberName = "테스트";

        // when & then
        mockMvc.perform(get("/member/find-pwOk")
                        .param("memberName", memberName))
                .andExpect(status().isOk())
                .andExpect(view().name("member/find-pwOk"))
                .andExpect(model().attribute("memberName", memberName))
                .andDo(print());
    }

}