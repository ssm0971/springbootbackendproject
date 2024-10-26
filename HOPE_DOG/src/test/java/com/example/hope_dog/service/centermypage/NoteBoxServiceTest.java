package com.example.hope_dog.service.centermypage;

import com.example.hope_dog.dto.centerMember.CenterMemberDTO;
import com.example.hope_dog.dto.centermypage.notebox.NoteboxSendListDTO;
import com.example.hope_dog.dto.member.MemberDTO;
import com.example.hope_dog.mapper.centermypage.notebox.NoteBoxMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class NoteBoxServiceTest {

    @Autowired
    private NoteBoxService noteBoxService;

    @Mock
    private NoteBoxMapper noteBoxMapper;

    @Test
    void testGetSendList() {
        Long centerMemberNo = 12L;

        NoteboxSendListDTO noteboxdto = new NoteboxSendListDTO();
        noteboxdto.setNoteboxSendTitle("Test Title");
        noteboxdto.setNoteboxSendContent("Test Content");
        noteboxdto.setNoteboxSendRegiDate("2024-01-01");
        noteboxdto.setNoteboxSendR(12L);


        // Mocking the mapper's response
        when(noteBoxMapper.SendList(centerMemberNo)).thenReturn(List.of(noteboxdto));

        // Call the service method
        List<NoteboxSendListDTO> result = noteBoxService.getSendList(centerMemberNo);

        // Assertions
        assertThat(result).isNotNull();
        assertThat(result).isNotEmpty();
    }

}