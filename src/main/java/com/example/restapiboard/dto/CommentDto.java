package com.example.restapiboard.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommentDto {

    private int boardId;
    private int memberId;
    private String content;
}
