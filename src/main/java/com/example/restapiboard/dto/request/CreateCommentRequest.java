package com.example.restapiboard.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCommentRequest {
    private int board_id;
    private int member_id;
    private String content;

    public CreateCommentRequest(){}
}
