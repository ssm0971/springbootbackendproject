package com.example.hope_dog.mapper.centermypage;

import com.example.hope_dog.dto.centermypage.CenterProfileDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CenterMypageMapperTest {

    @Autowired
    private CenterMypageMapper centerMypageMapper;

    @Test
    void testCenterProfile() {
        // 테스트할 centerMemberNo를 설정합니다.
        Long testMemberNo = 12L; // 실제 데이터베이스에 존재하는 값으로 바꿔주세요.

        // centerProfile 메서드를 호출하여 결과를 가져옵니다.
        CenterProfileDTO profile = centerMypageMapper.centerProfile(testMemberNo);

        // 결과가 null이 아닌지 검증합니다.
        assertNotNull(profile);

        // 추가로 반환된 값에 대한 검증을 추가합니다.
        assertEquals(testMemberNo, profile.getCenterMemberNo());
        // 여기에 추가적인 필드 검증을 할 수 있습니다.
        // assertEquals("expectedName", profile.getCenterMemberName());
        // assertEquals("expectedEmail", profile.getCenterMemberEmail());
        // 등등
    }
}
