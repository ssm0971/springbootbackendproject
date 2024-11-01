package com.example.hope_dog.dto.page;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Page {
    // 페이지 세트당 표시될 페이지 수
    private int pageCount;

    // 페이지 세트의 시작 숫자
    private int startPage;

    // 페이지 세트의 마지막 숫자
    private int endPage;

    // 실제 가장 마지막 페이지
    private int realEnd;

    // 이전 버튼 표시 여부
    private boolean prev;

    // 다음 버튼 표시 여부
    private boolean next;

    // 전체 게시글 수
    private int total;

    // 화면에서 전달받은 page, amount를 저장하는 객체
    private Criteria criteria;

    // 생성자 : 기본 페이지 세트당 5개의 페이지를 사용
    public Page(Criteria criteria, int total) {
        this(criteria, total, 5);
    }

    // 생성자
    public Page(Criteria criteria, int total, int pageCount) {
        this.criteria = criteria; // Criteria 객체 저장
        this.total = total; // 전체 게시글 수 저장
        this.pageCount = pageCount; // 페이지 세트당 표시될 페이지 수 저장

        // 현재 페이지를 기준으로 세트의 마지막 번호, 시작 번호를 구함
        // ceil() : 올림 처리
        this.endPage = (int)(Math.ceil(criteria.getPage() / (double)pageCount)) * pageCount;
        // 예시: criteria.getPage()가 7이고 pageCount가 5일 경우
        // 7/5.0 = 1.4 -> Math.ceil(1.4) = 2.0 -> (int)(2.0) = 2
        // 2 * 5 = 10 -> endPage는 10이 됨

        this.startPage = endPage - pageCount + 1;
        // 10 - 5 + 1 = 6 -> startPage는 6이 됨

        // 게시글 전체 수로 실제 마지막 페이지를 구함
        this.realEnd = (int)Math.ceil((double)total / criteria.getAmount());
        // 예시: 총 게시글이 130개이고 한 페이지당 10개일 경우
        // 130 / 10.0 = 13.0 -> realEnd는 13이 됨

        // 세트의 마지막 번호보다 실제 마지막 페이지가 작다면
        if (realEnd < endPage) {
            // 세트의 마지막 번호를 실제 마지막 페이지로 교체
            this.endPage = realEnd == 0 ? 1 : realEnd;
        }

        // 이전 버튼 표시 여부 결정
        this.prev = startPage > 1; // 시작 페이지가 1보다 크면 이전 버튼 표시

        // 다음 버튼 표시 여부 결정
        this.next = endPage < realEnd; // 끝 페이지가 실제 끝 페이지보다 작으면 다음 버튼 표시
    }
}