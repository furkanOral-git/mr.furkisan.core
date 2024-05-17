package com.mrfurkisan.core.security.authorization;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.mrfurkisan.core.contracts.abstracts.RequestType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class RolePrototype {

    private AccessLevel level;
    private final List<RequestType> actions;
    private final String id;
    private DomainName domain_name;

    public RolePrototype() {

        this.actions = new ArrayList<RequestType>();
        this.id = UUID.randomUUID().toString();
    }
    
    public void addAction(RequestType action) {
        this.actions.add(action);
    }

}
