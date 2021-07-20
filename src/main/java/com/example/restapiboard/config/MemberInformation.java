package com.example.restapiboard.config;

import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Configuration
public class MemberInformation {

    private final String LOGIN_SESSION = "LOGIN_SESSION";

    public int getMemberId(HttpServletRequest request){
        HttpSession session = request.getSession();
        int sessionId = (Integer)session.getAttribute(LOGIN_SESSION);
        return sessionId;
    }
}
