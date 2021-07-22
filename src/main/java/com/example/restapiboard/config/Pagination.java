package com.example.restapiboard.config;

import com.example.restapiboard.dto.BoardListDto;

public class Pagination {

    public BoardListDto listPagination(int currentPageNum, int count){

        // 한번에 표시할 페이징 번호의 갯수
        int pageNum_cnt = 10;

        // 표시되는 페이지 번호 중 마지막 번호
        // 마지막 페이지 번호 = ((올림)(현재 페이지 번호 / 한번에 표시할 페이지 번호의 갯수)) * 한번에 표시할 페이지 번호의 갯수
        int endPageNum = (int)(Math.ceil((double)currentPageNum / (double)pageNum_cnt) * pageNum_cnt);

        // 표시되는 페이지 번호 중 첫번째 번호
        //시작 페이지 = 마지막 페이지 번호 - 한번에 표시할 페이지 번호의 갯수 + 1
        int startPageNum = endPageNum - (pageNum_cnt - 1);

        // 마지막 번호 재계산
        int endPageNum_tmp = (int)(Math.ceil((double)count / (double)pageNum_cnt));

        //만약 현재 페이지가 12페이지이고 총 15페이지까지 있다면
        //endPageNum는 20이 된다. 16~20은 필요없는데도 나와버린다.
        //재계산이 필요
        //게시물이 152개로 15페이지까지 있다면
        //endPageNum_tmp은 15가 나온다
        //둘중에 작은것을 페이지끝번호로 사용하면 된다.
        if(endPageNum > endPageNum_tmp) {
            endPageNum = endPageNum_tmp;
        }

        //다음 이전 버튼 여부
        boolean prev = startPageNum == 1 ? false : true;
        boolean next = endPageNum * pageNum_cnt >= count ? false : true;
        return BoardListDto.builder()
                .startPageNum(startPageNum)
                .endPageNum(endPageNum)
                .prev(prev)
                .next(next)
                .build();
    }
}
