package com.example.restapiboard.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommentDto {

    private int board_id;
    private int comment_id;
    private int member_id;
    private String content;
}
