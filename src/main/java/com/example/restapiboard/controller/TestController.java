package com.example.restapiboard.controller;

import com.example.restapiboard.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("/test")
    @ResponseBody
    public List<Map<String, Object>> getTest(){
        return testService.getTest();
    }
}
