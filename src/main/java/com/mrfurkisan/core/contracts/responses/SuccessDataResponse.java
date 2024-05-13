package com.mrfurkisan.core.contracts.responses;


import com.mrfurkisan.core.contracts.abstracts.BaseDataResponse;

public class SuccessDataResponse<TData> extends BaseDataResponse<TData> {

    public SuccessDataResponse(String message, TData data) {
        super(true, message, data);
    }
}
