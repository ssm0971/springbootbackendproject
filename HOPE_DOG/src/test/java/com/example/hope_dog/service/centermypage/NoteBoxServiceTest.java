package com.example.hope_dog.service.centermypage;

import com.example.hope_dog.dto.centermypage.notebox.NoteboxReceiveListDTO;
import com.example.hope_dog.dto.centermypage.notebox.NoteboxSendListDTO;
import com.example.hope_dog.mapper.centermypage.notebox.NoteBoxMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class NoteBoxServiceTest {

    @Autowired
    private NoteBoxService noteBoxService;

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
}