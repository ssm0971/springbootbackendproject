package com.example.hope_dog.service.mypage;

import com.example.hope_dog.dto.mypage.*;
import com.example.hope_dog.mapper.mypage.MpNoteBoxMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // Lombok을 사용하여 생성자 주입
public class MpNoteBoxService {

    private final MpNoteBoxMapper mpNoteBoxMapper;
    private final HttpSession session; // 세션을 주입받음

    // 보낸 쪽지 목록
    public List<MypageNoteSendDTO> getSendList(Long memberNo) {
        return mpNoteBoxMapper.sendList(memberNo);
    }

    // 받은 쪽지 목록
    public List<MypageNoteReceiveDTO> getReceiveList(Long memberNo) {
        return mpNoteBoxMapper.receiveList(memberNo);
    }

    // 보낸 쪽지 상세페이지
    public MpNoteboxSendDetailDTO getNoteboxSendDetail(Long noteboxSendNo) {
        return mpNoteBoxMapper.getNoteboxSendDetail(noteboxSendNo);
    }

    //받은 쪽지 상세페이지
    public MpNoteboxReceiveDetailDTO getNoteboxReceiveDetail(Long noteboxReceiveNo) {
        // 쪽지 읽음 표시 업데이트
        mpNoteBoxMapper.updateNoteboxReceiveRead(noteboxReceiveNo);

        // 쪽지 상세 정보 가져오기
        return mpNoteBoxMapper.getNoteboxReceiveDetail(noteboxReceiveNo);
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
        Long centerMemberNo = mpNoteBoxMapper.findCenterMemberNoByNickname(nickname);

        if (centerMemberNo != null) {
            return centerMemberNo; // 센터회원이 존재하는 경우
        } else {
            return mpNoteBoxMapper.findMemberNoByNickname(nickname); // 일반회원 반환
        }
    }


    // 쪽지 전송
    public void sendingNote(MpNoteboxWriteDTO mpNoteboxWriteDTO) {
        Long senderNo = (Long) session.getAttribute("memberNo");
        Long receiverNo = findMemberNoByNickname(mpNoteboxWriteDTO.getNoteboxReceiverName());

        if (receiverNo == null) {
            throw new IllegalArgumentException("수신자를 찾을 수 없습니다.");
        }

        // DTO에 수신자 번호와 발신자 번호 설정
        mpNoteboxWriteDTO.setNoteboxR(receiverNo); // 수신자 번호 설정
        mpNoteboxWriteDTO.setNoteboxS(senderNo); // 발신자 번호 설정

        // 쪽지 전송
        mpNoteBoxMapper.sendingNoteReceive(mpNoteboxWriteDTO);
        mpNoteBoxMapper.sendingNoteSend(mpNoteboxWriteDTO);
    }


    //쪽지 삭제 진행
    // 쪽지 삭제 메서드
    public boolean deleteNoteByReceiveNo(Long noteboxReceiveNo) {
        int deletedRows = mpNoteBoxMapper.deleteNoteByReceiveNo(noteboxReceiveNo);
        return deletedRows > 0;
    }
}
