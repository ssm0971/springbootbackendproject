package com.example.hope_dog.mapper.centermypage.notebox;

import com.example.hope_dog.dto.centermypage.notebox.NoteboxSendListDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface NoteBoxMapper {
    List<NoteboxSendListDTO> SendList(Long centerMemberNo);
}
