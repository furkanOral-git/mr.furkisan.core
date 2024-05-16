package com.mrfurkisan.core.infrastructure.security;

import com.mrfurkisan.core.contracts.abstracts.RequestTypesEnum;
import com.mrfurkisan.core.security.authorization.AccessLevel;
import com.mrfurkisan.core.security.authorization.DomainName;

public interface IRoleBuilder{
    
    public IRoleBuilder AddDomain(DomainName domain);
    public IRoleBuilder AddAction(RequestTypesEnum action);
    public IRoleBuilder AddAllActions();
    public IRoleBuilder SetAccessLevel(AccessLevel level);
    public void BuildRole();
}
