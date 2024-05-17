package com.mrfurkisan.core.contracts.requests;

import com.mrfurkisan.core.contracts.abstracts.BaseRequest;
import com.mrfurkisan.core.contracts.abstracts.RequestType;

public class FreeRequest extends BaseRequest{
    
    public FreeRequest(RequestType requestType, String onVersion) {
        super(requestType,onVersion);
    }
}
