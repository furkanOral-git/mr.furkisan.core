package com.mrfurkisan.core.security.authorization;

public class Role {
    
    private String __domains;

    public Role(int RoleId, AccessLevel AccessLevel) {
        super();
        this.__domains = "";
    }

    public boolean IsExistDomain(String name) {

        return this.__domains.contains(name);

    }

    public void AddDomain(String name) {

        if (this.__domains.contains(name)) {
            return;
        }
        this.__domains = this.__domains.concat(".".concat(name));
    }
}
