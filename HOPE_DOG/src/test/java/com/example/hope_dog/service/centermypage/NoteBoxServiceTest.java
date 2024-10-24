package com.example.hope_dog.service.centermypage;

import com.example.hope_dog.dto.centermypage.notebox.NoteboxSendListDTO;
import com.example.hope_dog.mapper.centermypage.notebox.NoteBoxMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class NoteBoxServiceTest {

    @Autowired
    private NoteBoxService noteBoxService;

    @Mock
    private NoteBoxMapper noteBoxMapper;

    @Test
    void testGetSendList() {
        Long centerMemberNo = 21L;

        NoteboxSendListDTO dto = new NoteboxSendListDTO();
        dto.setNoteboxSendTitle("Test Title");
        dto.setNoteboxSendContent("Test Content");
        dto.setNoteboxSendRediDate("2024-01-01");
        dto.setNoteboxSendR(21L);

        // Mocking the mapper's response
        when(noteBoxMapper.selectSendList(centerMemberNo)).thenReturn(List.of(dto));

        // Call the service method
        List<NoteboxSendListDTO> result = noteBoxService.getSendList(centerMemberNo);

        // Assertions
        assertThat(result).isNotNull();
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getNoteboxSendTitle()).isEqualTo("Test Title");
    }

}