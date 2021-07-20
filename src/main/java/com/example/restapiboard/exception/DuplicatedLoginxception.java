package com.example.restapiboard.exception;

public class DuplicatedLoginxception extends RuntimeException{
    public DuplicatedLoginxception(String message) {
        super(message);
    }
}
