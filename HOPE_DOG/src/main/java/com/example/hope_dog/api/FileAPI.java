package com.example.hope_dog.api;

import com.example.hope_dog.service.admin.AdminFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

@RestController
@RequiredArgsConstructor
public class FileAPI {
    public final AdminFileService fileService;

    @Value("C:/upload/")
    private String fileDir;

    //HTTP 요청 쿼리 파라미터에서 fileName 값을 받아와 fileName 변수에 저장
    @GetMapping("/v1/files")
    public byte[] display(@RequestParam("from") String from, @RequestParam("fileName") String fileName, @RequestParam("filePath") String filePath, @RequestParam("fileUuid") String fileUuid) throws IOException {
        //지정된 파일 저장 경로와 요청받은 파일 이름을 사용하여 File 객체 생성
        File file = new File(fileDir + from + "/" + filePath, fileUuid + "_" + fileName);
        //FileCopyUtils를 사용하여 파일을 바이트배열로 복사 및 반환
        return FileCopyUtils.copyToByteArray(file);
    }

    //HTTP GET 요청이 "/download" 엔드포인트로 들어올 때 이 메소드가 호출되도록 설정
    @GetMapping("/download")
    //HttpServletResponse와 동일하게 ResponseEntity객체는 응답을 나타내는 객체이다
    //스프링에서 지원하는 응답객체이며 기존의 응답객체보다 간편하게 설정할 수 있다는 장점이 있다
    public ResponseEntity<Resource> download(@RequestParam("from") String from, @RequestParam("fileName") String fileName, @RequestParam("filePath") String filePath, @RequestParam("fileUuid") String fileUuid) throws IOException {
        // fileName 문자열 매개변수를 받고 파일을 다운로드 하는 기능을 수행하는 메소드
        // ResponseEntity<Resource> : 타입의 반환값은 HTTP 응답 데이터를 구성하는데 사용됨

        //Resouse 객체는 말 그대로 자원을 나타내는 객체로 스프링에서 지원하는 타입이다
        //이미지 파일이라는 리소스를 다운로드 처리하기 위해 사용하고 있으며 File 객체보다 많은 종류의 리소스를 다룰수 있고
        //스프링과의 호환성이 좋다
        //Resource는 인터페이스이므로 객체화를 할 때는 자식 클래스를 사용한다
        Resource resource = new FileSystemResource(fileDir + from + "/"+ filePath + "/" + fileUuid + "_" + fileName); //Resource의 구현체, fileDir + fileName을 통해 전체 파일 경로 구성하여 Resource 객체 생성

        HttpHeaders headers = new HttpHeaders();
        //HttpHeaders 객체를 생성하여 HTTP 응답 헤더 설정

        String name = resource.getFilename();
        //Resource 객체에서 파일 이름 얻어옴

//        name = name.substring(name.indexOf("_") + 1);
        //파일 이름에서 UUID 부분 제거하고 실제 파일 이름만 남김

        //Content-Disposition 헤더로 설정하여 클라이언트 브라우저가 첨부파일이라는 것을 알게 함
        headers.add("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
        //파일 이름을 UTF-8 인코딩으로 브라우저가 올바르게 파일이름 인식하도록 설정
        //attachment는 파일을 다운로드하도록 하며 filename="" 부분은 다운로드 할 파일의 이름을 설정

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
        //ResponseEntity 객체 생성하여 HTTP 응답 구성함
        //응답 본문으로는  Resource 객체, 응답 헤더로는 HttpHeaders 객체, HTTP 상태코드는 200 OK
    }

    @DeleteMapping("/deleteFileByFileNo/{fileNo}")
    public ResponseEntity<String> deleteFileByFileNo(@PathVariable("fileNo") Long fileNo) {
        try {
            // 파일 삭제 서비스 호출 (void 반환)
            fileService.deleteFileByFileNo(fileNo);
            return ResponseEntity.ok("파일이 삭제되었습니다.");
        } catch (EmptyResultDataAccessException e) {
            // 파일이 없는 경우 처리 (데이터베이스에 없을 경우)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("파일을 찾을 수 없습니다.");
        } catch (Exception e) {
            // 그 외 예외 처리
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 삭제 중 오류가 발생했습니다.");
        }
    }


    @DeleteMapping("/deleteFileByNoticeNo/{noticeNo}")
    public ResponseEntity<String> deleteFileByNoticeNo(@PathVariable("noticeNo") Long noticeNo) {
        try {
            // 파일 삭제 서비스 호출 (void 반환)
            fileService.deleteFileByFileNo(noticeNo);
            return ResponseEntity.ok("파일이 삭제되었습니다.");
        } catch (EmptyResultDataAccessException e) {
            // 파일이 없는 경우 처리 (데이터베이스에 없을 경우)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("파일을 찾을 수 없습니다.");
        } catch (Exception e) {
            // 그 외 예외 처리
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 삭제 중 오류가 발생했습니다.");
        }
    }
}
