package com.andremoresco.asynctask.http;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HttpErrorResponse {

    @JsonProperty("error_code")
    private final String errorCode;
    private final Object message;

    public HttpErrorResponse(String errorCode, Object message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Object getMessage() {
        return message;
    }

}
