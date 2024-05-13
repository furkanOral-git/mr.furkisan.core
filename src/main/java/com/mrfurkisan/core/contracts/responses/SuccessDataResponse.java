package com.mrfurkisan.core.contracts.responses;

import org.springframework.http.HttpStatusCode;

import com.mrfurkisan.core.contracts.abstracts.BaseDataResponse;

public class SuccessDataResponse<TData> extends BaseDataResponse<TData> {

    public SuccessDataResponse(HttpStatusCode statusCode, String message, TData data) {
        super(statusCode, true, message, data);
    }
}
