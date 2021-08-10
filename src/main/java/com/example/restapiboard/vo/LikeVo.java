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
    private int parent_id;  //게시글 id

    private boolean like_type;          //true면 좋아요, false면 싫어요
}
