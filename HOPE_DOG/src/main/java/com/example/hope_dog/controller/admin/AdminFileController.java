package com.example.hope_dog.controller.admin;

import com.example.hope_dog.dto.admin.AdminFileDTO;
import com.example.hope_dog.service.admin.AdminFileService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 파일 다운로드를 처리하는 관리자용 컨트롤러
 */
@RestController // RESTful API를 구현하는 클래스임을 나타냄
@RequestMapping("/file") // "/file" 경로로 들어오는 요청을 처리하는 컨트롤러
public class AdminFileController {

    private final AdminFileService fileService; // 파일 관련 서비스를 처리하는 Service 클래스

    /**
     * 생성자에서 AdminFileService를 주입받음
     * @param fileService 파일 관련 비즈니스 로직을 처리하는 서비스
     */
    public AdminFileController(AdminFileService fileService) {
        this.fileService = fileService;
    }

    /**
     * 파일 다운로드 요청을 처리하는 메서드
     * @param fileNo 다운로드할 파일의 번호
     * @return 파일 다운로드를 위한 ResponseEntity
     */
    @GetMapping("/{fileNo}") // "/file/{fileNo}" 경로로 GET 요청을 처리
    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileNo) {

        // 파일 정보를 조회 (DB에서 파일 정보를 가져옴)
        AdminFileDTO fileDTO = fileService.selectFileByNo(fileNo);

        // 파일 경로 생성: 파일 저장 위치와 파일명을 결합하여 경로를 찾음
        Path filePath = Paths.get("C:/upload/notice/" + fileDTO.getStoredFileName());
        Resource resource = new FileSystemResource(filePath); // 파일을 시스템 리소스로 로딩

        // 파일이 존재하지 않으면 404 Not Found 응답 반환
        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        // 파일명 인코딩 처리 (URL에 적합한 형식으로 파일명을 변환)
        String encodedFileName = URLEncoder.encode(fileDTO.getFileName(), StandardCharsets.UTF_8).replace("+", "%20");

        // Content-Disposition 헤더 설정 (다운로드 시 파일명 표시)
        String contentDisposition = "attachment; filename=\"" + encodedFileName + "\"";

        // 파일 다운로드를 위한 응답 생성
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM) // 콘텐츠 타입을 "application/octet-stream"으로 설정 (파일 다운로드)
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition) // 다운로드할 파일명 설정
                .body(resource); // 파일 리소스를 응답 바디에 포함하여 반환
    }
}
