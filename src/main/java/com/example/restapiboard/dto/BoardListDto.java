package com.example.restapiboard.dto;

import com.example.restapiboard.vo.BoardVo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class BoardListDto {

    //게시글 전체 조회 시 해당 페이지의 모든 게시글과 페이징 정보를 담아서 반환하는 dto

    private List<BoardVo> boardVos;

    //페이지 하단에 페이지 시작번호와 끝번호
    private int startPageNum;
    private int endPageNum;

    //게시글 총 개수(프론트에서 글 번호를 매기기 위함)
    private int count;

    //현재 페이지 번호
    private int currentPageNum;

    //페이지 하단에 이전버튼과 다음버튼 여부
    private boolean prev;
    private boolean next;

    //좋아요 싫어요 개수
    private int like_count;
    private int dislike_count;
}
