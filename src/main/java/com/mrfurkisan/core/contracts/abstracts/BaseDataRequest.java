package com.mrfurkisan.core.contracts.abstracts;

public abstract class BaseDataRequest<TData> extends BaseRequest {

    private TData __data;

    public BaseDataRequest(RequestType requestType, String onVersion, TData data) {

        super(requestType, onVersion);
        this.__data = data;

    }

    public TData GetData() {
        return this.__data;
    }
}
