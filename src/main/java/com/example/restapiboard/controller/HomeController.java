package com.example.restapiboard.controller;

import com.example.restapiboard.dto.MemberDto;
import com.example.restapiboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "board";
    }

    @GetMapping("/board/createBoard")
    public String createBoard() {
        return "create-board";
    }

    @GetMapping("/board/{id}")
    public String boardInfo(Model model, @PathVariable("id") int id) {
        log.info("id번 페이지 이동");
        model.addAttribute("id", id);
        return "board-info";
    }

    @GetMapping("/update/{id}")
    public String boardUpdate(Model model, @PathVariable("id") int id) {
        log.info(id+"번 게시글 수정");
        model.addAttribute("id", id);
        return "update-board";
    }
}