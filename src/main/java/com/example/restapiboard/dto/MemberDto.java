package com.example.restapiboard.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberDto {

    private String login_id;
    private String login_password;
    private String email;
    private String name;
    private String nickname;
}
