package com.samso.linkjoa.core.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public class ErrorResponse {
    private final String code;
    private final HttpStatus status;

    public ErrorResponse(String code, HttpStatus status){
        this.code = code;
        this.status = status;
    }
}
