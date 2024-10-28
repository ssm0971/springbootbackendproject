package com.example.hope_dog.service.centermypage;

import com.example.hope_dog.dto.centermypage.notebox.NoteboxReceiveListDTO;
import com.example.hope_dog.dto.centermypage.notebox.NoteboxSendListDTO;
import com.example.hope_dog.dto.centermypage.notebox.NoteboxWriteDTO;
import com.example.hope_dog.mapper.centermypage.notebox.NoteBoxMapper;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class NoteBoxServiceTest {

    @Autowired
    private NoteBoxService noteBoxService;

    @MockBean
    private NoteBoxMapper noteBoxMapper;

    @MockBean
    private HttpSession session;

    @Test
    public void testSendList() {
        Long centerMemberNo = 12L; // 테스트용 값
        List<NoteboxSendListDTO> result = noteBoxService.getSendList(centerMemberNo);
        assertNotNull(result);
        assertFalse(result.isEmpty());

        for (NoteboxSendListDTO dto : result) {
            assertNotNull(dto.getNoteboxSendTitle());
            assertNotNull(dto.getNoteboxSendContent());

        }
    }

    @Test
    public void testReceiveList() {
        Long centerMemberNo = 12L; // 테스트용 값
        List<NoteboxReceiveListDTO> result = noteBoxService.getReceiveList(centerMemberNo);
        assertNotNull(result);
        assertFalse(result.isEmpty());

        for (NoteboxReceiveListDTO dto : result) {
            assertNotNull(dto.getNoteboxReceiveTitle());
            assertNotNull(dto.getNoteboxReceiveContent());

        }
    }

    @Test
    public void testFindMemberNoByNickname_Admin() {
        Long expectedMemberNo = 3L;
        Long actualMemberNo = noteBoxService.findMemberNoByNickname("관리자");
        assertEquals(expectedMemberNo, actualMemberNo);
    }

    @Test
    public void testFindMemberNoByNickname_CenterMember() {
        String nickname = "서울센터";
        Long expectedMemberNo = 11L;

        when(noteBoxMapper.findCenterMemberNoByNickname(nickname)).thenReturn(expectedMemberNo);

        Long actualMemberNo = noteBoxService.findMemberNoByNickname(nickname);
        assertEquals(expectedMemberNo, actualMemberNo);
    }

    @Test
    public void testFindMemberNoByNickname_GeneralMember() {
        String nickname = "철수짱";
        Long expectedMemberNo = 12L;

        when(noteBoxMapper.findCenterMemberNoByNickname(nickname)).thenReturn(null);
        when(noteBoxMapper.findMemberNoByNickname(nickname)).thenReturn(expectedMemberNo);

        Long actualMemberNo = noteBoxService.findMemberNoByNickname(nickname);
        assertEquals(expectedMemberNo, actualMemberNo);
    }

//    @Test
//    public void testSendingNote() {
//        NoteboxWriteDTO noteboxWriteDTO = new NoteboxWriteDTO();
//        noteboxWriteDTO.setNoteboxReceiverName("철수짱");
//
//        Long senderNo = 12L;
//        Long receiverNo = 11L;
//
//        when(session.getAttribute("centerMemberNo")).thenReturn(senderNo);
//        when(noteBoxMapper.findCenterMemberNoByNickname("일반회원")).thenReturn(null);
//        when(noteBoxMapper.findMemberNoByNickname("일반회원")).thenReturn(receiverNo);
//
//        noteBoxService.sendingNote(noteboxWriteDTO);
//
//        assertEquals(receiverNo, noteboxWriteDTO.getNoteboxReceiveNo());
//        assertEquals(senderNo, noteboxWriteDTO.getNoteboxSender());
//        verify(noteBoxMapper, times(1)).sendingNote(noteboxWriteDTO);
//    }
//
//    @Test
//    public void testFindMemberNoByNickname_NullNickname() {
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//            noteBoxService.findMemberNoByNickname(null);
//        });
//        assertEquals("닉네임은 null일 수 없습니다.", exception.getMessage());
//    }
//
//    @Test
//    public void testSendingNote_ReceiverNotFound() {
//        NoteboxWriteDTO noteboxWriteDTO = new NoteboxWriteDTO();
//        noteboxWriteDTO.setNoteboxReceiverName("존재하지 않는 회원");
//
//        when(session.getAttribute("centerMemberNo")).thenReturn(10L);
//        when(noteBoxMapper.findCenterMemberNoByNickname("존재하지 않는 회원")).thenReturn(null);
//        when(noteBoxMapper.findMemberNoByNickname("존재하지 않는 회원")).thenReturn(null);
//
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//            noteBoxService.sendingNote(noteboxWriteDTO);
//        });
//        assertEquals("수신자를 찾을 수 없습니다.", exception.getMessage());
//    }

}