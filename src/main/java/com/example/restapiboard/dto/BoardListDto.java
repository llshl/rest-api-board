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

    //페이지 하단에 이전버튼과 다음버튼 여부
    private boolean prev;
    private boolean next;
}
