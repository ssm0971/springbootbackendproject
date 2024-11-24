package com.example.hope_dog.service.admin;

import com.example.hope_dog.dto.admin.*;
import com.example.hope_dog.mapper.admin.AdminFileMapper;
import com.example.hope_dog.mapper.admin.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminService {
    private final AdminMapper adminMapper;
    private final AdminFileMapper adminFileMapper;

    @Value("C:/upload/notice")
    private String fileDir;

//    public Long selectId(String adminId, String adminPw) {
//        return adminMapper.selectId(adminId, adminPw).orElseThrow(() -> new IllegalStateException("존재하지 않는 관리자 정보"));
//    }

    public AdminSessionDTO findLoginInfo(String adminId, String adminPw) {
        return adminMapper.selectLoginInfo(adminId, adminPw).orElse(null);
    }
    public List<AdminMemberDTO> selectMemberList(){
        return adminMapper.selectMemberList();
    }

    public List<AdminMemberDTO> searchMemberByKeyword(String keyword){ return adminMapper.searchMemberByKeyword(keyword); }

    public AdminMemberDTO selectMemberByNo(Long memberNo) {
        return adminMapper.selectMemberByNo(memberNo);
    }

    public void deleteMember(List<Long> memberNoList){ adminMapper.deleteMembers(memberNoList); }

    public void deleteCenterMember(List<Long> memberNoList){ adminMapper.deleteCenterMembers(memberNoList); }

    public List<AdminCenterMemberDTO> selectCenterMemberList(){
        return adminMapper.selectCenterMemberList();
    }

    public AdminCenterMemberDTO selectNotPassedCenterMemberByNo(Long centerMemberNo) { return adminMapper.selectNotPassedCenterMemberByNo(centerMemberNo); }

    public List<AdminCenterMemberDTO> selectPassedCenterMemberList(){ return adminMapper.selectPassedCenterMemberList(); }

    public List<AdminCenterMemberDTO> searchPassedCenterMemberByKeyword(String keyword){ return adminMapper.searchPassedCenterMemberByKeyword(keyword); }

    public AdminCenterMemberDTO selectPassedCenterMemberByNo(Long centerMemberNo) { return adminMapper.selectPassedCenterMemberByNo(centerMemberNo); }

    public List<AdminReportDTO> selectReportList(){
        return adminMapper.selectReportList();
    }

    public List<AdminReportDTO> searchReportByKeyword(String keyword){ return adminMapper.searchReportByKeyword(keyword); }

    public List<AdminPostDTO> selectPostList(){
        return adminMapper.selectPostList();
    }

    public List<AdminPostDTO> searchPostByKeyword(String keyword){ return adminMapper.searchPostByKeyword(keyword); }

    public void deletePost(AdminPostDTO item){adminMapper.deletePost((AdminPostDTO) item);}

    public void deleteComment(AdminCommentDTO item){adminMapper.deleteComment((AdminCommentDTO) item);}

    public AdminPostDTO selectPostDetail(String postType, Long postNo){ return adminMapper.selectPostDetail(postType, postNo); }

    public void deletePostDetail(AdminPostDTO item){adminMapper.deletePostDetail((AdminPostDTO) item);}

    public void deleteCommentDetail(AdminCommentDTO item){adminMapper.deleteCommentDetail((AdminCommentDTO) item);}

    public List<AdminCommentDTO> selectCommentList(){ return adminMapper.selectCommentList(); }

    public List<AdminCommentDTO> searchCommentByKeyword(String keyword){ return adminMapper.searchCommentByKeyword(keyword); }

    public List<AdminCommentDTO> selectCommentListByPostNo(String postType, Long postNo){ return adminMapper.selectCommentListByPostNo(postType, postNo); }

    public List<AdminNoticeDTO> selectNoticeList(){
        return adminMapper.selectNoticeList();
    }

    public AdminNoticeDTO selectNoticeDetail(Long noticeNo){ return adminMapper.selectNoticeDetail(noticeNo); }

    public List<AdminNoticeDTO> searchNoticeByKeyword(String keyword){ return adminMapper.searchNoticeByKeyword(keyword); }

    public List<AdminAdoptRequestDTO> selectAdoptRequestList(){
        return adminMapper.selectAdoptRequestList();
    }

    public List<AdminAdoptRequestDTO> searchAdoptRequestByKeyword(String keyword){ return adminMapper.searchAdoptRequestByKeyword(keyword); }

    public AdminAdoptRequestDTO selectAdoptRequestDetail(Long adoptRequestNo){ return adminMapper.selectAdoptRequestDetail(adoptRequestNo); }

    public List<AdminProtectRequestDTO> selectProtectRequestList(){
        return adminMapper.selectProtectRequestList();
    }

    public List<AdminProtectRequestDTO> searchProtectRequestByKeyword(String keyword){ return adminMapper.searchProtectRequestByKeyword(keyword); }

    public AdminProtectRequestDTO selectProtectRequestDetail(Long protectRequestNo){return adminMapper.selectProtectRequestDetail(protectRequestNo);}

    public List<AdminVolunRequestDTO> selectVolunRequestList(){
        return adminMapper.selectVolunRequestList();
    }

    public List<AdminVolunRequestDTO> searchVolunRequestList(String keyword){ return adminMapper.searchVolunRequestByKeyword(keyword); }

    public AdminVolunRequestDTO selectVolunRequestDetail(Long volunNo){ return adminMapper.selectVolunRequestDetail(volunNo); }

    public List<AdminNoteSendDTO> selectNoteSendList(){
        return adminMapper.selectNoteSendList();
    }

    public List<AdminNoteReceiveDTO> selectNoteReceiveList(){
        return adminMapper.selectNoteReceiveList();
    }

    public AdminNoteReceiveDTO selectNoteReceiveDetail(Long noteboxReceiveNo){ return adminMapper.selectNoteReceiveDetail(noteboxReceiveNo);}

    public AdminNoteSendDTO selectNoteSendDetail(Long noteboxSendNo){ return adminMapper.selectNoteSendDetail(noteboxSendNo);}

    public void insertNoteWriteReceive(String title, String content, String receiver){adminMapper.insertNoteWriteReceive(title, content, receiver); }

    public void insertNoteWriteSend(String title, String content, String receiver){adminMapper.insertNoteWriteSend(title, content, receiver); }

    public List<AdminNoteReceiveDTO> searchNoteInByKeyword(String keyword){ return adminMapper.searchNoteInByKeyword(keyword);}

    public List<AdminNoteSendDTO> searchNoteOutByKeyword(String keyword){ return adminMapper.searchNoteOutByKeyword(keyword); }

    public void deleteNoteIn(List<Long> noteNoList){adminMapper.deleteNoteIn(noteNoList);}

    public void deleteNoteOut(List<Long> noteNoList){adminMapper.deleteNoteOut(noteNoList);}

    public void approveCenterMember(List<Long> itemList){adminMapper.approveCenterMember(itemList);}

    public String findCenterMemberNameByNo(Long centerMemberNo){ return adminMapper.findCenterMemberNameByNo(centerMemberNo); }

    public void deleteNotice(List<Long> noticeNoList){
        for(Long noticeNo : noticeNoList){
            adminFileMapper.deleteFileByNoticeNo(noticeNo);
        }
        adminMapper.deleteNotice(noticeNoList);
    }

    public void insertNotice(AdminNoticeDTO notice, List<MultipartFile> files) throws IOException {
        adminMapper.insertNotice(notice);

        for(MultipartFile file : files){
            if(file.isEmpty()){
                break;
            }

            AdminFileDTO adminFileDTO = saveFile(file);
            adminFileMapper.insertFile(adminFileDTO);
        }
    }

    public AdminFileDTO saveFile(MultipartFile file) throws IOException {
        // 파일명, uuid 생성
        String originalFilename = file.getOriginalFilename();
        UUID uuid = UUID.randomUUID();
        String systemName = uuid.toString() + "_" + originalFilename;

        // 파일 업로드 경로 설정 및 디렉터리 생성
        String uploadPath = fileDir + "/" + getUploadPath();  // fileDir과 getUploadPath()를 결합
        File uploadDir = new File(uploadPath);

        // 경로가 존재하지 않는다면(폴더가 없다면)
        if (!uploadDir.exists()) {
            // 경로에 필요한 모든 폴더를 생성한다
            uploadDir.mkdirs();
        }

        // 파일 저장
        File uploadFile = new File(uploadDir, systemName);
        file.transferTo(uploadFile); // 최종적으로 저장될 파일 객체

        // 이미지 파일인 경우 썸네일 생성
        String contentType = Files.probeContentType(uploadFile.toPath());
        try {
            if (contentType != null && contentType.startsWith("image")) {
                BufferedImage bufferedImage = ImageIO.read(uploadFile);
                if (bufferedImage != null) {
                    // Thumbnails 라이브러리 사용하여 이미지 썸네일 생성하고 th_ 접두사를 붙여 저장
                    Thumbnails.of(bufferedImage).size(300, 200).outputFormat("jpg")
                            .toFile(new File(uploadDir, "th_" + systemName + ".jpg"));
                }
            } else {
                // 이미지가 아닌 파일일 경우 처리 (이미지 파일이 아니면 IOException을 던짐)
                throw new IOException("The file is not an image.");
            }
        } catch (IOException e) {
            // 이미지는 아니지만 다른 예외가 발생한 경우 처리
            System.err.println("Error while processing image file: " + e.getMessage());
            throw new RuntimeException("Error while processing the file.", e);
        }

        // FileDTO 객체 생성 및 반환
        AdminFileDTO fileDTO = new AdminFileDTO();
        fileDTO.setNoticeNo(selectCurNoticeNo());
        fileDTO.setFileUuid(uuid.toString());
        fileDTO.setFileName(originalFilename);
        fileDTO.setFilePath(getUploadPath());  // 날짜 형식 경로만 저장 (상대 경로)

        return fileDTO;
    }


    //여러개의 파일을 처리하여 각각 저장하는 메소드
    public void saveFiles(List<MultipartFile> files) throws IOException{
        for(MultipartFile file : files){
            if(!file.isEmpty()){
                AdminFileDTO fileDTO = saveFile(file);
                fileDTO.setNoticeNo(selectCurNoticeNo());
                adminFileMapper.insertFile(fileDTO);
            }
        }
    }

    private String getUploadPath(){
        LocalDate date = LocalDate.now();
        return date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    private void deleteFileByFileNo(Long fileNo){
        // 데이터베이스에서 파일 정보 삭제 (존재하지 않으면 예외 발생 가능)
        adminFileMapper.deleteFileByFileNo(fileNo);

        // 파일 시스템에서 파일 삭제 로직
        Optional<AdminFileDTO> fileOptional = Optional.ofNullable(adminFileMapper.selectFileByNo(fileNo));
        if (fileOptional.isPresent()) {
            AdminFileDTO file = fileOptional.get();
            File fileToDelete = new File(file.getFilePath());

            if (fileToDelete.exists()) {
                fileToDelete.delete();
            }
        }
    }

    public void deleteFileByNoticeNo(Long noticeNo){
        // 데이터베이스에서 파일 정보 삭제 (존재하지 않으면 예외 발생 가능)
        adminFileMapper.deleteFileByNoticeNo(noticeNo);

        // 파일 시스템에서 파일 삭제 로직
        Optional<AdminFileDTO> fileOptional = Optional.ofNullable(adminFileMapper.selectFileByNo(noticeNo));
        if (fileOptional.isPresent()) {
            AdminFileDTO file = fileOptional.get();
            File fileToDelete = new File(file.getFilePath());

            if (fileToDelete.exists()) {
                fileToDelete.delete();
            }
        }
    }

    public void modifyNotice(AdminNoticeDTO notice){adminMapper.modifyNotice(notice);}

    public Long selectCurNoticeNo(){return adminMapper.selectCurNoticeNo();}
}