package com.example.hope_dog.dto.page;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
    private int page; // 현재 페이지
    private int amount; // 한 페이지 당 게시물 수
    private String cate;//카테고리 정보 추가

    public Criteria() {
        this(1, 9);
    }

    public Criteria(int page, int amount){
        this.page = page;
        this.amount = amount;
    }

    public int getPageStart(){
        return (page - 1) * this.amount;
    }


}