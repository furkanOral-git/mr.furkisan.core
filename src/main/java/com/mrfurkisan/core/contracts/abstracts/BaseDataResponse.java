package com.mrfurkisan.core.contracts.abstracts;

import org.springframework.http.HttpStatusCode;

public abstract class BaseDataResponse<TData> extends BaseResponse {
    
    private TData __data;

    public BaseDataResponse(HttpStatusCode statusCode, Boolean success, String message, TData data) {
        super(statusCode, success, message);
        this.__data = data;
    }

    public TData GetData() {
        return this.__data;
    }
}
