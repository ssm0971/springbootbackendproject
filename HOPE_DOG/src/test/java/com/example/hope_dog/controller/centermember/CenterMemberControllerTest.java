<<<<<<< HEAD
//
//package com.example.hope_dog.controller.centermember;
//
//import com.example.hope_dog.dto.centerMember.CenterMemberDTO;
//import com.example.hope_dog.dto.centerMember.CenterMemberSessionDTO;
//import com.example.hope_dog.service.centermember.CenterMemberService;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.mock.web.MockHttpSession;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.doThrow;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//// Controller 테스트를 위한 WebMVC 테스트 어노테이션
//@WebMvcTest(CenterMemberController.class)
//class CenterMemberControllerTest {
//
////    @Autowired
////    private MockMvc mockMvc;
////
////    @MockBean
////    private CenterMemberService centerMemberService;
////
////    @Test
////    @DisplayName("회원가입 페이지 이동 테스트")
////    void joinForm() throws Exception {
////        mockMvc.perform(get("/center/center-join"))
////                .andExpect(status().isOk())
////                .andExpect(view().name("center/center-join"))
////                .andDo(print());
////    }
////
////    @Test
////    @DisplayName("로그인 페이지 이동 테스트")
////    void loginForm() throws Exception {
////        mockMvc.perform(get("/center/center-login"))
////                .andExpect(status().isOk())
////                .andExpect(view().name("center/center-login"))
////                .andDo(print());
////    }
////
////    @Test
////    @DisplayName("회원가입 성공 테스트")
////    void joinSuccess() throws Exception {
////        // given
////        MockMultipartFile file = new MockMultipartFile(
////                "businessFile",
////                "test.jpg",
////                "image/jpeg",
////                "test file content".getBytes()
////        );
////
////        // when & then
////        mockMvc.perform(multipart("/center/join")
////                        .file(file)
////                        .param("centerMemberId", "testCenter")
////                        .param("centerMemberPw", "test123!")
////                        .param("centerMemberName", "테스트센터")
////                        .param("centerMemberEmail", "test@center.com")
////                        .param("centerMemberPhoneNumber", "010-1234-5678")
////                        .param("centerMemberZipcode", "12345")
////                        .param("centerMemberAddress", "테스트시 테스트구")
////                        .param("centerMemberDetailAddress", "테스트동 123"))
////                .andExpect(status().isOk())
////                .andDo(print());
////    }
////
////    @Test
////    @DisplayName("회원가입 실패 테스트 - 이메일 중복")
////    void joinFailDueToEmailDuplication() throws Exception {
////        // given
////        doThrow(new IllegalArgumentException("이미 사용 중인 이메일입니다."))
////                .when(centerMemberService).join(any());
////
////        MockMultipartFile file = new MockMultipartFile(
////                "businessFile",
////                "test.jpg",
////                "image/jpeg",
////                "test file content".getBytes()
////        );
////
////        // when & then
////        mockMvc.perform(multipart("/center/join")
////                        .file(file)
////                        .param("centerMemberEmail", "duplicate@center.com"))
////                .andExpect(status().isBadRequest())
////                .andExpect(content().string("이미 사용 중인 이메일입니다."))
////                .andDo(print());
////    }
////
////    @Test
////    @DisplayName("로그인 성공 테스트")
////    void loginSuccess() throws Exception {
////        // given
////        CenterMemberSessionDTO sessionDTO = new CenterMemberSessionDTO();
////        sessionDTO.setCenterMemberNo(1L);
////        sessionDTO.setCenterMemberId("testCenter");
////
////        given(centerMemberService.login(anyString(), anyString())).willReturn(sessionDTO);
////
////        // when & then
////        mockMvc.perform(post("/center/login")
////                        .param("centerId", "testCenter")
////                        .param("centerPw", "test123!"))
////                .andExpect(status().is3xxRedirection())
////                .andExpect(redirectedUrl("/center/main"))
////                .andDo(print());
////    }
////
////    @Test
////    @DisplayName("로그인 실패 테스트")
////    void loginFail() throws Exception {
////        // given
////        given(centerMemberService.login(anyString(), anyString()))
////                .willThrow(new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다."));
////
////        // when & then
////        mockMvc.perform(post("/center/login")
////                        .param("centerId", "wrongId")
////                        .param("centerPw", "wrongPw"))
////                .andExpect(status().is3xxRedirection())
////                .andExpect(redirectedUrl("/center/login"))
////                .andExpect(flash().attributeExists("loginError"))
////                .andDo(print());
////    }
////
////    @Test
////    @DisplayName("이메일 중복 체크 테스트")
////    void checkEmail() throws Exception {
////        // given
////        given(centerMemberService.checkEmail(anyString())).willReturn(true);
////
////        // when & then
////        mockMvc.perform(get("/center/check-email")
////                        .param("email", "test@center.com"))
////                .andExpect(status().isOk())
////                .andExpect(jsonPath("$.available").value(true))
////                .andDo(print());
////    }
////
////    @Test
////    @DisplayName("아이디 중복 체크 테스트")
////    void checkId() throws Exception {
////        // given
////        given(centerMemberService.checkId(anyString())).willReturn(true);
////
////        // when & then
////        mockMvc.perform(get("/center/check-id")
////                        .param("id", "testCenter"))
////                .andExpect(status().isOk())
////                .andExpect(jsonPath("$.available").value(true))
////                .andDo(print());
////    }
////}
//
//
//
//    // MockMvc 주입
//    @Autowired
//    private MockMvc mockMvc;
//
//    // CenterMemberService Mock 객체 생성
//    @MockBean
//    private CenterMemberService centerMemberService;
//
//    /**
//     * 테스트용 MultipartFile 생성 메서드
//     * @return MockMultipartFile 객체
//     */
//    private MockMultipartFile createMockBusinessFile() {
//        return new MockMultipartFile(
=======
<<<<<<< HEAD

=======
>>>>>>> main
package com.example.hope_dog.controller.centermember;

import com.example.hope_dog.dto.centerMember.CenterMemberDTO;
import com.example.hope_dog.dto.centerMember.CenterMemberSessionDTO;
import com.example.hope_dog.service.centermember.CenterMemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// Controller 테스트를 위한 WebMVC 테스트 어노테이션
@WebMvcTest(CenterMemberController.class)
class CenterMemberControllerTest {
<<<<<<< HEAD
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
>>>>>>> main
//                "businessFile",
//                "test.jpg",
//                "image/jpeg",
//                "test file content".getBytes()
//        );
<<<<<<< HEAD
//    }
//
//    /**
//     * 로그인 페이지 접속 테스트
//     * 정상적으로 로그인 페이지로 이동하는지 확인
//     */
//    @Test
//    @DisplayName("로그인 페이지 이동 테스트")
//    void loginFormTest() throws Exception {
//        // when & then
//        mockMvc.perform(get("/center/center-login"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("center/center-login"));
//    }
//
//    /**
//     * 로그인 성공 테스트
//     * 올바른 자격증명으로 로그인 시 메인페이지로 리다이렉트되는지 확인
//     */
//    @Test
//    @DisplayName("로그인 성공 테스트")
//    void loginSuccessTest() throws Exception {
=======
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
>>>>>>> main
//        // given
//        CenterMemberSessionDTO sessionDTO = new CenterMemberSessionDTO();
//        sessionDTO.setCenterMemberNo(1L);
//        sessionDTO.setCenterMemberId("testCenter");
<<<<<<< HEAD
//        given(centerMemberService.login(anyString(), anyString())).willReturn(sessionDTO);
//
//        // when & then
//        mockMvc.perform(post("/center/center-login")
//                        .param("centerId", "testCenter")
//                        .param("centerPw", "test123!"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/main/main"));
//    }
//
//    /**
//     * 로그인 실패 테스트
//     * 잘못된 자격증명으로 로그인 시 에러 메시지와 함께 로그인 페이지로 리다이렉트되는지 확인
//     */
//    @Test
//    @DisplayName("로그인 실패 테스트")
//    void loginFailTest() throws Exception {
=======
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
>>>>>>> main
//        // given
//        given(centerMemberService.login(anyString(), anyString()))
//                .willThrow(new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다."));
//
//        // when & then
<<<<<<< HEAD
//        mockMvc.perform(post("/center/center-login")
//                        .param("centerId", "wrongId")
//                        .param("centerPw", "wrongPw"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/center/center-login"))
//                .andExpect(flash().attributeExists("loginError"));
//    }
//
//    /**
//     * 로그아웃 테스트
//     * 로그아웃 시 세션이 무효화되고 메인페이지로 리다이렉트되는지 확인
//     */
//    @Test
//    @DisplayName("로그아웃 테스트")
//    void logoutTest() throws Exception {
//        // given
//        MockHttpSession session = new MockHttpSession();
//        session.setAttribute("centerMemberNo", 1L);
//
//        // when & then
//        mockMvc.perform(get("/center/center-logout")
//                        .session(session))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/main/main"));
//    }
//
//    /**
//     * 이메일 중복 체크 API 테스트
//     * 사용 가능한 이메일인 경우를 확인
//     */
//    @Test
//    @DisplayName("이메일 중복 체크 - 사용 가능한 경우")
//    void checkEmailAvailableTest() throws Exception {
=======
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
>>>>>>> main
//        // given
//        given(centerMemberService.checkEmail(anyString())).willReturn(true);
//
//        // when & then
//        mockMvc.perform(get("/center/check-email")
<<<<<<< HEAD
//                        .param("email", "test@example.com"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.available").value(true));
//    }
//
//    /**
//     * 이메일 중복 체크 API 테스트
//     * 이미 사용 중인 이메일인 경우를 확인
//     */
//    @Test
//    @DisplayName("이메일 중복 체크 - 사용 중인 경우")
//    void checkEmailNotAvailableTest() throws Exception {
//        // given
//        given(centerMemberService.checkEmail(anyString())).willReturn(false);
//
//        // when & then
//        mockMvc.perform(get("/center/check-email")
//                        .param("email", "used@example.com"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.available").value(false));
//    }
//
//    /**
//     * 회원가입 성공 테스트
//     * 올바른 회원정보로 가입 시 성공 응답을 반환하는지 확인
//     */
//    @Test
//    @DisplayName("회원가입 성공 테스트")
//    void joinSuccessTest() throws Exception {
//        // given
//        MockMultipartFile file = createMockBusinessFile();
//        given(centerMemberService.join(any(CenterMemberDTO.class))).willReturn(1L);
//
//        // when & then
//        mockMvc.perform(multipart("/center/center-join")
//                        .file(file)
//                        .param("centerMemberId", "testCenter")
//                        .param("centerMemberPw", "test123!")
//                        .param("centerMemberName", "테스트센터")
//                        .param("centerMemberEmail", "test@center.com"))
//                .andExpect(status().isOk());
//    }
//
//    /**
//     * 회원가입 실패 테스트
//     * 유효하지 않은 회원정보로 가입 시 에러 응답을 반환하는지 확인
//     */
//    @Test
//    @DisplayName("회원가입 실패 테스트")
//    void joinFailTest() throws Exception {
//        // given
//        MockMultipartFile file = createMockBusinessFile();
//        doThrow(new IllegalArgumentException("이미 사용 중인 이메일입니다."))
//                .when(centerMemberService).join(any(CenterMemberDTO.class));
//
//        // when & then
//        mockMvc.perform(multipart("/center/center-join")
//                        .file(file)
//                        .param("centerMemberId", "testCenter")
//                        .param("centerMemberPw", "test123!")
//                        .param("centerMemberName", "테스트센터")
//                        .param("centerMemberEmail", "used@center.com"))
//                .andExpect(status().isBadRequest());
//    }
//
//    /**
//     * 약관 페이지 테스트
//     * 약관 페이지에 필요한 데이터가 정상적으로 전달되는지 확인
//     */
//    @Test
//    @DisplayName("약관 페이지 테스트")
//    void termsTest() throws Exception {
//        // when & then
//        mockMvc.perform(get("/center/center-terms"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("center/center-terms"))
//                .andExpect(model().attributeExists("terms"));
//    }
//}
//
=======
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
=======

    // MockMvc 주입
    @Autowired
    private MockMvc mockMvc;

    // CenterMemberService Mock 객체 생성
    @MockBean
    private CenterMemberService centerMemberService;

    /**
     * 테스트용 MultipartFile 생성 메서드
     * @return MockMultipartFile 객체
     */
    private MockMultipartFile createMockBusinessFile() {
        return new MockMultipartFile(
                "businessFile",
                "test.jpg",
                "image/jpeg",
                "test file content".getBytes()
        );
    }

    /**
     * 로그인 페이지 접속 테스트
     * 정상적으로 로그인 페이지로 이동하는지 확인
     */
    @Test
    @DisplayName("로그인 페이지 이동 테스트")
    void loginFormTest() throws Exception {
        // when & then
        mockMvc.perform(get("/center/center-login"))
                .andExpect(status().isOk())
                .andExpect(view().name("center/center-login"));
    }

    /**
     * 로그인 성공 테스트
     * 올바른 자격증명으로 로그인 시 메인페이지로 리다이렉트되는지 확인
     */
    @Test
    @DisplayName("로그인 성공 테스트")
    void loginSuccessTest() throws Exception {
        // given
        CenterMemberSessionDTO sessionDTO = new CenterMemberSessionDTO();
        sessionDTO.setCenterMemberNo(1L);
        sessionDTO.setCenterMemberId("testCenter");
        given(centerMemberService.login(anyString(), anyString())).willReturn(sessionDTO);

        // when & then
        mockMvc.perform(post("/center/center-login")
                        .param("centerId", "testCenter")
                        .param("centerPw", "test123!"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/main/main"));
    }

    /**
     * 로그인 실패 테스트
     * 잘못된 자격증명으로 로그인 시 에러 메시지와 함께 로그인 페이지로 리다이렉트되는지 확인
     */
    @Test
    @DisplayName("로그인 실패 테스트")
    void loginFailTest() throws Exception {
        // given
        given(centerMemberService.login(anyString(), anyString()))
                .willThrow(new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다."));

        // when & then
        mockMvc.perform(post("/center/center-login")
                        .param("centerId", "wrongId")
                        .param("centerPw", "wrongPw"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/center/center-login"))
                .andExpect(flash().attributeExists("loginError"));
    }

    /**
     * 로그아웃 테스트
     * 로그아웃 시 세션이 무효화되고 메인페이지로 리다이렉트되는지 확인
     */
    @Test
    @DisplayName("로그아웃 테스트")
    void logoutTest() throws Exception {
        // given
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("centerMemberNo", 1L);

        // when & then
        mockMvc.perform(get("/center/center-logout")
                        .session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/main/main"));
    }

    /**
     * 이메일 중복 체크 API 테스트
     * 사용 가능한 이메일인 경우를 확인
     */
    @Test
    @DisplayName("이메일 중복 체크 - 사용 가능한 경우")
    void checkEmailAvailableTest() throws Exception {
        // given
        given(centerMemberService.checkEmail(anyString())).willReturn(true);

        // when & then
        mockMvc.perform(get("/center/check-email")
                        .param("email", "test@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.available").value(true));
    }

    /**
     * 이메일 중복 체크 API 테스트
     * 이미 사용 중인 이메일인 경우를 확인
     */
    @Test
    @DisplayName("이메일 중복 체크 - 사용 중인 경우")
    void checkEmailNotAvailableTest() throws Exception {
        // given
        given(centerMemberService.checkEmail(anyString())).willReturn(false);

        // when & then
        mockMvc.perform(get("/center/check-email")
                        .param("email", "used@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.available").value(false));
    }

    /**
     * 회원가입 성공 테스트
     * 올바른 회원정보로 가입 시 성공 응답을 반환하는지 확인
     */
    @Test
    @DisplayName("회원가입 성공 테스트")
    void joinSuccessTest() throws Exception {
        // given
        MockMultipartFile file = createMockBusinessFile();
        given(centerMemberService.join(any(CenterMemberDTO.class))).willReturn(1L);

        // when & then
        mockMvc.perform(multipart("/center/center-join")
                        .file(file)
                        .param("centerMemberId", "testCenter")
                        .param("centerMemberPw", "test123!")
                        .param("centerMemberName", "테스트센터")
                        .param("centerMemberEmail", "test@center.com"))
                .andExpect(status().isOk());
    }

    /**
     * 회원가입 실패 테스트
     * 유효하지 않은 회원정보로 가입 시 에러 응답을 반환하는지 확인
     */
    @Test
    @DisplayName("회원가입 실패 테스트")
    void joinFailTest() throws Exception {
        // given
        MockMultipartFile file = createMockBusinessFile();
        doThrow(new IllegalArgumentException("이미 사용 중인 이메일입니다."))
                .when(centerMemberService).join(any(CenterMemberDTO.class));

        // when & then
        mockMvc.perform(multipart("/center/center-join")
                        .file(file)
                        .param("centerMemberId", "testCenter")
                        .param("centerMemberPw", "test123!")
                        .param("centerMemberName", "테스트센터")
                        .param("centerMemberEmail", "used@center.com"))
                .andExpect(status().isBadRequest());
    }

    /**
     * 약관 페이지 테스트
     * 약관 페이지에 필요한 데이터가 정상적으로 전달되는지 확인
     */
    @Test
    @DisplayName("약관 페이지 테스트")
    void termsTest() throws Exception {
        // when & then
        mockMvc.perform(get("/center/center-terms"))
                .andExpect(status().isOk())
                .andExpect(view().name("center/center-terms"))
                .andExpect(model().attributeExists("terms"));
    }
}
>>>>>>> main
>>>>>>> main
