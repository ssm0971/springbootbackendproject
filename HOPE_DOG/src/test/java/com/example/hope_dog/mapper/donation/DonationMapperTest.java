package com.example.hope_dog.mapper.donation;

import com.example.hope_dog.dto.donation.DonationListDTO;
import com.example.hope_dog.dto.donation.DonationViewDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class DonationMapperTest {

    @Mock
    private DonationMapper donationMapper; // DonationMapper를 Mock으로 설정

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDonationList() {
        // Given
        List<DonationListDTO> mockDonationList = new ArrayList<>();
        mockDonationList.add(new DonationListDTO()); // 필요에 따라 DTO 객체를 추가

        // Mocking the Mapper method directly
        when(donationMapper.donationList()).thenReturn(mockDonationList);

        // When
        List<DonationListDTO> donationList = donationMapper.donationList();

        // Then
        assertEquals(mockDonationList, donationList);
    }

    @Test
    void testDonationView() {
        // Given
        Long donaNo = 1L;
        List<DonationViewDTO> mockDonationViewList = new ArrayList<>();
        mockDonationViewList.add(new DonationViewDTO()); // 필요에 따라 DTO 객체를 추가

        // Mocking the Mapper method directly
        when(donationMapper.donationView(donaNo)).thenReturn(mockDonationViewList);

        // When
        List<DonationViewDTO> donationViewList = donationMapper.donationView(donaNo);

        // Then
        assertEquals(mockDonationViewList, donationViewList);
    }
}
