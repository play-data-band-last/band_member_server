package com.example.user.common;

public class RestResult<T> {
    private String status;
    private T data;

    public RestResult(String status, T data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }
}