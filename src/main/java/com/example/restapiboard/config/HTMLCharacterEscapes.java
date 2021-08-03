package com.example.restapiboard.config;

import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

public class HTMLCharacterEscapes extends CharacterEscapes {

    private final int[] asciiEscapes;

    public HTMLCharacterEscapes() {

        // 1. XSS 방지 처리할 특수 문자 지정
        asciiEscapes = CharacterEscapes.standardAsciiEscapesForJSON();
        asciiEscapes['<'] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes['>'] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes['&'] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes['\"'] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes['('] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes[')'] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes['#'] = CharacterEscapes.ESCAPE_CUSTOM;
        asciiEscapes['\''] = CharacterEscapes.ESCAPE_CUSTOM;

    }

    @Override
    public int[] getEscapeCodesForAscii() {
        return new int[0];
    }

    @Override
    public SerializableString getEscapeSequence(int ch) {
        return null;
    }
}
