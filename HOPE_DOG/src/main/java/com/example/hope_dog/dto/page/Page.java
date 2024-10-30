package com.example.hope_dog.dto.page;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Page {
    //페이지 세트당 표시될 수
    private int pageCount;

    //페이지 세트의 시작숫자
    private int startPage;

    //페이지 세트의 마지막 숫자
    private int endPage;

    //실제 가장 마지막 페이지
    private int realEnd;

    //이전버튼 표시 여부
    private boolean prev;

    //다음버튼 표시 여부
    private boolean next;

    //전체 게시글 수
    private int total;

    //화면에서 전달받은 page, amount를 저장하는 객체
    private Criteria criteria;

    //생성자 : 기본 페이지 세트당 5개의 페이지를 사용
    public Page(Criteria criteria, int total) {
        this(criteria,total, 5);
    }

    //생성자
    public Page(Criteria criteria, int total, int pageCount) {
        this.criteria = criteria;
        this.total = total;
        this.pageCount = pageCount;

        //현재 페이지를 기준으로 세트의 마지막 번호, 시작 번호를 구한다
        //ceil() : 올림처리
        this.endPage = (int)(Math.ceil(criteria.getPage()/ (double)pageCount))*pageCount;
        //criteria.getPage()/ (double)pageCount => 7/5.0 = 1.4
        //Math.ceil(criteria.getPage()/ (double)pageCount) =>1.4 올림 => 2.0
        //(int)(Math.ceil(criteria.getPage()/ (double)pageCount)) => 2.0 정수형 변환 => 2
        //(int)(Math.ceil(criteria.getPage()/ (double)pageCount))*pageCount => 2*5 => 10
        this.startPage = endPage - pageCount +1;
        //10-5 => 5+1

        // 게시글 전체 수로 실제 마지막 페이지를 구한다
        this.realEnd =(int)Math.ceil((double)total / criteria.getAmount());
        //총 게시글이 130개 한페에지당 10개  130/10.0 => 10.3 => 올림처리 11.0 => 11

        //세트의 마지막 번호보다 실제 마지막 페이지가 작다면
        if(realEnd < endPage){
            //세트의 마지막 번호를 실제 마지막 페이지로 교체
            this.endPage = realEnd ==0? 1:realEnd;
        }



    }



}
