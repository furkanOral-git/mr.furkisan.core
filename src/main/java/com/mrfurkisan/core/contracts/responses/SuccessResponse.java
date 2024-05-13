package com.mrfurkisan.core.contracts.responses;


import com.mrfurkisan.core.contracts.abstracts.BaseResponse;

public class SuccessResponse extends BaseResponse {

    public SuccessResponse( String message) {
        super(true, message);
    }
}
