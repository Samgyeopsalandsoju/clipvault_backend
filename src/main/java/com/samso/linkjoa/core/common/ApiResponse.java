package com.samso.linkjoa.core.common;

import lombok.Getter;

@Getter
public class ApiResponse<T> {
    private String status;
    private T body;

    public ApiResponse(String status, T body){
        this.status = status;
        this.body = body;
    }
}
