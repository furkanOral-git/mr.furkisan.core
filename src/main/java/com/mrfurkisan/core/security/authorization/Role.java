package com.mrfurkisan.core.security.authorization;

import java.util.ArrayList;
import java.util.List;

public class Role {

    private List<DomainName> __domains;
    private List<AllowedAction> __actions;

    public Role(int RoleId, AccessLevel AccessLevel) {
        super();
        this.__domains = new ArrayList<DomainName>();
    }

    public boolean IsExistDomain(String name) {

        return this.__domains.contains(name);

    }

    public boolean CanYouDo(AllowedAction action) {

        return this.__actions.contains(action);
    }

    public void AddDomain(DomainName domain) {

        if (!this.__domains.contains(domain)) {

            this.__domains.add(domain);
        }

    }

    public void AddAction(AllowedAction action) {
        
        if (!this.__domains.contains(action)) {
            this.__actions.add(action);
        }
    }

    public void RemoveAction(AllowedAction action) {

        this.__actions.remove(action);
    }

    public void RemoveDomain(DomainName domain) {

        this.__domains.remove(domain);
    }
}
