package com.example.restapiboard.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PageDto {

    //페이징 계산 결과를 컨트롤러로 전달 시 사용
    //Pagination => BoardController

    private int displayPost;

    //페이지 하단에 페이지 시작번호와 끝번호
    private int startPageNum;
    private int endPageNum;

    //페이지 하단에 이전버튼과 다음버튼 여부
    private boolean prev;
    private boolean next;
}
