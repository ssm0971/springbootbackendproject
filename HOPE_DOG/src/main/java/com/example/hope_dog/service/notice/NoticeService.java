package com.example.hope_dog.service.notice;

import com.example.hope_dog.dto.notice.NoticeViewDTO;
import com.example.hope_dog.dto.notice.NoticeListDTO;
import com.example.hope_dog.dto.page.Criteria;
import com.example.hope_dog.mapper.notice.NoticeMapper;
import lombok.RequiredArgsConstructor;
//import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service // 비즈니스 로직 처리
@Transactional // 트랜잭션을 지원
@RequiredArgsConstructor // final 필드에 대한 생성자 자동 생성
public class NoticeService {
    private final NoticeMapper noticeMapper;

    //List
//    public List<NoticeListDTO> getNoticeList() {
//        return noticeMapper.noticeList();
//    }
    public List<NoticeListDTO> findAll() {
        return noticeMapper.selectAll();
    }

    public int findTotal(){
        return noticeMapper.selectTotal();
    }

    public List<NoticeListDTO> findAllPage(Criteria criteria) {
        return noticeMapper.selectAllPage(criteria);
    }

    //View
    public List<NoticeViewDTO> getNoticeViewList(Long noticeNo) {
        return noticeMapper.noticeView(noticeNo);
    }
//    private final FileMapper fileMapper;
//    private final FileService fileService;

//    @Value("C:/upload")
//    private String fileDir;
//
//    public void registerBoardWithFiles(BoardWriteDTO boardWriteDTO, List<MultipartFile> files) throws IOException {
//        boardMapper.insertBoard(boardWriteDTO);
//        Long boardId = boardWriteDTO.getBoardId();
//
//    }
//
//    // 단일 파일 저장하고 파일 정보를 fileDTO 객체로 반환하는 메소드
//    public FileDTO saveFile(MultipartFile file) throws IOException{
//        // 파일명, uuid 생성
//        String originalFilename = file.getOriginalFilename();
//        UUID uuid = UUID.randomUUID();
//        String systemName = uuid.toString() + "_" + originalFilename;
//
//        // 파일 업로드 경로 설정 및 디렉터리 생성
//        File uploadPath = new File(fileDir, getUploadPath());
//
//        if(!uploadPath.exists()){
//            uploadPath.mkdirs();
//        }
//
//        //파일 저장
//        File uploadFile = new File(uploadPath, systemName);
//        file.transferTo(uploadFile); //uploadFile : 최종적으로 저장될 파일 객체
//
//        //이미지 파일인 경우 썸네일 생성
//        String contentType = Files.probeContentType(uploadFile.toPath());
//        // 파일의 MIME 타입을 확인
//        try {
//            if(contentType != null && contentType.startsWith("image")){
//                BufferedImage bufferedImage = ImageIO.read(uploadFile);
//                if(bufferedImage != null){
//                    //Thumbnails 라이브러리 사용하여 이미지 썸네일 생성하고 th_ 접두사를 붙여 저장
//                    Thumbnails.of(bufferedImage).size(300, 200).outputFormat("jpg")
//                            .toFile(new File(uploadPath, "th_" + systemName + ".jpg"));
//                }
//            }else{
//                throw new IOException("buffered image is null");
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        // FileDTO 객체 생성 및 반환
//        FileDTO fileDTO = new FileDTO();
//        fileDTO.setUuid(uuid.toString());
//        fileDTO.setName(originalFilename);
//        fileDTO.setUploadPath(getUploadPath());
//
//        return fileDTO;
//    }
//
//    // 여러개의 파일을 처리하여 각각 저장하는 메소드
//    private void saveFiles(List<MultipartFile> files, Long boardId) throws IOException{
//        for(MultipartFile file : files){
//            if(!file.isEmpty()){
//                FileDTO fileDTO = saveFile(file);
//                fileDTO.setBoardId(boardId);
//                fileMapper.insertFile(fileDTO);
//            }
//        }
//    }
//
//    // 현재날짜를 기반으로 파일이 저장될 경로를 반환
//    private String getUploadPath(){
//        return new SimpleDateFormat("yyyy/MM/dd").format(new Date());
//    }

//    public NoticeViewDTO findById(Long noticeNo){
//        return noticeMapper.selectById(noticeNo).orElseThrow(() -> new IllegalStateException("유효하지 않은 게시물 번호"));
//    }
//
//    public List<NoticeListDTO> findAll() {
//        return noticeMapper.selectAll();
//    }
//
//    public int findTotal(){
//        return noticeMapper.selectTotal();
//    }
//
//    public List<NoticeListDTO> findAllPage(Criteria criteria){
//        return noticeMapper.selectAllPage(criteria);
//    }



}
