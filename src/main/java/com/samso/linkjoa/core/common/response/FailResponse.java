package com.samso.linkjoa.core.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Getter

public class FailResponse {
    private final String code;

    public FailResponse(String code){
        this.code = code;
    }
}
