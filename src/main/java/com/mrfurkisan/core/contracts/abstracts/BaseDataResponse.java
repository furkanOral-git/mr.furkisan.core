package com.mrfurkisan.core.contracts.abstracts;


public abstract class BaseDataResponse<TData> extends BaseResponse {
    
    private TData __data;

    public BaseDataResponse(Boolean success, String message, TData data) {
        super(success, message);
        this.__data = data;
    }

    public TData GetData() {
        return this.__data;
    }
}
