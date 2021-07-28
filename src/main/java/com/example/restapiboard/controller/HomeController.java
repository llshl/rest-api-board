package com.example.restapiboard.controller;

import com.example.restapiboard.security.MemberDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal MemberDetailsImpl memberDetails, Principal principal){
        model.addAttribute("username", memberDetails.getUsername());
        System.out.println("멤버 id 반환 되나? "+memberDetails.getMemberVo().getMember_id());
        return "index";
    }
}
