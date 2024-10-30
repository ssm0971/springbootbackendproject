package com.example.hope_dog.mapper.centermypage.notebox;

import com.example.hope_dog.dto.centermypage.notebox.NoteboxReceiveListDTO;
import com.example.hope_dog.dto.centermypage.notebox.NoteboxSendListDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class NoteBoxMapperTest {

    @Autowired
    private NoteBoxMapper noteBoxMapper;

//    보낸쪽지함 목록
//    @Test
//    void testFindNoteboxSendList() {
//        Long noteboxMemberNo = 12L; // 테스트에 사용할 centerMemberNo
//
//        List<NoteboxSendListDTO> result = noteBoxMapper.sendList(noteboxMemberNo);
//
//        assertThat(result).isNotNull();
//        assertThat(result).isNotEmpty(); // 결과가 비어있지 않음을 확인
//        // 추가적인 Assertions을 통해 결과 검증 가능
//    }
//
////    받은쪽지함 목록
//    @Test
//    void testFindNoteboxReceiveList() {
//        Long noteboxMemberNo = 12L;
//
//        List<NoteboxReceiveListDTO> result = noteBoxMapper.receiveList(noteboxMemberNo);
//
//        assertThat(result).isNotNull();
//        assertThat(result).isNotEmpty();
//
//
//
//    }


}