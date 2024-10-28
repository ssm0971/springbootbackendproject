package com.example.hope_dog.controller.centermember;

import com.example.hope_dog.dto.centerMember.CenterMemberSessionDTO;
import com.example.hope_dog.service.centermember.CenterMemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CenterMemberController.class)
class CenterMemberControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private CenterMemberService centerMemberService;
//
//    @Test
//    @DisplayName("회원가입 페이지 이동 테스트")
//    void joinForm() throws Exception {
//        mockMvc.perform(get("/center/center-join"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("center/center-join"))
//                .andDo(print());
//    }
//
//    @Test
//    @DisplayName("로그인 페이지 이동 테스트")
//    void loginForm() throws Exception {
//        mockMvc.perform(get("/center/center-login"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("center/center-login"))
//                .andDo(print());
//    }
//
//    @Test
//    @DisplayName("회원가입 성공 테스트")
//    void joinSuccess() throws Exception {
//        // given
//        MockMultipartFile file = new MockMultipartFile(
//                "businessFile",
//                "test.jpg",
//                "image/jpeg",
//                "test file content".getBytes()
//        );
//
//        // when & then
//        mockMvc.perform(multipart("/center/join")
//                        .file(file)
//                        .param("centerMemberId", "testCenter")
//                        .param("centerMemberPw", "test123!")
//                        .param("centerMemberName", "테스트센터")
//                        .param("centerMemberEmail", "test@center.com")
//                        .param("centerMemberPhoneNumber", "010-1234-5678")
//                        .param("centerMemberZipcode", "12345")
//                        .param("centerMemberAddress", "테스트시 테스트구")
//                        .param("centerMemberDetailAddress", "테스트동 123"))
//                .andExpect(status().isOk())
//                .andDo(print());
//    }
//
//    @Test
//    @DisplayName("회원가입 실패 테스트 - 이메일 중복")
//    void joinFailDueToEmailDuplication() throws Exception {
//        // given
//        doThrow(new IllegalArgumentException("이미 사용 중인 이메일입니다."))
//                .when(centerMemberService).join(any());
//
//        MockMultipartFile file = new MockMultipartFile(
//                "businessFile",
//                "test.jpg",
//                "image/jpeg",
//                "test file content".getBytes()
//        );
//
//        // when & then
//        mockMvc.perform(multipart("/center/join")
//                        .file(file)
//                        .param("centerMemberEmail", "duplicate@center.com"))
//                .andExpect(status().isBadRequest())
//                .andExpect(content().string("이미 사용 중인 이메일입니다."))
//                .andDo(print());
//    }
//
//    @Test
//    @DisplayName("로그인 성공 테스트")
//    void loginSuccess() throws Exception {
//        // given
//        CenterMemberSessionDTO sessionDTO = new CenterMemberSessionDTO();
//        sessionDTO.setCenterMemberNo(1L);
//        sessionDTO.setCenterMemberId("testCenter");
//
//        given(centerMemberService.login(anyString(), anyString())).willReturn(sessionDTO);
//
//        // when & then
//        mockMvc.perform(post("/center/login")
//                        .param("centerId", "testCenter")
//                        .param("centerPw", "test123!"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/center/main"))
//                .andDo(print());
//    }
//
//    @Test
//    @DisplayName("로그인 실패 테스트")
//    void loginFail() throws Exception {
//        // given
//        given(centerMemberService.login(anyString(), anyString()))
//                .willThrow(new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다."));
//
//        // when & then
//        mockMvc.perform(post("/center/login")
//                        .param("centerId", "wrongId")
//                        .param("centerPw", "wrongPw"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/center/login"))
//                .andExpect(flash().attributeExists("loginError"))
//                .andDo(print());
//    }
//
//    @Test
//    @DisplayName("이메일 중복 체크 테스트")
//    void checkEmail() throws Exception {
//        // given
//        given(centerMemberService.checkEmail(anyString())).willReturn(true);
//
//        // when & then
//        mockMvc.perform(get("/center/check-email")
//                        .param("email", "test@center.com"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.available").value(true))
//                .andDo(print());
//    }
//
//    @Test
//    @DisplayName("아이디 중복 체크 테스트")
//    void checkId() throws Exception {
//        // given
//        given(centerMemberService.checkId(anyString())).willReturn(true);
//
//        // when & then
//        mockMvc.perform(get("/center/check-id")
//                        .param("id", "testCenter"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.available").value(true))
//                .andDo(print());
//    }
}

