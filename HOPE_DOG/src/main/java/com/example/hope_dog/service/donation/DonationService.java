package com.example.hope_dog.service.donation;

import com.example.hope_dog.dto.donation.DonationListDTO;
import com.example.hope_dog.dto.donation.DonationUpdateDTO;
import com.example.hope_dog.dto.donation.DonationViewDTO;
import com.example.hope_dog.dto.donation.DonationWriteDTO;
import com.example.hope_dog.dto.page.Criteria;
import com.example.hope_dog.mapper.donation.DonationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@Service // 비즈니스 로직 처리
@Transactional // 트랜잭션을 지원
@RequiredArgsConstructor // final 필드에 대한 생성자 자동 생성
public class DonationService {

    private final DonationMapper donationMapper;

    // List
//    public List<DonationListDTO> getDonationList() {
//        return donationMapper.donationMainList();
//    }

    //페이지네이션 관련 service
    public List<DonationListDTO> findAll() {
        return donationMapper.selectAll();
    }

    public int findTotal(){
        return donationMapper.selectTotal();
    }

    public List<DonationListDTO> findAllPage(Criteria criteria){
        return donationMapper.selectAllPage(criteria);
    }


    // View
    public List<DonationViewDTO> getDonationViewList(Long donaNo) {
        return donationMapper.donationView(donaNo);
    }

    // Write
    public void registerDonation(DonationWriteDTO donationWriteDTO) {
        donationMapper.donationWrite(donationWriteDTO);
    }

    // Delete
//    @Override
//    @Transactional
    public void donationDelete(Long donaNo) {
        donationMapper.donationDelete(donaNo);
    }


    public DonationViewDTO findById(Long donaNo){
        return donationMapper.selectById(donaNo).orElseThrow(() -> new IllegalStateException("유효하지 않은 게시물 번호"));
    }

    // modify
    public void modifyDonation(DonationUpdateDTO donationUpdateDTO) {
        donationMapper.donationUpdate(donationUpdateDTO);
        Long donaNo = donationUpdateDTO.getDonaNo();

        donationMapper.donationDelete(donaNo);

    }
//    public void modifyBoard(BoardUpdateDTO boardUpdateDTO, List<MultipartFile> files) throws IOException {
//        boardMapper.updateBoard(boardUpdateDTO);
//        Long boardId = boardUpdateDTO.getBoardId();
//
//        fileMapper.deleteFile(boardId);
//
//        for(MultipartFile file : files) {
//            if (file.isEmpty()) {
//                break;
//            }
//
//            FileDTO fileDTO = saveFile(file);
//            fileDTO.setBoardId(boardId);
//            fileMapper.insertFile(fileDTO);
//        }
//    }

}
