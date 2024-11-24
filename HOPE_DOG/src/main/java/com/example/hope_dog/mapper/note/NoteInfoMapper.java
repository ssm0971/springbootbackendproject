package com.example.hope_dog.mapper.note;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoteInfoMapper {

//  안읽은 쪽지 수 확인
    Long getUnreadCount(Long userNo);

//  안읽은 쪽지 일괄 읽음 처리
    void markAllAsRead(Long userNo);

}
