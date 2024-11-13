package com.example.hope_dog.service.donation;

import com.example.hope_dog.dto.commu.CommuReportDTO;
import com.example.hope_dog.dto.donation.DonaCommentListDTO;
import com.example.hope_dog.dto.donation.DonaCommentReportDTO;
import com.example.hope_dog.dto.donation.DonaCommentUpdateDTO;
import com.example.hope_dog.dto.donation.DonaCommentWriteDTO;
import com.example.hope_dog.dto.page.Criteria;
import com.example.hope_dog.dto.page.Slice;
import com.example.hope_dog.mapper.donation.DonaCommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DonaCommentService {
    private final DonaCommentMapper donaCommentMapper;

    public void registerComment(DonaCommentWriteDTO donaCommentWriteDTO){
//        Long memberNo = donaCommentWriteDTO.getMemberNo();
        if(donaCommentWriteDTO.getDonaCommentWriter() == null) {
            donaCommentWriteDTO.setDonaCommentWriter(donaCommentWriteDTO.getMemberNo());
        }
        donaCommentMapper.insertComment(donaCommentWriteDTO);
    }

    public List<DonaCommentListDTO> findList(Long donaNo){
        return donaCommentMapper.selectCommentList(donaNo);
    }

    public void modifyComment(DonaCommentUpdateDTO donaCommentUpdateDTO){
        donaCommentMapper.updateComment(donaCommentUpdateDTO);
    }

    public void removeComment(Long donaCommentNo){
        donaCommentMapper.deleteComment(donaCommentNo);
    }


    public Slice<DonaCommentListDTO> findSlice(Criteria criteria, Long donaNo){
        List<DonaCommentListDTO> commentList = donaCommentMapper.selectCommentSlice(criteria, donaNo);

        boolean hasNext = commentList.size() > criteria.getAmount();

        if(hasNext){
            commentList.remove(criteria.getAmount());
        }

        return new Slice<DonaCommentListDTO>(hasNext, commentList);
    }

    //댓글 신고
//    public void commuCommentReport(CommuReportDTO commuReportDTO){
//        commuMapper.commuCommentReport(commuReportDTO);
//    }

    public void donaCommentReport(DonaCommentReportDTO donaCommentReportDTO){
        donaCommentMapper.insertCommentReport(donaCommentReportDTO);
    }
}
