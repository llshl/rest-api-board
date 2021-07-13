package com.example.restapiboard.config;

import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Configuration
public class UserInformation {

    private final String LOGIN_SESSION = "LOGIN_SESSION";

    public String getUserName(HttpServletRequest request){
        HttpSession session = request.getSession();
        String sessionId = (String)session.getAttribute(LOGIN_SESSION);
        return sessionId;
    }
}
