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

    //service단에서 조립되어 db에 들어가는 형태의 클래스
    //db에서 꺼내고 이 형태로 그대로 프론트로 나가는 클래스
    private int like_id;
    private int member_id;
    private int parent_id;
    private ParentType parent_type;
    private LikeType like_type;
}
