package com.springapplication.controller;

import com.springapplication.service.exception.AuthenticationException;
import com.springapplication.service.exception.ExceptionResponse;
import com.springapplication.service.exception.JwtAuthenticationException;
import com.springapplication.service.exception.UserAlreadyExistException;
import com.springapplication.service.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(JwtAuthenticationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public ExceptionResponse handleJwtAuthenticationException(JwtAuthenticationException ex) {
        return ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.PRECONDITION_FAILED.value())
                .error(HttpStatus.PRECONDITION_FAILED.name())
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleUserNotFoundException(UserNotFoundException ex) {
        return ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.name())
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleUserAlreadyExistException(UserAlreadyExistException ex) {
        return ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.name())
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleAuthenticationException(AuthenticationException ex) {
        return ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.name())
                .message(ex.getMessage())
                .build();
    }
}
