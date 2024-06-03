package com.mrfurkisan.core.contracts.requests;

import com.mrfurkisan.core.contracts.abstracts.BaseDataRequest;
import com.mrfurkisan.core.contracts.abstracts.RequestType;
import com.mrfurkisan.core.security.authentication.ISecurityToken;

public class SecureDataRequest<TData> extends BaseDataRequest<TData> {

    private final ISecurityToken __token;

    public SecureDataRequest(RequestType requestType, String onVersion, TData data, ISecurityToken token) {
        super(requestType, onVersion, data);
        this.__token = token;
    }

    public ISecurityToken GetToken() {
        return this.__token;
    }
    
}
