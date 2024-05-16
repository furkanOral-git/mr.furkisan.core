package com.mrfurkisan.core.security.authorization;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.mrfurkisan.core.contracts.abstracts.RequestTypesEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class RolePrototype {

    private List<DomainName> names;
    private AccessLevel level;
    private List<RequestTypesEnum> actions;
    private final String id;

    public RolePrototype() {

        this.actions = new ArrayList<RequestTypesEnum>();
        this.names = new ArrayList<DomainName>();
        this.id =  UUID.randomUUID().toString();
    }

    public List<DomainName> GetDomains() {
        return this.names;
    }

    public List<RequestTypesEnum> GetActions() {
        return this.actions;
    }

    public void AddDomain(DomainName name) {

        this.names.add(name);
    }

    public void AddAction(RequestTypesEnum action) {
        this.actions.add(action);
    }

}
