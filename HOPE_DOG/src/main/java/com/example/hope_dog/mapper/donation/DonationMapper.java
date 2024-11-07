package com.example.hope_dog.mapper.donation;

import com.example.hope_dog.dto.donation.*;
import com.example.hope_dog.dto.page.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface DonationMapper {

    // 기부 글 목록
//    List<DonationListDTO> donationList();
    Optional<DonationViewDTO> selectById(Long donaNo);

    //페이지네이션
    List<DonationListDTO> selectAll();

    int selectTotal();

    List<DonationListDTO> selectAllPage(Criteria criteria);

    // 기부 글 상세
    List<DonationViewDTO> donationView(Long donaNo);

    // 기부 글 작성
    void donationWrite(DonationWriteDTO donationWriteDTO);

    // 기부 글 삭제
    void donationDelete(Long donaNo);

    // 기부 글 수정
    void donationUpdate(DonationWriteDTO donationWriteDTO);
//    void updateBoard(BoardUpdateDTO boardUpdateDTO);

}
