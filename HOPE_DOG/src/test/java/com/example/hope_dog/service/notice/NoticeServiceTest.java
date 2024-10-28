package com.example.hope_dog.service.notice;

import com.example.hope_dog.dto.notice.NoticeListDTO;
import com.example.hope_dog.dto.notice.NoticeViewDTO;
import com.example.hope_dog.mapper.notice.NoticeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class NoticeServiceTest {

    private NoticeService noticeService;

    @Mock
    private NoticeMapper noticeMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Injecting the mocked NoticeMapper into the NoticeService manually
        noticeService = new NoticeService(noticeMapper);
    }

    @Test
    void testGetNoticeList() {
        // Given
        List<NoticeListDTO> mockNoticeList = new ArrayList<>();
        when(noticeMapper.noticeList()).thenReturn(mockNoticeList);

        // When
        List<NoticeListDTO> noticeList = noticeService.getNoticeList();

        // Then
        verify(noticeMapper).noticeList();
        assertEquals(mockNoticeList, noticeList);
    }

    @Test
    void testGetNoticeViewList() {
        // Given
        Long noticeNo = 1L;
        List<NoticeViewDTO> mockNoticeViewList = new ArrayList<>();
        when(noticeMapper.noticeView(noticeNo)).thenReturn(mockNoticeViewList);

        // When
        List<NoticeViewDTO> noticeViewList = noticeService.getNoticeViewList(noticeNo);

        // Then
        verify(noticeMapper).noticeView(noticeNo);
        assertEquals(mockNoticeViewList, noticeViewList); // 내용 비교
    }
}
