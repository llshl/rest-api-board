package com.example.restapiboard.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class AuthInterceptor  implements HandlerInterceptor {
    private final String LOGIN_SESSION = "LOGIN_SESSION";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        String loginedMemberId = (String) request.getSession().getAttribute(LOGIN_SESSION);
        if (loginedMemberId == null) {
            log.info("no login session");
            response.sendRedirect("http://localhost:8080/login");
            return false;
        }
        else{
            log.info("user id: "+loginedMemberId);
        }
        return true;
    }
}
