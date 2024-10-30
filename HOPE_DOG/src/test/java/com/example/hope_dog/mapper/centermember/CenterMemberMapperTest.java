package com.example.hope_dog.mapper.centermember;

import com.example.hope_dog.dto.centerMember.CenterFileDTO;
import com.example.hope_dog.dto.centerMember.CenterMemberDTO;
import com.example.hope_dog.dto.centerMember.CenterMemberSessionDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class CenterMemberMapperTest {

    @Autowired
    private CenterMemberMapper centerMemberMapper;

    // 테스트용 센터회원 DTO 생성 메서드
    private CenterMemberDTO createTestCenterMember() {
        CenterMemberDTO memberDTO = new CenterMemberDTO();
        memberDTO.setCenterMemberId("testCenter");
        memberDTO.setCenterMemberPw("test123!");
        memberDTO.setCenterMemberName("테스트센터");
        memberDTO.setCenterMemberEmail("test@center.com");
        memberDTO.setCenterMemberPhoneNumber("010-1234-5678");
        memberDTO.setCenterMemberZipcode("12345");
        memberDTO.setCenterMemberAddress("테스트시 테스트구");
        memberDTO.setCenterMemberDetailAddress("테스트동 123");
        memberDTO.setCenterMemberStatus("N");
        return memberDTO;
    }

    // 테스트용 센터파일 DTO 생성 메서드
    private CenterFileDTO createTestCenterFile(Long centerMemberNo) {
        CenterFileDTO fileDTO = new CenterFileDTO();
        fileDTO.setCenterFileName("test.jpg");
        fileDTO.setCenterFileUuid("test-uuid");
        fileDTO.setCenterFilePath("C:/upload/test.jpg");
        fileDTO.setCenterMemberNo(centerMemberNo);
        return fileDTO;
    }

    @Test
    @DisplayName("센터 회원 등록 테스트")
    void insertCenterMemberTest() {
        // given
        CenterMemberDTO memberDTO = createTestCenterMember();

        // when
        centerMemberMapper.insertCenterMember(memberDTO);

        // then
        assertThat(memberDTO.getCenterMemberNo()).isNotNull();
    }

    @Test
    @DisplayName("센터 파일 등록 테스트")
    void insertCenterFileTest() {
        // given
        CenterMemberDTO memberDTO = createTestCenterMember();
        centerMemberMapper.insertCenterMember(memberDTO);

        CenterFileDTO fileDTO = createTestCenterFile(memberDTO.getCenterMemberNo());

        // when
        centerMemberMapper.insertCenterFile(fileDTO);

        // then
        assertThat(fileDTO.getCenterFileNo()).isNotNull();
    }

    @Test
    @DisplayName("이메일 중복 체크 테스트")
    void checkCenterEmailTest() {
        // given
        CenterMemberDTO memberDTO = createTestCenterMember();
        centerMemberMapper.insertCenterMember(memberDTO);

        // when
        int existingEmailCount = centerMemberMapper.checkCenterEmail("test@center.com");
        int newEmailCount = centerMemberMapper.checkCenterEmail("new@center.com");

        // then
        assertThat(existingEmailCount).isEqualTo(1);
        assertThat(newEmailCount).isEqualTo(0);
    }

    @Test
    @DisplayName("아이디 중복 체크 테스트")
    void checkCenterIdTest() {
        // given
        CenterMemberDTO memberDTO = createTestCenterMember();
        centerMemberMapper.insertCenterMember(memberDTO);

        // when
        int existingIdCount = centerMemberMapper.checkCenterId("testCenter");
        int newIdCount = centerMemberMapper.checkCenterId("newCenter");

        // then
        assertThat(existingIdCount).isEqualTo(1);
        assertThat(newIdCount).isEqualTo(0);
    }

    @Test
    @DisplayName("로그인 정보 조회 테스트")
    void selectCenterLoginInfoTest() {
        // given
        CenterMemberDTO memberDTO = createTestCenterMember();
        centerMemberMapper.insertCenterMember(memberDTO);

        // when
        CenterMemberSessionDTO loginInfo = centerMemberMapper.selectCenterLoginInfo("testCenter", "test123!");

        // then
        assertThat(loginInfo).isNotNull();
        assertThat(loginInfo.getCenterMemberId()).isEqualTo("testCenter");
    }


    @Test
    @DisplayName("잘못된 비밀번호로 로그인 시도 테스트")
    void loginWithWrongPasswordTest() {
        // given
        CenterMemberDTO memberDTO = createTestCenterMember();
        centerMemberMapper.insertCenterMember(memberDTO);

        // when
        CenterMemberSessionDTO loginInfo = centerMemberMapper.selectCenterLoginInfo("testCenter", "wrongPassword!");

        // then
        assertThat(loginInfo).isNull();
    }

    @Test
    @DisplayName("존재하지 않는 아이디로 로그인 시도 테스트")
    void loginWithNonExistentIdTest() {
        // given
        CenterMemberDTO memberDTO = createTestCenterMember();
        centerMemberMapper.insertCenterMember(memberDTO);

        // when
        CenterMemberSessionDTO loginInfo = centerMemberMapper.selectCenterLoginInfo("nonExistentId", "test123!");

        // then
        assertThat(loginInfo).isNull();
    }

    @Test
    @DisplayName("센터파일 등록 시 잘못된 회원번호 테스트")
    void insertCenterFileWithInvalidMemberNoTest() {
        // given
        CenterFileDTO fileDTO = createTestCenterFile(999999L); // 존재하지 않는 회원번호

        // when & then
        assertThatThrownBy(() -> centerMemberMapper.insertCenterFile(fileDTO))
                .isInstanceOf(Exception.class);
    }


}