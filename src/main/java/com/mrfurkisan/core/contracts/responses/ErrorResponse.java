package com.mrfurkisan.core.contracts.responses;

import org.springframework.http.HttpStatusCode;

import com.mrfurkisan.core.contracts.abstracts.BaseResponse;

public class ErrorResponse extends BaseResponse {
    
    public ErrorResponse(HttpStatusCode statusCode, String message) {
        
        super(statusCode, false, message);
    }
}
