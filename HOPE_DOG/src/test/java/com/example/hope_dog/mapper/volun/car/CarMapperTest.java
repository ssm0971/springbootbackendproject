//package com.example.hope_dog.mapper.volun.car;
//
//import com.example.hope_dog.dto.volun.car.CarDTO;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//@SpringBootTest
//@Transactional
//class CarMapperTest {
//    @Autowired
//    private CarMapper carMapper;
//
//    private CarDTO carDTO;
//
//    @BeforeEach
//    void setUp() {
//        carDTO = new CarDTO();
//        carDTO.setCarCate("평택");
//        carDTO.setCarTitle("제목");
//        carDTO.setCarContent("카풀 내용");
//        carDTO.setCarWriter(1L);
//    }
//
//    @Test
//    void testInsertCar(){
//        //given
//        CarDTO carDTO = new CarDTO();
//        carDTO.setCarTitle("Test Car");
//
//        //when
//        carMapper.CarMain(carDTO);
//
//        //then
//        assertThat(carDTO.getCarNo()).isNotNull();
//    }
//
//}