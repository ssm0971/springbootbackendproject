package com.example.hope_dog.mapper.volun.volun;

import com.example.hope_dog.dto.volun.volun.VolunDetailDTO;
import com.example.hope_dog.dto.volun.volun.VolunWriteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class VolunMapperTest {

    @Autowired
    VolunMapper volunMapper;

    VolunWriteDTO volunWriteDTO;
    VolunDetailDTO volunDetailDTO;


}