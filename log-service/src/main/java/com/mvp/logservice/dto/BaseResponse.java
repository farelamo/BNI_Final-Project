package com.mvp.logservice.dto;

import lombok.Data;

@Data
public class BaseResponse<T> {
    private Boolean success = Boolean.TRUE;
    private String message = "Operation succes";
    private T data;

    public BaseResponse(T data){
        this.data = data;
    }

    public BaseResponse(Boolean success, String message){
        this.success = success;
        this.message = message;
    }
}
