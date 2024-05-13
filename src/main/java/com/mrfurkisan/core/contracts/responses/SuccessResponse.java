package com.mrfurkisan.core.contracts.responses;

import org.springframework.http.HttpStatusCode;

import com.mrfurkisan.core.contracts.abstracts.BaseResponse;

public class SuccessResponse extends BaseResponse {

    public SuccessResponse(HttpStatusCode statusCode, String message) {
        super(statusCode, true, message);
    }
}
