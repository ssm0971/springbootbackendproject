package com.example.hope_dog.mapper.donation;

import com.example.hope_dog.dto.donation.DonationListDTO;
import com.example.hope_dog.dto.donation.DonationViewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DonationMapper {


    List<DonationListDTO> donationList();

    List<DonationViewDTO> donationView(Long donaNo);

}
