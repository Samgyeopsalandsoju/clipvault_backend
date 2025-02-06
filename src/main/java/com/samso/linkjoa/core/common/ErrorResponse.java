package com.samso.linkjoa.core.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor
@Getter
public class ErrorResponse {
    private final List<StackTraceElement> stackTraces;
    private final String message;
    private final HttpStatus status;
}
