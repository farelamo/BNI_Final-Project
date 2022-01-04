package com.mvp.authservice.Payload;

import lombok.Data;

@Data
public class BaseResponse<T> {
    private Boolean success = Boolean.TRUE;
    private String message = "Operation Success";
    private T data;

    public BaseResponse(T data) {
        this.data = data;
    }

    public BaseResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
