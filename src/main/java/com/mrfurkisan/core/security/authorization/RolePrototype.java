package com.mrfurkisan.core.security.authorization;

import java.util.ArrayList;
import java.util.List;

import com.mrfurkisan.core.contracts.abstracts.RequestTypesEnum;

public class RolePrototype {

    private List<DomainName> __names;
    private AccessLevel __level;
    private List<RequestTypesEnum> __actions;
    private int __id;

    public RolePrototype(AccessLevel level) {

        this.__actions = new ArrayList<RequestTypesEnum>();
        this.__names = new ArrayList<DomainName>();
        this.__level = level;
    }

    public List<DomainName> GetDomains() {
        return this.__names;
    }

    public List<RequestTypesEnum> GetActions() {
        return this.__actions;
    }

    public int GetId() {

        return this.__id;
    }

    public void SetId(int id) {
        this.__id = id;
    }

    public AccessLevel GetLevel() {
        return this.__level;
    }

    public void AddDomain(DomainName name) {

        this.__names.add(name);
    }

    public void AddAction(RequestTypesEnum action) {
        this.__actions.add(action);
    }

}
