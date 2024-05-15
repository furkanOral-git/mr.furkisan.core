package com.mrfurkisan.core.security.authorization;

import java.util.ArrayList;
import java.util.List;

import com.mrfurkisan.core.contracts.abstracts.RequestTypesEnum;
import com.mrfurkisan.core.domain.BaseEntity;

public class Role extends BaseEntity<Number>{

    private List<DomainName> __domains;
    private List<RequestTypesEnum> __actions;
    private AccessLevel __accessLevel;
    private int __roleId;

    public Role(RolePrototype proto) {
        
        super();
        this.__domains = proto.GetDomains();
        this.__accessLevel = proto.GetLevel();
        this.__roleId = proto.GetId();
    }

    public boolean IsExistDomain(DomainName name) {

        return this.__domains.contains(name);

    }

    public AccessLevel getLevel() {
        return this.__accessLevel;
    }

    public boolean CanYouDo(RequestTypesEnum action) {

        return this.__actions.contains(action);
    }
    

    @Override
    public Number GetId() {
        
        return this.GetId();
    }
}
