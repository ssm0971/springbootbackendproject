//package com.example.hope_dog.controller.centermypage;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//
//import jakarta.servlet.http.HttpSession;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpSession;
//import org.springframework.test.web.servlet.MockMvc;
//
//
//
//@WebMvcTest(CenterMypageController.class) // 실제 컨트롤러 클래스 이름으로 교체
//public class CenterMypageControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private HttpSession session; // HttpSession을 모킹합니다.
//
//    @InjectMocks
//    private CenterMypageController centerMypageController; // 테스트할 컨트롤러
//
//    private MockHttpSession mockHttpSession;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        mockHttpSession = new MockHttpSession();
//        // 세션에 centerMemberNo 설정 (예: 1L)
//        mockHttpSession.setAttribute("centerMemberNo", 1L);
//    }
//
//    @Test
//    public void testNoteboxWriteWithSession() throws Exception {
//        mockMvc.perform(get("/centerMypage/noteboxWrite")
//                        .session(mockHttpSession) // 모킹된 세션을 사용
//                        .contentType(MediaType.TEXT_HTML))
//                .andExpect(status().isOk()) // HTTP 상태 코드 200 확인
//                .andExpect(view().name("centermypage/notebox/center-mypage-notebox-write")); // 반환되는 뷰 이름 확인
//    }
//
//    @Test
//    public void testNoteboxWriteWithoutSession() throws Exception {
//        mockMvc.perform(get("/centerMypage/noteboxWrite")
//                        .session(new MockHttpSession()) // 빈 세션을 사용
//                        .contentType(MediaType.TEXT_HTML))
//                .andExpect(status().is3xxRedirection()) // HTTP 상태 코드 3xx 확인 (리다이렉트)
//                .andExpect(view().name("redirect:/login")); // 리다이렉트 경로 확인
//    }
//}
