package com.samso.linkjoa.core.common.exception;

import com.samso.linkjoa.core.common.response.ErrorResponse;
import com.samso.linkjoa.core.common.response.FailResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
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

    @ExceptionHandler(ApplicationInternalException.class)
    public FailResponse handleServiceException(ApplicationInternalException e){
        log.warn("Fail: [{}] : {}",
                e.getCode(), e.getMessage());
        return new FailResponse(e.getCode());
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ErrorResponse handleUsernameNotFoundException(Exception e){
        log.warn("Exception: {} ({}:{} Line)"
                , e.toString(),e.getStackTrace()[0].getClassName(), e.getStackTrace()[0].getLineNumber());
        return new ErrorResponse(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
    /* 로그인실패 */
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleBadCredentialsException(Exception e){
        log.warn("Exception: {} ({}:{} Line)"
                , e.toString(), e.getStackTrace()[0].getClassName(), e.getStackTrace()[0].getLineNumber());
        return new ErrorResponse(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
    /*공통처리*/
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleBadReqExceptions(Exception e, WebRequest web) {
        log.error("Exception : {}", e.getMessage(), e);
        return new ErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
