package com.samso.linkjoa.core.common;

import lombok.Getter;

@Getter
public class ErrorResponse {
    //private final List<StackTraceElement> stackTraces;
    //private final HttpStatus status;
    private final String message;
    public ErrorResponse(String message) {
        this.message = message;
    }
}
