package com.example.restapiboard.dto;

import com.example.restapiboard.vo.BoardVo;
import com.example.restapiboard.vo.CommentVo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class CommnetListDto {

    //게시글 전체 조회 시 해당 페이지의 모든 게시글과 페이징 정보를 담아서 반환하는 dto

    private List<CommentVo> commentVos;

    //좋아요 싫어요 개수
    private int like_count;
    private int dislike_count;
}
