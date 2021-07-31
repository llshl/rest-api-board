package com.example.restapiboard.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import java.util.Locale;

@Controller
@Slf4j
@RequiredArgsConstructor
public class InternationalizedController {

    private final MessageSource messageSource;

    @GetMapping(path = "/greeting-internationalized")
    public String greetingInternationalized(
            @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return messageSource.getMessage("greeting.message",null,locale);
    }
}
