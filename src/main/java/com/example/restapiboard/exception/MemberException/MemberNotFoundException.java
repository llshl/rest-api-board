package com.example.restapiboard.exception.MemberException;

public class MemberNotFoundException extends RuntimeException{
    public MemberNotFoundException(String message) {
        super(message);
    }
}
