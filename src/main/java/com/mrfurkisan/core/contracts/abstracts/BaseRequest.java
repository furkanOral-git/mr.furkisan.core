package com.mrfurkisan.core.contracts.abstracts;


public abstract class BaseRequest {
    
    private RequestTypesEnum __requestType;
    private String __onVersion;
    

    public BaseRequest(RequestTypesEnum requestType, String onVersion) {
        super();
        this.__requestType = requestType;
        this.__onVersion = onVersion;
        
    }

    public RequestTypesEnum GetRequestType() {
        return this.__requestType;
    }

    public String GetVersionInfo() {
        return this.__onVersion;
    }

    
}
