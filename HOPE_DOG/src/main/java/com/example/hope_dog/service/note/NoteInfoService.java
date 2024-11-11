package com.example.hope_dog.service.note;

import com.example.hope_dog.mapper.note.NoteInfoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoteInfoService {

    private final NoteInfoMapper noteInfoMapper;

    //    안읽은 쪽지 카운트
    public Long getUnreadCount(Long userNo) {
        return noteInfoMapper.getUnreadCount(userNo);
    }

    //    안읽은 쪽지 모두 읽음 처리
    @Transactional
    public void markAllAsRead(Long userNo) {
        log.info("Marking all messages as read for user: {}", userNo); // 로그 추가
        noteInfoMapper.markAllAsRead(userNo);
        log.info("All messages marked as read for user: {}", userNo); // 로그 추가
    }

}
