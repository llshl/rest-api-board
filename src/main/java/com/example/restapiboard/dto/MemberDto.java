package com.example.restapiboard.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    private String login_id;
    private String login_password;
    private String email;
    private String name;
    private String nickname;
}
