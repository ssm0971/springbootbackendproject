package com.example.hope_dog.service.centermypage;

import com.example.hope_dog.dto.centermypage.notebox.NoteboxReceiveListDTO;
import com.example.hope_dog.dto.centermypage.notebox.NoteboxSendListDTO;
import com.example.hope_dog.mapper.centermypage.notebox.NoteBoxMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteBoxService {

    @Autowired
    private NoteBoxMapper noteBoxMapper;

//    보낸쪽지목록
    public List<NoteboxSendListDTO> getSendList(Long centerMemberNo) {
        return noteBoxMapper.SendList(centerMemberNo);
    }

//    받은쪽지목록
    public List<NoteboxReceiveListDTO> getReceiveList(Long centerMemberNo) {
        return noteBoxMapper.ReceiveList(centerMemberNo);
    }

}
