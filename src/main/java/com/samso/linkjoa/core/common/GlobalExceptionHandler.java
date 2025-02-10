package com.samso.linkjoa.core.common;

import com.samso.linkjoa.core.common.response.ErrorResponse;
import com.samso.linkjoa.core.common.response.FailResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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

    @ExceptionHandler(ApplicationInternalException.class)
    public FailResponse handleServiceException(ApplicationInternalException e){

        //TODO 로그추가
        return new FailResponse(e.getCode());
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ErrorResponse handleUsernameNotFoundException(Exception e){
        return new ErrorResponse(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
    /*공통처리*/
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadReqExceptions(Exception e, WebRequest web) {
        List<StackTraceElement> stactTraces = null;
        if(stackTrace){
            stactTraces = Arrays.asList(e.getStackTrace());
        }

        //FIXME
        System.out.println("error : " + e.getMessage());
        e.printStackTrace();
        return new ErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
