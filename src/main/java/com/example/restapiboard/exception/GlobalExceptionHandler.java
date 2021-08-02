package com.example.restapiboard.exception;

import com.example.restapiboard.exception.MemberException.DuplicatedLoginxception;
import com.example.restapiboard.exception.MemberException.MemberNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    //모든 예외에 대한 처리를 해주는 메서드
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse((new Date()), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //MemberNotFoundException에 대한 처리 메서드
    @ExceptionHandler(MemberNotFoundException.class)
    public final ResponseEntity<Object> handleMemberNotFoundException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse((new Date()), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    //DuplicatedLoginxception에 대한 처리 메서드
    @ExceptionHandler(DuplicatedLoginxception.class)
    public final ResponseEntity<Object> handleDuplicatedLoginxceptionException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse((new Date()), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.FORBIDDEN); //403코드
    }

    //BoardNotFoundException 대한 처리 메서드
    @ExceptionHandler(BoardNotFoundException.class)
    public final ResponseEntity<Object> handleBoardNotFoundException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse((new Date()), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND); //403코드
    }

    //handleMethodArgumentNotValid를 재정의하여 유효성검사(Valid)에 걸리면 반환되는 예외
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
                "Validation Failed", ex.getBindingResult().toString());
        //"timestamp" , "message" , "details" 의 순서로 인자 생성하여 전달

        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
