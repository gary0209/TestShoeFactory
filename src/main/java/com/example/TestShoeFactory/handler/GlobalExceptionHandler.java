package com.example.TestShoeFactory.handler;

import com.example.TestShoeFactory.entity.OperInfo;
import com.example.TestShoeFactory.exception.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    @ResponseStatus(HttpStatus.OK)
    public Object handleAppException(AppException ex) {
        return new OperInfo(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMsg());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public OperInfo handleException(Exception ex) {
        return new OperInfo(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }
}
