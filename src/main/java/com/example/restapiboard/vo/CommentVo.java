package com.example.restapiboard.vo;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentVo {

    //service단에서 조립되어 db에 들어가는 형태의 클래스
    //db에서 꺼내고 이 형태로 그대로 프론트로 나가는 클래스
    private Long commentId;
    private Long boardId;
    private Long memberId;
    private String content;

    private int like;
    private int dislike;

    private LocalDateTime date;
}
