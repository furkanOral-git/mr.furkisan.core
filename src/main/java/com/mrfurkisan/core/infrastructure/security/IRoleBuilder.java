package com.mrfurkisan.core.infrastructure.security;

import com.mrfurkisan.core.contracts.abstracts.RequestType;
import com.mrfurkisan.core.security.authorization.AccessLevel;
import com.mrfurkisan.core.security.authorization.DomainName;

public interface IRoleBuilder{
    
    public IRoleBuilder AddAction(RequestType action);
    public IRoleBuilder AddAllActions();
    public IRoleBuilder SetDomain(DomainName domain);
    public IRoleBuilder SetAccessLevel(AccessLevel level);
    public void BuildRole();
}
