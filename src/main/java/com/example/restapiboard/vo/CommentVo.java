package com.example.restapiboard.vo;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentVo {

    //데이터베이스의 필드명과 같아야한다.
    private int comment_id;
    private int board_id;
    private int member_id;
    private String content;

    private int like_count;
    private int dislike_count;

    private LocalDateTime date;
}
