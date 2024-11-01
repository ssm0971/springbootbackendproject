package com.example.hope_dog.service.volun.car;

import com.example.hope_dog.dto.centerMember.CenterMemberDTO;
import com.example.hope_dog.dto.member.MemberDTO;
import com.example.hope_dog.dto.page.Criteria;
import com.example.hope_dog.dto.volun.car.CarCommentDTO;
import com.example.hope_dog.dto.volun.car.CarDTO;
import com.example.hope_dog.dto.volun.car.CarDetailDTO;
import com.example.hope_dog.mapper.volun.car.CarMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class CarService {

    private final CarMapper carMapper;

    //목록조회 메서드
    public List<CarDTO> getCarList() {
        //carmapper로 정보 조회
        List<CarDTO> carList = carMapper.selectAllCars();

        //각 게시글 작성자 확인
        for (CarDTO car : carList) {
            if (car.getCarWriter() % 2 == 0) {//작성자id가 짝수일 때
                // 센터회원인 경우 작성자 센터회원 정보 조회
                CenterMemberDTO centerMember = carMapper.selectCenterMemberByNo(car.getCarWriter());
                if (centerMember != null) { //센터회원 정보가 존재할 때
                    //센터회원 이름 보여주기
                    car.setCenterMemberName(centerMember.getCenterMemberName());
                }

            } else {//작성자 id가 홀수일 때
                // 일반회원으로
                MemberDTO member = carMapper.selectMemberByNo(car.getCarWriter());
                if (member != null) { //일반회원 정보가 존재할 때
                    car.setMemberNickname(member.getMemberNickname());
                }
            }
        }


        return carList;
    }


    // 카테고리에 따른 게시글 조회
    public List<CarDTO> getCarListByCate(String cate,Criteria criteria) {
        List<CarDTO> carList = carMapper.selectCate(cate,criteria);

        // 각 게시글 작성자 확인
        for (CarDTO car : carList) {
            if (car.getCarWriter() % 2 == 0) {
                // 센터회원인 경우 작성자 센터회원 정보 조회
                CenterMemberDTO centerMember = carMapper.selectCenterMemberByNo(car.getCarWriter());
                if (centerMember != null) { // 센터회원 정보가 존재할 때
                    // 센터회원 이름 보여주기
                    car.setCenterMemberName(centerMember.getCenterMemberName());
                }
            } else {
                // 일반회원인 경우
                MemberDTO member = carMapper.selectMemberByNo(car.getCarWriter());
                if (member != null) {
                    car.setMemberNickname(member.getMemberNickname());
                }
            }
        }

        return carList;
    }

    //페이지네이션
    // 전체 게시글 조회
    public List<CarDTO> findCarMain() {
        return carMapper.selectCarMain();
    }

    // 게시글 총 개수 조회 (Criteria를 통해 카테고리 정보를 확인)
    public int findCarTotal(Criteria criteria) {
        String cate = criteria.getCate(); // Criteria에서 카테고리 정보 추출
        if (cate != null && !cate.isEmpty()) {
            return carMapper.countCarsByCategory(cate); // 카테고리별 총 개수 조회
        }
        return carMapper.carTotal(); // 카테고리 없는 경우 전체 개수 조회
    }

    // 페이지별 게시글 조회
    public List<CarDTO> findCarPage(Criteria criteria) {
        return carMapper.selectCarPage(criteria);
    }

//    검색기능
    public List<CarDTO> searchCars(String searchType, String keyword, Criteria criteria) {
        Map<String, Object> params = new HashMap<>();
        System.out.println("서비스 Map 들어옴");


        // 검색 조건 설정
        if ("title".equals(searchType)) {
            params.put("carTitle", keyword);
        } else if ("nickname".equals(searchType)) {
            try {
                Long writerId = Long.parseLong(keyword);
                params.put("carWriter", writerId);
            } catch (NumberFormatException e) {
                return List.of(); // 변환 실패 시 빈 리스트 반환
            }
        }

        // Mapper 호출
        List<CarDTO> carList = carMapper.searchCars(params, criteria);
        System.out.println("메소드 리스트 들어오세요");

        // 작성자 정보 설정
        for (CarDTO car : carList) {
            if (car.getCarWriter() % 2 == 0) {
                // 센터회원인 경우 작성자 센터회원 정보 조회
                CenterMemberDTO centerMember = carMapper.selectCenterMemberByNo(car.getCarWriter());
                if (centerMember != null) {
                    car.setCenterMemberName(centerMember.getCenterMemberName());
                }
            } else {
                // 일반회원인 경우
                MemberDTO member = carMapper.selectMemberByNo(car.getCarWriter());
                if (member != null) {
                    car.setMemberNickname(member.getMemberNickname());
                }
            }
        }

        return carList;
    }

    public int findCarSearch(Criteria criteria, String searchType, String keyword) {
        String cate = criteria.getCate(); // 카테고리 정보 추출
        if (searchType.equals("title")) {
            return carMapper.countCarsByTitle(keyword); // 제목으로 검색할 때
        } else if (searchType.equals("nickname")) {
            return carMapper.countCarsByNickname(keyword); // 닉네임으로 검색할 때
        }
        // 카테고리 정보가 있을 경우
        if (cate != null && !cate.isEmpty()) {
            return carMapper.countCarsByCategory(cate); // 카테고리별 총 개수 조회
        }
        return carMapper.carTotal(); // 카테고리 없는 경우 전체 개수 조회
    }



    //게시글 상세 조회

    public CarDetailDTO getCarDetail(Long carNo) {
        CarDetailDTO carDetail = carMapper.selectCarDetail(carNo);

        // 게시글 작성자 구별 (기존과 동일)
        if (carDetail.getCarWriter() % 2 == 0) {
            CenterMemberDTO centerMember = carMapper.selectCenterMemberByNo(carDetail.getCarWriter());
            if (centerMember != null) {
                carDetail.setCenterMemberName(centerMember.getCenterMemberName());
            }
        } else {
            MemberDTO member = carMapper.selectMemberByNo(carDetail.getCarWriter());
            if (member != null) {
                carDetail.setMemberNickname(member.getMemberNickname());
            }
        }

        // 댓글 리스트 가져오기
        List<CarCommentDTO> comments = carMapper.selectCommentsByCarNo(carNo);
        for (CarCommentDTO comment : comments) {
            // 댓글 작성자 구별
            if (comment.getCarCommentWriter() % 2 == 0) {
                CenterMemberDTO centerMember = carMapper.selectCenterMemberByNo(comment.getCarCommentWriter());
                if (centerMember != null) {
                    comment.setCenterMemberName(centerMember.getCenterMemberName());
                }
            } else {
                MemberDTO member = carMapper.selectMemberByNo(comment.getCarCommentWriter());
                if (member != null) {
                    comment.setMemberNickname(member.getMemberNickname());
                }
            }
        }

        carDetail.setComments(comments); // 댓글 리스트를 DTO에 추가
        return carDetail;
    }


    }

