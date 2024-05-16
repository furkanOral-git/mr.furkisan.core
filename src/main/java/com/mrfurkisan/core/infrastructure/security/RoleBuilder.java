package com.mrfurkisan.core.infrastructure.security;

import com.mrfurkisan.core.application.auth.IAuthorizationService;
import com.mrfurkisan.core.contracts.abstracts.RequestTypesEnum;
import com.mrfurkisan.core.infrastructure.services.AuthorizationService;
import com.mrfurkisan.core.security.authorization.AccessLevel;
import com.mrfurkisan.core.security.authorization.DomainName;
import com.mrfurkisan.core.security.authorization.Role;
import com.mrfurkisan.core.security.authorization.RolePrototype;

public class RoleBuilder implements IRoleBuilder {

    private IAuthorizationService __service;
    private final RolePrototype __prototype;

    public RoleBuilder(IAuthorizationService service) {
        
        super();
        this.__service = service;
        this.__prototype = new RolePrototype();
    }

    public RolePrototype GetProto() {
        return this.__prototype;
    }

   
    public IRoleBuilder AddDomain(DomainName domain) {

        this.__prototype.AddDomain(domain);
        return this;
    }

   
    public IRoleBuilder AddAction(RequestTypesEnum action) {

        this.__prototype.AddAction(action);
        return this;
    }

    
    public IRoleBuilder AddAllActions() {

        this.__prototype.AddAction(RequestTypesEnum.DELETE);
        this.__prototype.AddAction(RequestTypesEnum.GET);
        this.__prototype.AddAction(RequestTypesEnum.POST);
        this.__prototype.AddAction(RequestTypesEnum.PUT);
        return this;
    }

    
    public void BuildRole() {
    
       var casted = (AuthorizationService)this.__service;
       casted.Add(new Role(this.__prototype));
       
    }

    
    public IRoleBuilder SetAccessLevel(AccessLevel level) {
        
        this.__prototype.setLevel(level);
        return this;
    }
}
