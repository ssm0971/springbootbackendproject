package com.example.hope_dog.dto.volun.car;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter @ToString
@NoArgsConstructor

public class CarDetailDTO extends CarDTO {
    private List<CarCommentDTO> comments;
//    기본 cardto정보를 유지하면서 댓글 목록 포함할 수 있게 dto 추가
}
