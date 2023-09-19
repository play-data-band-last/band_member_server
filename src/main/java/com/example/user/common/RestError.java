package com.example.user.common;

public class RestError {
    private String id;
    private String message;

    public RestError(String id, String message) {
        this.id = id;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}