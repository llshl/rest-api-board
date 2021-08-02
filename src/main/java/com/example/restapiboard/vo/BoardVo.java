package com.example.restapiboard.vo;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardVo {

    //service단에서 조립되어 db에 들어가는 형태의 클래스
    //db에서 꺼내고 이 형태로 그대로 프론트로 나가는 클래스
    private int board_id;
    private String title;
    private String author;
    private String content;

    private int like_count;     //join 쿼리를 통해서 boardVo에 좋아요 싫어요 개수도 포함되게하자
    private int dislike_count;

    private boolean isUpdated;
    private LocalDateTime date;

    //좋아요 많이 받은 게시물 히트게시판 등록
    //싫어요가 좋아요의 40% 이하여야함
/*    public boolean isHit() {
        if(this.like * 0.4 > this.dislike && this.like > 10) return true;
        return false;
    }*/

/*    @Builder
    public BoardVo(String title, String content, int like, int dislike, String author, LocalDateTime date) {
        this.title = title;
        this.content = content;
        this.like = like;
        this.dislike = dislike;
        this.author = author;
        this.date = date;
    }*/

/*    @Override
    public String toString() {
        return "\ntitle: "+title
                +"\ncontent: "+content;
    }*/

}
