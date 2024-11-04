package com.example.hope_dog.mapper.mypage;

import com.example.hope_dog.dto.mypage.*;
import com.example.hope_dog.dto.mypage.MpNoteboxReceiveDetailDTO;
import com.example.hope_dog.dto.mypage.MpNoteboxSendDetailDTO;
import com.example.hope_dog.dto.mypage.MypageNoteReceiveDTO;
import com.example.hope_dog.dto.mypage.MypageNoteSendDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MpNoteBoxMapper {
    //보낸쪽지함
    List<MypageNoteSendDTO> sendList(Long memberNo);

    //받은쪽지함
    List<MypageNoteReceiveDTO> receiveList(Long memberNo);

    // 닉네임으로 일반회원 조회
    Long findMemberNoByNickname(String nickname);

    // 이름으로 센터회원 조회
    Long findCenterMemberNoByNickname(String nickname);

    //쪽지보내기
    //받은쪽지 insert
    void sendingNoteSend(MpNoteboxWriteDTO mpNoteboxWriteDTO);
    //보낸쪽지 insert
    void sendingNoteReceive(MpNoteboxWriteDTO mpNoteboxWriteDTO);

    // 보낸 쪽지 상세 정보 조회
    MpNoteboxSendDetailDTO getNoteboxSendDetail(Long noteboxSendNo);

    // 받은 쪽지 상세 진입시 읽음 전환
    void updateNoteboxReceiveRead(Long noteboxReceiveNo);

    // 받은 쪽지 상세 정보 조회
    MpNoteboxReceiveDetailDTO getNoteboxReceiveDetail(Long noteboxReceiveNo);

    // 쪽지 삭제
    int deleteNoteByReceiveNo(Long noteboxReceiveNo);
}
