package com.example.hope_dog.controller.adopt;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class FileController {

    @Autowired
    private final WebApplicationContext context;

    @PostMapping(value = "/image-upload")
    public ResponseEntity<?> imageUpload(@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
        try {
            // 서버에 저장할 경로
            String uploadDirectory = context.getServletContext().getRealPath("/resources/static/file/adopt/adopt");

            // 업로드 된 파일의 이름
            String originalFileName = file.getOriginalFilename();

            // 업로드 된 파일의 확장자
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));

            // 업로드 될 파일의 이름 재설정 (중복 방지를 위해 UUID 사용)
            String uuidFileName = UUID.randomUUID().toString() + fileExtension;

            // 파일을 지정된 경로에 저장
            File destinationFile = new File(uploadDirectory, uuidFileName);
            file.transferTo(destinationFile);

            // 업로드된 파일명 반환
            return ResponseEntity.ok(uuidFileName);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("파일 업로드 실패");
        }
    }
}