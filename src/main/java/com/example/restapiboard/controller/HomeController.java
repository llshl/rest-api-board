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
    public String home(Model model, @AuthenticationPrincipal MemberDetailsImpl memberDetails){
        model.addAttribute("username", memberDetails.getUsername());
        System.out.println("member_id: "+memberDetails.getMemberVo().getMember_id());
        return "indextest";
    }

    @GetMapping("/write")
    public String writeBoard(Model model, @AuthenticationPrincipal MemberDetailsImpl memberDetails){
        model.addAttribute("username", memberDetails.getUsername());
        return "writeboard";
    }
}
