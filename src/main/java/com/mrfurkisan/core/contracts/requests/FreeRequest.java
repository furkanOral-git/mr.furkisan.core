package com.mrfurkisan.core.contracts.requests;

import com.mrfurkisan.core.contracts.abstracts.BaseRequest;
import com.mrfurkisan.core.contracts.abstracts.RequestTypesEnum;

public class FreeRequest extends BaseRequest{
    
    public FreeRequest(RequestTypesEnum requestType, String onVersion) {
        super(requestType,onVersion);
    }
}
