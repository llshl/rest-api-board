package com.example.restapiboard.vo;

import com.example.restapiboard.dto.LikeType;
import com.example.restapiboard.dto.ParentType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LikeVo {

    private int like_id;
    private int member_id;  //좋아요를 누른 사람(중복 좋아요를 막기 위해)
    private int parent_id;  //게시글 혹은 댓글의 id
//    private ParentType parent_type; //이 좋아요싫어요가 달린곳이 게시글인지 댓글인지 enum
//    private LikeType like_type;     //좋아요인지 싫어요인지

    private boolean parent_type;        //true면 게시글, false면 댓글
    private boolean like_type;          //true면 좋아요, false면 싫어요
}
