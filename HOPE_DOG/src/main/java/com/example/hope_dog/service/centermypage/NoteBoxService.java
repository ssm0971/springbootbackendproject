package com.example.hope_dog.service.centermypage;

import com.example.hope_dog.dto.centermypage.notebox.NoteboxSendListDTO;
import com.example.hope_dog.mapper.centermypage.notebox.NoteBoxMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteBoxService {

    @Autowired
    private NoteBoxMapper noteBoxMapper;

    public List<NoteboxSendListDTO> getSendList(Long centerMemberNo) {
        return noteBoxMapper.selectSendList(centerMemberNo);
    }
}
