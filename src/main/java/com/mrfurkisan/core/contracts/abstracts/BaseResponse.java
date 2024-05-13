package com.mrfurkisan.core.contracts.abstracts;

import org.springframework.http.HttpStatusCode;

public abstract class BaseResponse {
    
    private HttpStatusCode __statusCode;
    private String __message;
    private Boolean __success;

    public BaseResponse(HttpStatusCode statusCode, Boolean success, String message) {
        super();
        this.__statusCode = statusCode;
        this.__success = success;
        this.__message = message;
    }

    public HttpStatusCode GetStatusCode() {
        return this.__statusCode;
    }

    public String GetMessage() {
        return this.__message;
    }

    public Boolean GetSuccess() {
        return this.__success;
    }
}
