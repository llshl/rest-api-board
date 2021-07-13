package com.example.restapiboard.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDto {

    private String title;
    private String content;

    //view -> controller에서 받을때 사용할 빌더
    @Builder
    public BoardDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "\ntitle: "+title
                +"\ncontent: "+content;
    }
}
