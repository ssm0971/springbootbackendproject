package com.example.hope_dog.service.volun.car;

import com.example.hope_dog.dto.centerMember.CenterMemberDTO;
import com.example.hope_dog.dto.commu.CommuDetailDTO;
import com.example.hope_dog.dto.member.MemberDTO;
import com.example.hope_dog.dto.page.Criteria;
import com.example.hope_dog.dto.volun.car.CarCommentDTO;
import com.example.hope_dog.dto.volun.car.CarDTO;
import com.example.hope_dog.dto.volun.car.CarDetailDTO;
import com.example.hope_dog.dto.volun.car.CarReportDTO;
import com.example.hope_dog.mapper.volun.car.CarMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class CarService {

    private final CarMapper carMapper;

    //목록조회 메서드
    public List<CarDTO> getCarList(HttpSession session) {
        //carmapper로 정보 조회
        List<CarDTO> carList = carMapper.selectAllCars();

        //세션에서 로그인한 회원 정보 가져옴
        Long memberNo = (Long) session.getAttribute("memberNo");
        Long centerMemberNo = (Long) session.getAttribute("centerMemberNo");

        //각 게시글 작성자 확인
        for (CarDTO car : carList) {
            Long writerNo = car.getCarWriter();

            if (writerNo % 2 == 0) {//작성자id가 짝수일 때
                // 센터회원인 경우 작성자 센터회원 정보 조회
                CenterMemberDTO centerMember = carMapper.selectCenterMemberByNo(writerNo);
                if (centerMember != null) { //센터회원 정보가 존재할 때
                    //센터회원 이름 보여주기
                    car.setCenterMemberName(centerMember.getCenterMemberName());
                    car.setCenterMemberNo(writerNo);
                }

            } else {//작성자 id가 홀수일 때
                // 일반회원으로
                MemberDTO member = carMapper.selectMemberByNo(writerNo);
                if (member != null) { //일반회원 정보가 존재할 때
                    car.setMemberNickname(member.getMemberNickname());
                    car.setMemberNo(writerNo);
                }
            }
        }


        return carList;
    }


    // 카테고리에 따른 게시글 조회
    public List<CarDTO> getCarListByCate(String cate){
        List<CarDTO> carList = carMapper.findCarByCate(cate);

        // 각 게시글 작성자 확인
        for (CarDTO car : carList) {
            Long writerNo = car.getCarWriter();

            if (writerNo % 2 == 0) { // 작성자 ID가 짝수일 때
                // 센터회원인 경우 작성자 센터회원 정보 조회
                CenterMemberDTO centerMember = carMapper.selectCenterMemberByNo(writerNo);
                if (centerMember != null) { // 센터회원 정보가 존재할 때
                    // 센터회원 이름과 번호 세팅
                    car.setCenterMemberName(centerMember.getCenterMemberName());
                    car.setCenterMemberNo(writerNo); // 센터회원 번호 설정
                }
            } else { // 작성자 ID가 홀수일 때
                // 일반회원으로
                MemberDTO member = carMapper.selectMemberByNo(writerNo);
                if (member != null) { // 일반회원 정보가 존재할 때
                    car.setMemberNickname(member.getMemberNickname());
                    car.setMemberNo(writerNo); // 일반회원 번호 설정
                }
            }
        }

        return carList;
    }

    //검색
    public List<CarDetailDTO> carSearch(String carTitle, String memberNickname, String centerMemberName) {
        return carMapper.carSearch(carTitle, memberNickname, centerMemberName);
    }



    //게시글 상세 조회

    public List<CarDetailDTO> selectCarDetail(Long carNo){
        return carMapper.selectCarDetail(carNo);
    }



    //게시글 작성
    public void carWriter(CarDTO carDTO){
        //작성자 null인지
        if(carDTO.getCarWriter() == null){
            throw new IllegalArgumentException("작성자 id가 필요합니다");
        }

        //작성자 일반회원인지 센터회원인지 확인하여 정보 세팅하기
        Long writerNo = carDTO.getCarWriter();

        if(writerNo % 2 == 0){ // 짝수 = 센터회원
            CenterMemberDTO centerMember = carMapper.selectCenterMemberByNo(writerNo);
            if (centerMember != null) {
                carDTO.setCenterMemberName(centerMember.getCenterMemberName());
            }
        }else{ //홀수 = 일반회원
            MemberDTO member = carMapper.selectMemberByNo(writerNo);
            if (member != null) {
                carDTO.setMemberNickname(member.getMemberNickname());
            }
        }

        // 작성 날짜 및 수정 날짜 설정
        Date now = new Date();
        carDTO.setCarRegiDate(now);
        carDTO.setCarUpdateDate(now);

        //db에 게시글 저장
        carMapper.carWriter(carDTO);

    }

    //게시글 수정
    public void carModify(CarDetailDTO carDetailDTO) {
        carMapper.carModify(carDetailDTO);
    }


    //글 삭제
    public void carDelete(CarDetailDTO carDetailDTO){
        carMapper.carDelete(carDetailDTO);
    }

    //글신고
    public void carContentReport(CarReportDTO carReportDTO){
        carMapper.carContentReport(carReportDTO);
    }

    //게시글 댓글 불러오기
    public List<CarCommentDTO> carComment(Long carNo){
        return carMapper.carComment(carNo);
    }

    //댓글 등록
    public void carCommentRegi(CarCommentDTO carCommentDTO){
        carMapper.carCommentRegi(carCommentDTO);
    }

    //댓글 수정
    public void carCommentModi(CarCommentDTO carCommentDTO){
        carMapper.carCommentModi(carCommentDTO);
    }

    //댓글 삭제
    public void carCommentDelete(CarCommentDTO carCommentDTO){
        carMapper.carCommentDelete(carCommentDTO);
    }

    //댓글 신고
    public void carCommentReport(CarReportDTO carReportDTO){
        carMapper.carCommentReport(carReportDTO);
    }





    }

