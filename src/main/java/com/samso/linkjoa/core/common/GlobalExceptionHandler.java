package com.samso.linkjoa.core.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleDataIntegrityViolationException(Exception e){
        return new ErrorResponse(e.getMessage());
    }
    /*공통처리*/
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleGlobalException(Exception e) {
        return new ErrorResponse(e.getMessage());
    }
}
