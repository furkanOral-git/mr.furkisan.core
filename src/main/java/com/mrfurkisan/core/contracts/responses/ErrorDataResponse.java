package com.mrfurkisan.core.contracts.responses;

import org.springframework.http.HttpStatusCode;

import com.mrfurkisan.core.contracts.abstracts.BaseDataResponse;

public class ErrorDataResponse<TData> extends BaseDataResponse<TData> {

    public ErrorDataResponse(HttpStatusCode statusCode, String message, TData data) {
        
        super(statusCode, false, message, null);
    }
}
