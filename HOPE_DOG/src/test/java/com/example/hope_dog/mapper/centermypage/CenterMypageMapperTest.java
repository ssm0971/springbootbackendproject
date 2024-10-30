package com.example.hope_dog.mapper.centermypage;

import com.example.hope_dog.dto.centermypage.CenterUpdateProfileDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CenterMypageMapperTest {

//    @Autowired
//    private CenterMypageMapper centerMypageMapper;
//
//    @Test
//    void testCheckPasswordMatch() {
//        // Given
//        Long centerMemberNo = 12L; // 실제 DB에 존재하는 회원번호로 테스트
//        String correctPassword = "centerpass456"; // 실제 DB에 존재하는 비밀번호
//        String wrongPassword = "wrongPassword";
//
//        // When
//        boolean correctMatch = centerMypageMapper.checkPasswordMatch(centerMemberNo, correctPassword);
//        boolean incorrectMatch = centerMypageMapper.checkPasswordMatch(centerMemberNo, wrongPassword);
//
//        // Then
//        assertTrue(correctMatch, "Correct password should match");
//        assertFalse(incorrectMatch, "Wrong password should not match");
//    }
//
//    @Test
//    void testUpdateCenterProfile() {
//        // Given
//        CenterUpdateProfileDTO dto = new CenterUpdateProfileDTO();
//        dto.setCenterMemberNo(12L); // 실제 DB에 존재하는 회원번호로 테스트
//        dto.setCenterMemberName("New Name");
//        dto.setCenterMemberEmail("newemail@example.com");
//        dto.setCenterMemberPhoneNumber("123-456-7890");
//        dto.setCenterMemberAddress("New Address");
//        dto.setCenterMemberDetailAddress("New Detail Address");
//        dto.setTblCenterMemberAgree("Y");
//        dto.setCenterMemberPassword("existingPassword"); // 실제 DB에 존재하는 비밀번호
//
//        // When
//        int rowsAffected = centerMypageMapper.updateCenterProfile(dto);
//
//        // Then
//        assertEquals(1, rowsAffected, "Profile should be updated for one row");
//    }
}
