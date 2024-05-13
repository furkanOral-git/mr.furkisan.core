package com.mrfurkisan.core.contracts.requests;

import com.mrfurkisan.core.contracts.abstracts.BaseRequest;
import com.mrfurkisan.core.contracts.abstracts.RequestTypesEnum;
import com.mrfurkisan.core.security.authentication.ISecurityToken;

public class SecureRequest extends BaseRequest {
    
    private final ISecurityToken __token;

    public SecureRequest(RequestTypesEnum requestType, String onVersion,ISecurityToken token) {
        super(requestType, onVersion);
        this.__token = token;
    }
    public ISecurityToken GetToken(){
        return this.__token;
    }
}
