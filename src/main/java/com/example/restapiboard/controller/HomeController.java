package com.example.restapiboard.controller;

import com.example.restapiboard.dto.MemberDto;
import com.example.restapiboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;

    @GetMapping("/")
    public String home(){
        return "index";
    }

    //아이디 중복체크 ajax 구현
    //nickname 중복체크 ajax 구현
    @PostMapping("/login")
    public String login(@RequestBody MemberDto memberDto, HttpSession session, Model model){
        log.info("로그인 시도");
        String result = memberService.login(memberDto,session);
        if(result.equals("success")){
            //성공
            log.info("로그인 성공");
            return "redirect:/";
        }
        else if(result.equals("wrong-password")){
            //비밀번호만 틀림
            return "redirect:/";
        }
        //아이디가 없음
        return "redirect:/";
    }
}
