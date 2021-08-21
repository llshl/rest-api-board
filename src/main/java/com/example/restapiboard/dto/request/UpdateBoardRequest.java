package com.example.restapiboard.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBoardRequest {
    private int board_id;
    private String title;
    private String content;

    public UpdateBoardRequest(){}
}
