package com.example.hope_dog.service.centermypage;

import com.example.hope_dog.dto.centermypage.notebox.*;
import com.example.hope_dog.mapper.centermypage.notebox.NoteBoxMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // Lombok을 사용하여 생성자 주입
public class NoteBoxService {

    private final NoteBoxMapper noteBoxMapper;
    private final HttpSession session; // 세션을 주입받음

    // 보낸 쪽지 목록
    public List<NoteboxSendListDTO> getSendList(Long centerMemberNo) {
        return noteBoxMapper.sendList(centerMemberNo);
    }

    // 받은 쪽지 목록
    public List<NoteboxReceiveListDTO> getReceiveList(Long centerMemberNo) {
        return noteBoxMapper.receiveList(centerMemberNo);
    }

    // 보낸 쪽지 상세페이지
    public NoteboxSendDetailDTO getNoteboxSendDetail(Long noteboxSendNo) {
        return noteBoxMapper.getNoteboxSendDetail(noteboxSendNo);
    }

    //받은 쪽지 상세페이지
    public NoteboxReceiveDetailDTO getNoteboxReceiveDetail(Long noteboxReceiveNo) {
        // 쪽지 읽음 표시 업데이트
        noteBoxMapper.updateNoteboxReceiveRead(noteboxReceiveNo);

        // 쪽지 상세 정보 가져오기
        return noteBoxMapper.getNoteboxReceiveDetail(noteboxReceiveNo);
    }

    // 닉네임으로 회원번호 확인
    public Long findMemberNoByNickname(String nickname) {
        if (nickname == null) {
            throw new IllegalArgumentException("닉네임은 null일 수 없습니다.");
        }

        if ("관리자".equals(nickname)) {
            return 3L; // 관리자일 경우 3을 반환
        }

        // 센터회원 또는 일반회원 테이블에서 조회
        Long centerMemberNo = noteBoxMapper.findCenterMemberNoByNickname(nickname);

        if (centerMemberNo != null) {
            return centerMemberNo; // 센터회원이 존재하는 경우
        } else {
            return noteBoxMapper.findMemberNoByNickname(nickname); // 일반회원 반환
        }
    }


    // 쪽지 전송
    public void sendingNote(NoteboxWriteDTO noteboxWriteDTO) {
        Long senderNo = (Long) session.getAttribute("centerMemberNo");
        Long receiverNo = findMemberNoByNickname(noteboxWriteDTO.getNoteboxReceiverName());

        if (receiverNo == null) {
            throw new IllegalArgumentException("수신자를 찾을 수 없습니다.");
        }

        // DTO에 수신자 번호와 발신자 번호 설정
        noteboxWriteDTO.setNoteboxR(receiverNo); // 수신자 번호 설정
        noteboxWriteDTO.setNoteboxS(senderNo); // 발신자 번호 설정

        // 쪽지 전송
        noteBoxMapper.sendingNoteReceive(noteboxWriteDTO);
        noteBoxMapper.sendingNoteSend(noteboxWriteDTO);
    }


    //쪽지 삭제 진행
    // 쪽지 삭제 메서드
    public boolean deleteNoteByReceiveNo(Long noteboxReceiveNo) {
        int deletedRows = noteBoxMapper.deleteNoteByReceiveNo(noteboxReceiveNo);
        return deletedRows > 0;
    }
}
