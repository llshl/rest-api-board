package com.example.restapiboard.vo;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentVo {

    //데이터베이스의 필드명과 같아야한다.
    private int comment_id;
    private int board_id;
    private int member_id;
    private String member_nickname;
    private String content;

    private LocalDateTime date;

    private boolean isUpdated;
}
