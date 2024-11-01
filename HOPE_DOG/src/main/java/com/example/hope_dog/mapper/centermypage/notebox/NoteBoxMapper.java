package com.example.hope_dog.mapper.centermypage.notebox;

import com.example.hope_dog.dto.centermypage.notebox.*;
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
    //받은쪽지 insert
    void sendingNoteSend(NoteboxWriteDTO noteboxWriteDTO);
    //보낸쪽지 insert
    void sendingNoteReceive(NoteboxWriteDTO noteboxWriteDTO);

    // 보낸 쪽지 상세 정보 조회
    NoteboxSendDetailDTO getNoteboxSendDetail(Long noteboxSendNo);

    // 받은 쪽지 상세 진입시 읽음 전환
    void updateNoteboxReceiveRead(Long noteboxReceiveNo);

    // 받은 쪽지 상세 정보 조회
    NoteboxReceiveDetailDTO getNoteboxReceiveDetail(Long noteboxReceiveNo);

    // 쪽지 삭제
    int deleteNoteByReceiveNo(Long noteboxReceiveNo);
}


