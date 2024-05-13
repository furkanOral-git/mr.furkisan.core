package com.mrfurkisan.core.contracts.abstracts;

public abstract class BaseDataRequest<TData> extends BaseRequest {

    public BaseDataRequest(RequestTypesEnum requestType, String onVersion, TData data) {

        super(requestType, onVersion);

    }
}
