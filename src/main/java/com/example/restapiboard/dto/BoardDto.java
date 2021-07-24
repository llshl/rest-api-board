package com.example.restapiboard.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class BoardDto {

    private int board_id;

    private String title;
    private String content;
    private String author;

    private int like_count;
    private int dislike_count;

    private boolean isUpdated;
    private boolean isPressedLikeOrDislike;      //좋아요 싫어요 버튼을 이미 눌렀는가?(1인 1회만 누를 수 있게 제한)
    private LocalDateTime date;
}
