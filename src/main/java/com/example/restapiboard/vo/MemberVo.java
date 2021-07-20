package com.example.restapiboard.vo;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberVo {

    //데이터베이스의 필드명과 같아야한다.
    private int member_id;
    private String login_id;
    private String login_password;
    private String email;
    private String name;
    private String nickname;
}
