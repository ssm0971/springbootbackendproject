package com.example.hope_dog.dto.mypage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Getter @Setter
@NoArgsConstructor
@ToString
public class MpAdoptRequestDTO {

        private Long adoptRequestNo;              // 신청서 번호 PK
        private String adoptRequestName;          // 신청인
        private String adoptRequestPhone;         // 연락처
        private String adoptRequestPhoneSub;      // 대체 연락처
        private String adoptRequestEmail;         // 이메일
        private String adoptRequestGender;          // 신청자 성별
        private LocalDate adoptRequestAge;             // 신청자 생일
        private String adoptRequestZipcode;       // 우편번호
        private String adoptRequestAddress;       // 주소
        private String adoptRequestAddressDetail; // 상세주소
        private String adoptRequestJob;           // 직업
        private String adoptRequestMarry;           // 결혼여부
        private String adoptRequestInfoAgreement;   // 개인정보 수집 동의
        private String adoptRequestQ1;            // 질문1
        private String adoptRequestQ2;            // 질문2
        private String adoptRequestQ3;            // 질문3
        private String adoptRequestQ4;            // 질문4
        private String adoptRequestQ5;            // 질문5
        private String adoptRequestQ6;            // 질문6
        private String adoptRequestQ7;            // 질문7
        private String adoptRequestQ8;            // 질문8
        private String adoptRequestQ9;            // 질문9
        private String adoptRequestQ10;           // 질문10
        private String adoptRequestQ11;           // 질문11
        private String adoptRequestQ12;           // 질문12
        private String adoptRequestQ13;           // 질문13
        private String adoptRequestQ14;           // 질문14
        private String adoptRequestQ15;           // 질문15
        private String adoptRequestAdoptAgreement;  // 입양 동의
        private Long adoptNo;                     // 신청한 게시글
        private Long memberNo;                    // 일반회원 FK
        private Long centerMemberNo;              // 센터 회원 FK
        private String adoptRequestStatus;          // 신청서 상태 (기본값 NULL)

    private String adoptTitle;
    private Date protectRegidate; // 날짜 형식에 따라 LocalDate 또는 LocalDateTime 사용
    private String memberNickname;
    private String centerMemberName;

    }


