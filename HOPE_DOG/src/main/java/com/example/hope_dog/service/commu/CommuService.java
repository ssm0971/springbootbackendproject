package com.example.hope_dog.service.commu;

import com.example.hope_dog.dto.centerMember.CenterMemberDTO;
import com.example.hope_dog.dto.commu.CommuCommentDTO;
import com.example.hope_dog.dto.commu.CommuDTO;
import com.example.hope_dog.dto.commu.CommuDetailDTO;
import com.example.hope_dog.dto.member.MemberDTO;
import com.example.hope_dog.dto.page.Criteria;
import com.example.hope_dog.mapper.commu.CommuMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class CommuService {

    private final CommuMapper commuMapper;

    //목록 조회 메서드
    public List<CommuDTO> getCommuList(){
        List<CommuDTO> commuList = commuMapper.commuCatalog();

        // 각 게시글 작성자 확인
        for (CommuDTO commu : commuList) {
            if (commu.getCommuWriter() % 2 == 0) { // 작성자 ID가 짝수일 때
                // 센터회원인 경우 작성자 센터회원 정보 조회
                CenterMemberDTO centerMember = commuMapper.commuCenterMemberByNo(commu.getCommuWriter());
                if (centerMember != null) { // 센터회원 정보가 존재할 때
                    // 센터회원 이름 보여주기
                    commu.setCenterMemberName(centerMember.getCenterMemberName());
                }
            } else { // 작성자 ID가 홀수일 때
                // 일반회원으로
                MemberDTO member = commuMapper.commuMemberByNo(commu.getCommuWriter());
                if (member != null) { // 일반회원 정보가 존재할 때
                    commu.setMemberNickname(member.getMemberNickname());
                }
            }
        }

        return commuList;
    }

    //게시글 클릭시마다 조회수 +1
    public void commuGood(CommuDTO commuDTO) {
        commuMapper.commuGood(commuDTO);
    }

    //인기 게시글 따로 보여주는
//    public List<CommuDTO> cateCommuGood(String cate) {
//        return commuMapper.findCateByGood(cate); // 인기 게시글을 조회하는 메서드로 수정
//    }

    // 카테고리별 게시글 조회
    public List<CommuDTO> getCommuListByCate(String cate) {
        return commuMapper.findCate(cate);
    }


    //검색
    public List<CommuDTO> searchCommu(String keyword) {
        System.out.println("서비스 검색 키워드: " + keyword); // 키워드 출력
        Map<String, Object> params = new HashMap<>();

        params.put("memberNickName", keyword);
        params.put("centerMemberName", keyword);

        // 검색 결과 목록 가져오기
        List<CommuDTO> commuList = commuMapper.searchCommu(params);

        // 각 게시글 작성자 확인
        for (CommuDTO commu : commuList) {
            if (commu.getCommuWriter() % 2 == 0) { // 작성자 ID가 짝수일 때
                // 센터회원인 경우 작성자 센터회원 정보 조회
                CenterMemberDTO centerMember = commuMapper.commuCenterMemberByNo(commu.getCommuWriter());
                if (centerMember != null) { // 센터회원 정보가 존재할 때
                    // 센터회원 이름 보여주기
                    commu.setCenterMemberName(centerMember.getCenterMemberName());
                }
            } else { // 작성자 ID가 홀수일 때
                // 일반회원으로
                MemberDTO member = commuMapper.commuMemberByNo(commu.getCommuWriter());
                if (member != null) { // 일반회원 정보가 존재할 때
                    commu.setMemberNickname(member.getMemberNickname());
                }
            }
        }

        return commuList; // 작성자 정보가 설정된 결과 리스트 반환
    }

    //게시글 상세

    // 게시글 상세 정보 및 댓글 조회 메소드
    public CommuDetailDTO getCommuDetail(Long commuNo) {

        // 게시글 정보 조회
        CommuDetailDTO commuDetail = commuMapper.selectCommuByNo(commuNo); // 기존 메소드와 동일
        // 작성자 정보 세팅
        setWriterInfo(commuDetail);

        // 댓글 정보 조회
        List<CommuCommentDTO> comments = commuMapper.selectCommentsByCommuNo(commuNo); // 댓글 리스트 조회
        System.out.println("댓글 : "+comments.size());
        commuDetail.setComments(comments); // 댓글 정보를 DTO에 세팅

        return commuDetail; // 게시글 상세 정보와 댓글이 포함된 DTO 반환
    }

    // 작성자 정보 세팅 메소드
    private void setWriterInfo(CommuDetailDTO commuDetail) {
        if (commuDetail.getCommuWriter() % 2 == 0) { // 짝수면 센터회원
            CenterMemberDTO centerMember = commuMapper.commuCenterMemberByNo(commuDetail.getCommuWriter());
            commuDetail.setCenterMemberName(centerMember != null ? centerMember.getCenterMemberName() : null);
        } else { // 홀수면 일반회원
            MemberDTO member = commuMapper.commuMemberByNo(commuDetail.getCommuWriter());
            commuDetail.setMemberNickname(member != null ? member.getMemberNickname() : null);
        }
    }



    // 게시글 작성
    public void writePost(CommuDTO commuDTO) {
        // 커뮤니티 게시글의 작성일 및 수정일 자동 설정
        Date now = new Date();
        commuDTO.setCommuRegiDate(now);
        commuDTO.setCommuUpdateDate(now);

        // 작성자가 일반회원인지 센터회원인지 확인
        if (commuDTO.getCommuWriter() != null) {
            if (commuDTO.getCommuWriter() % 2 == 0) { // 짝수일 때 센터회원
                CenterMemberDTO centerMember = commuMapper.commuCenterMemberByNo(commuDTO.getCommuWriter());
                if (centerMember != null) {
                    commuDTO.setCenterMemberName(centerMember.getCenterMemberName());
                }
            } else { // 홀수일 때 일반회원
                MemberDTO member = commuMapper.commuMemberByNo(commuDTO.getCommuWriter());
                if (member != null) {
                    commuDTO.setMemberNickname(member.getMemberNickname());
                }
            }
        }

        // 글 작성 호출
        commuMapper.insertWrite(commuDTO);
    }








}
