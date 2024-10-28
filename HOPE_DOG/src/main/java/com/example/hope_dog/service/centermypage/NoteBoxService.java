package com.example.hope_dog.service.centermypage;

import com.example.hope_dog.dto.centermypage.notebox.NoteboxReceiveListDTO;
import com.example.hope_dog.dto.centermypage.notebox.NoteboxSendListDTO;
import com.example.hope_dog.dto.centermypage.notebox.NoteboxWriteDTO;
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

    // 닉네임 확인
    public Long findMemberNoByNickname(String nickname) {
        if (nickname == null) {
            throw new IllegalArgumentException("닉네임은 null일 수 없습니다.");
        }

        if ("관리자".equals(nickname)) {
            return 3L; // 관리자일 경우 3을 반환
        }

        // 센터회원 또는 일반회원 테이블에서 조회
        Long centerMemberNo = noteBoxMapper.findCenterMemberNoByNickname(nickname);
        return (centerMemberNo != null)
                ? centerMemberNo
                : noteBoxMapper.findMemberNoByNickname(nickname); // 일반회원 반환
    }

    // 쪽지 전송
    public void sendingNote(NoteboxWriteDTO noteboxWriteDTO) {
        Long senderNo = (Long) session.getAttribute("centerMemberNo");
        Long receiverNo = findMemberNoByNickname(noteboxWriteDTO.getNoteboxReceiverName());

        if (receiverNo == null) {
            throw new IllegalArgumentException("수신자를 찾을 수 없습니다.");
        }

        // DTO에 수신자 번호와 발신자 번호 설정
        noteboxWriteDTO.setNoteboxReceiveNo(receiverNo);
        noteboxWriteDTO.setNoteboxSender(senderNo); // 발신자 번호 설정

        // 쪽지 전송
        noteBoxMapper.sendingNote(noteboxWriteDTO);
    }
}
