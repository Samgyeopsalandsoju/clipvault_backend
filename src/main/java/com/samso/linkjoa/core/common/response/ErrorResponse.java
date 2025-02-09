package com.samso.linkjoa.core.common.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponse {
    private final String code;
    private final HttpStatus httpStatus;

    public ErrorResponse(String code, HttpStatus status){
        this.code = code;
        this.httpStatus = status;
    }
}
