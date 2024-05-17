package com.mrfurkisan.core.contracts.requests;

import com.mrfurkisan.core.contracts.abstracts.BaseDataRequest;
import com.mrfurkisan.core.contracts.abstracts.RequestType;

public class FreeDataRequest<TData> extends BaseDataRequest<TData> {


    public FreeDataRequest(RequestType requestType, String onVersion, TData data) {
        super(requestType, onVersion, data);

    }
   
}
