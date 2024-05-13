package com.mrfurkisan.core.contracts.abstracts;

import java.io.Serializable;

public abstract class BaseResponse implements Serializable {
    
    
    private String __message;
    private Boolean __success;

    public BaseResponse( Boolean success, String message) {
        super();
        
        this.__success = success;
        this.__message = message;
    }

    public String GetMessage() {
        return this.__message;
    }

    public Boolean GetSuccess() {
        return this.__success;
    }
    
}
