package com.example.restapiboard.dto.request;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
public class CreateBoardRequest {
    private String title;
    private String content;

    public CreateBoardRequest(){}
}
