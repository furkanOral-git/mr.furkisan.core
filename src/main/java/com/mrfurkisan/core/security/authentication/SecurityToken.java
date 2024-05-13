package com.mrfurkisan.core.security.authentication;

public class SecurityToken implements ISecurityToken {
    private String __uniqueId;

    public SecurityToken(String uniqueId) {
        super();
        this.__uniqueId = uniqueId;
    }
   
    @Override
    public String GetSecurityToken() {
        return this.__uniqueId;
    }
}
