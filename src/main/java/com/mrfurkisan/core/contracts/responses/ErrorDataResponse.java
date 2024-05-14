package com.mrfurkisan.core.contracts.responses;


import com.mrfurkisan.core.contracts.abstracts.BaseDataResponse;

public class ErrorDataResponse<TData> extends BaseDataResponse<TData> {

    public ErrorDataResponse(String message) {
        
        super( false, message, null);
    }
}
