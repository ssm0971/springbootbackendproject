package com.example.hope_dog.api;

import com.example.hope_dog.dto.donation.DonaCommentListDTO;
import com.example.hope_dog.dto.donation.DonaCommentReportDTO;
import com.example.hope_dog.dto.donation.DonaCommentUpdateDTO;
import com.example.hope_dog.dto.donation.DonaCommentWriteDTO;
import com.example.hope_dog.dto.page.Criteria;
import com.example.hope_dog.dto.page.Slice;
import com.example.hope_dog.service.donation.DonaCommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController // 이 클래스가 REST API 컨트롤러임을 나타냄. 클라이언트의 HTTP 요청을 처리.
@RequiredArgsConstructor // Lombok 어노테이션으로, final로 선언된 필드들에 대해 자동으로 생성자를 생성
@Slf4j // Lombok 어노테이션으로, 로그를 기록할 수 있는 로깅 기능 추가
public class CommentApi {

    private final DonaCommentService donaCommentService; // 서비스 계층과 연동하여 댓글 관련 비즈니스 로직을 처리

    // 댓글 작성 API
    @PostMapping("/v1/donas/{donaNo}/comment")
    public void commentWrite(@RequestBody DonaCommentWriteDTO donaCommentWriteDTO,
                             @PathVariable("donaNo") Long donaNo) {
        // 전달된 댓글 데이터를 받는 DTO 객체와 기부 번호를 파라미터로 받음
        log.info("donaCommentWriteDTO = " + donaCommentWriteDTO + ", donaNo = " + donaNo);

        // 서비스 클래스의 registerComment 메서드를 호출하여 댓글 등록 처리
        donaCommentService.registerComment(donaCommentWriteDTO);
    }

    // 기부에 달린 댓글 목록을 조회하는 API
    @GetMapping("/v1/donas/{donaNo}/comments")
    public List<DonaCommentListDTO> commentList(@PathVariable("donaNo") Long donaNo) {
        // donaNo를 이용해 댓글 목록을 조회하여 반환
        return donaCommentService.findList(donaNo);
    }

    // 댓글 목록을 페이지 단위로 가져오는 API (페이징 처리)
    @GetMapping("/v2/donas/{donaNo}/comments")
    public Slice<DonaCommentListDTO> replySlice(@PathVariable("donaNo") Long donaNo,
                                                @RequestParam("page") int page) {
        log.info("/v2/donas/{donaNo}/comments donaNo번호 확인 : " + donaNo);

        // 댓글을 페이징 처리하여 가져오는 서비스 메서드 호출
        Slice<DonaCommentListDTO> slice = donaCommentService.findSlice(new Criteria(page, 50), donaNo);

        // 페이징된 댓글 데이터를 반환
        return slice;
    }

    // 댓글 수정 API
    @PatchMapping("/v1/comments/{donaCommentNo}")
    public void modifyComment(@RequestBody DonaCommentUpdateDTO donaCommentUpdateDTO,
                              @PathVariable("donaCommentNo") Long donaCommentNo) {
        // 수정할 댓글 번호를 DTO에 설정
        donaCommentUpdateDTO.setDonaCommentNo(donaCommentNo);

        // 서비스에서 댓글 수정 처리
        donaCommentService.modifyComment(donaCommentUpdateDTO);
    }

    // 댓글 삭제 API
    @DeleteMapping("/v1/comments/{donaCommentNo}")
    public void removeComment(@PathVariable("donaCommentNo") Long donaCommentNo) {
        // 댓글 번호를 받아 댓글 삭제 서비스 호출
        donaCommentService.removeComment(donaCommentNo);
    }

    // 댓글 신고 API
    @PostMapping("/v1/comments/report")
    public ResponseEntity<String> reportComment(@RequestBody DonaCommentReportDTO donaCommentReportDTO) {
        // 신고할 댓글의 정보를 포함하는 DTO 객체를 파라미터로 받음
        log.info("신고 데이터 수신: " + donaCommentReportDTO);

        // 신고 처리 서비스 호출
        donaCommentService.donaCommentReport(donaCommentReportDTO);

        // 신고가 정상적으로 처리되었음을 응답
        return ResponseEntity.ok("신고가 정상적으로 처리되었습니다.");
    }
}


