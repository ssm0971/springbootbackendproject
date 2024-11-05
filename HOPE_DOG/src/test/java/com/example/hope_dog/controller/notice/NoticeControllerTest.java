//package com.example.hope_dog.controller.notice;
//
//import com.example.hope_dog.dto.notice.NoticeListDTO;
//import com.example.hope_dog.dto.notice.NoticeViewDTO;
//import com.example.hope_dog.service.notice.NoticeService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//
//@WebMvcTest(NoticeController.class)
//class NoticeControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private NoticeService noticeService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testList() throws Exception {
//        // Given
//        List<NoticeListDTO> mockNoticeList = new ArrayList<>();
//        when(noticeService.getNoticeList()).thenReturn(mockNoticeList);
//
//        // When & Then
//        mockMvc.perform(get("/notice/list"))
//                .andExpect(view().name("notice/notice-list"))
//                .andExpect(model().attribute("noticeList", mockNoticeList));
//
//        verify(noticeService).getNoticeList();
//    }
//
//    @Test
//    void testView() throws Exception {
//        // Given
//        Long noticeNo = 1L;
//        List<NoticeViewDTO> mockNoticeViewList = new ArrayList<>();
//        when(noticeService.getNoticeViewList(noticeNo)).thenReturn(mockNoticeViewList);
//
//        // When & Then
//        mockMvc.perform(get("/notice/view?noticeNo=" + noticeNo))
//                .andExpect(view().name("notice/notice-detail"))
//                .andExpect(model().attribute("noticeViewList", mockNoticeViewList));
//
//        verify(noticeService).getNoticeViewList(noticeNo);
//    }
//}
