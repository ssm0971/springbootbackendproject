package com.example.hope_dog.service.donation;

import com.example.hope_dog.dto.donation.DonationListDTO;
import com.example.hope_dog.dto.donation.DonationViewDTO;
import com.example.hope_dog.mapper.donation.DonationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DonationService {
    private final DonationMapper donationMapper;

    public List<DonationListDTO> getDonationList() {

        return donationMapper.donationList();
    }

    public List<DonationViewDTO> getDonationViewList(Long donaNo) {
        return donationMapper.donationView(donaNo);
    }
}
