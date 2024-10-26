package com.example.hope_dog.mapper.centermypage.notebox;

import com.example.hope_dog.dto.centermypage.notebox.NoteboxReceiveListDTO;
import com.example.hope_dog.dto.centermypage.notebox.NoteboxSendListDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoteBoxMapper {
    //보낸 쪽지함 목록
    List<NoteboxSendListDTO> SendList(Long centerMemberNo);

    //받은 쪽지함 목록
    List<NoteboxReceiveListDTO> ReceiveList(Long centerMemberNo);


}
