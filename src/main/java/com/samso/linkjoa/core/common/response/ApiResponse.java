package com.samso.linkjoa.core.common.response;

import lombok.Getter;

@Getter
public class ApiResponse<T> {
    private Boolean status;
    private T body;

    public ApiResponse(Boolean status, T body){
        this.status = status;
        this.body = body;
    }
}
