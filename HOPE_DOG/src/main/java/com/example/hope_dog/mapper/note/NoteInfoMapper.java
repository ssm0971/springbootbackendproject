package com.example.hope_dog.mapper.note;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoteInfoMapper {

//  안읽은 쪽지 수 확인
    Long getUnreadCount(Long userNo);

    void markAllAsRead(Long userNo);

}
