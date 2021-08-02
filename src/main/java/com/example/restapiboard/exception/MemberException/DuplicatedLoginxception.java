package com.example.restapiboard.exception.MemberException;

public class DuplicatedLoginxception extends RuntimeException{
    public DuplicatedLoginxception(String message) {
        super(message);
    }
}
