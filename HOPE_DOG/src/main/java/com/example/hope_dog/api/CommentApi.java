package com.example.hope_dog.api;

import com.example.hope_dog.dto.donation.DonaCommentListDTO;
import com.example.hope_dog.dto.donation.DonaCommentUpdateDTO;
import com.example.hope_dog.dto.donation.DonaCommentWriteDTO;
import com.example.hope_dog.dto.page.Criteria;
import com.example.hope_dog.dto.page.Slice;
import com.example.hope_dog.service.donation.DonaCommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CommentApi {

    private final DonaCommentService donaCommentService;

    @PostMapping("/v1/donas/{donaNo}/comment")
    public void commentWrite(@RequestBody DonaCommentWriteDTO donaCommentWriteDTO,
                             @PathVariable("donaNo") Long donaNo){
        // donaCommentWriteDTO.setDonaNo(donaNo);
        // donaCommentWriteDTO.setMemberNo(centerMemberNo);

        log.info("donaCommentWriteDTO = " + donaCommentWriteDTO + ", donaNo = " + donaNo);

        donaCommentService.registerComment(donaCommentWriteDTO);

    }

    @GetMapping("/v1/donas/{donaNo}/comments")
    public List<DonaCommentListDTO> commentList(@PathVariable("donaNo") Long donaNo){
        return donaCommentService.findList(donaNo);
    }

    @GetMapping("/v2/donas/{donaNo}/comments")
    public Slice<DonaCommentListDTO> replySlice(@PathVariable("donaNo") Long donaNo,
                                                @RequestParam("page") int page){
        log.info("/v2/donas/{donaNo}/comments donaNo번호 확인 : " + donaNo);
        Slice<DonaCommentListDTO> slice = donaCommentService.findSlice(new Criteria(page, 50), donaNo);
        return slice;
    }

    @PatchMapping("/v1/comments/{donaCommentNo}")
    public void modifyComment(@RequestBody DonaCommentUpdateDTO donaCommentUpdateDTO,
                              @PathVariable("donaCommentNo") Long donaCommentNo){

        donaCommentUpdateDTO.setDonaCommentNo(donaCommentNo);
        donaCommentService.modifyComment(donaCommentUpdateDTO);
    }

    @DeleteMapping("/v1/comments/{donaCommentNo}")
    public void removeComment(@PathVariable("donaCommentNo") Long donaCommentNo){
        donaCommentService.removeComment(donaCommentNo);
    }
}

