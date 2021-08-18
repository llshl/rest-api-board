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

@Controller
@RequiredArgsConstructor
@Slf4j
public class SignupController {

    private final MemberService memberService;

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    @GetMapping("/user/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(@RequestBody MemberDto memberDto) {
        log.info("회원가입 요청 처리");
        memberService.joinMember(memberDto);
        return "redirect:/";
    }

    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(String code) {
        // authorizedCode: 카카오 서버로부터 받은 인가 코드
        // 사용자가 카카오 로그인 페이지에서 동의하고 진행하여 넘어온 코드
        log.info("kakaoLogin");
        memberService.kakaoLogin(code);
        return "board";
    }
}