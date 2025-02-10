package com.samso.linkjoa.core.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Getter
public class ApplicationInternalException extends RuntimeException{

    private String code;
    private String message;

    public ApplicationInternalException(String code, String message){
        this.code = code;
        this.message = message;
    }


}
