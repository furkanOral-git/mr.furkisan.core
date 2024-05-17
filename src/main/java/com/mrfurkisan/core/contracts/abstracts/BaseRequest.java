package com.mrfurkisan.core.contracts.abstracts;


public abstract class BaseRequest {
    
    private RequestType __requestType;
    private String __onVersion;
    

    public BaseRequest(RequestType requestType, String onVersion) {
        super();
        this.__requestType = requestType;
        this.__onVersion = onVersion;
        
    }

    public RequestType GetRequestType() {
        return this.__requestType;
    }

    public String GetVersionInfo() {
        return this.__onVersion;
    }
    
    
}
