package com.example.hope_dog.mapper.centermypage.notebox;

import com.example.hope_dog.dto.centermypage.notebox.NoteboxReceiveListDTO;
import com.example.hope_dog.dto.centermypage.notebox.NoteboxSendListDTO;
import com.example.hope_dog.dto.centermypage.notebox.NoteboxWriteDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoteBoxMapper {

    //보낸쪽지함
    List<NoteboxSendListDTO> sendList(Long centerMemberNo);

    //받은쪽지함
    List<NoteboxReceiveListDTO> receiveList(Long centerMemberNo);

    // 닉네임으로 일반회원 조회
    Long findMemberNoByNickname(String nickname);

    // 이름으로 센터회원 조회
    Long findCenterMemberNoByNickname(String nickname);

    //쪽지보내기
    void sendingNote(NoteboxWriteDTO noteboxWriteDTO);
}


