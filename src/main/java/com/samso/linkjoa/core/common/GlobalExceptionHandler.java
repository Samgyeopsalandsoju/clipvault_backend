package com.samso.linkjoa.core.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private boolean stackTrace;

//    @ExceptionHandler(DataIntegrityViolationException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorResponse handleDataIntegrityViolationException(Exception e){
//        return new ErrorResponse(e.getMessage());
//    }

    /*공통처리*/
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadReqExceptions(Exception e, WebRequest web) {
        List<StackTraceElement> stactTraces = null;
        if(stackTrace){
            stactTraces = Arrays.asList(e.getStackTrace());
        }
        return new ErrorResponse(stactTraces, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
