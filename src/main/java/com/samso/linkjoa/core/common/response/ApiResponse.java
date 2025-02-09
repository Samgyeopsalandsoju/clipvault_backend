package com.samso.linkjoa.core.common.response;

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
