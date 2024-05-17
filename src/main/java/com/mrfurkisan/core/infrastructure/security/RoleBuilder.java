package com.mrfurkisan.core.infrastructure.security;

import java.util.ArrayList;

import com.mrfurkisan.core.application.auth.IAuthorizationService;
import com.mrfurkisan.core.contracts.abstracts.RequestType;
import com.mrfurkisan.core.security.authorization.AccessLevel;
import com.mrfurkisan.core.security.authorization.DomainName;
import com.mrfurkisan.core.security.authorization.Role;
import com.mrfurkisan.core.security.authorization.RoleEntity;
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
    
    @Override
    public IRoleBuilder AddAction(RequestType action) {

        this.__prototype.addAction(action);
        return this;
    }
    
    @Override
    public IRoleBuilder AddAllActions() {

        this.__prototype.addAction(RequestType.DELETE);
        this.__prototype.addAction(RequestType.GET);
        this.__prototype.addAction(RequestType.POST);
        this.__prototype.addAction(RequestType.PUT);
        return this;
    }

    // Bu fonksiyon sadece uygulama ayarları sırasında kullanılmalı!
    // Runtime'da yetkilendirme ve rol oluşturmak için AuthorizationService
    // CreateRole kullanılmalı
    @Override
    public void BuildRole() {

        var result = this.__service.GetRoleEntityByDomain(this.__prototype.getDomain_name());
        if (result == null) {

            RoleEntity entityRole = new RoleEntity();
            entityRole.setAccess_level(this.__prototype.getLevel());
            entityRole.setDomain_name(this.__prototype.getDomain_name());
            entityRole.setRole_id(this.__prototype.getId());
            this.__service.AddRoleEntityToDb(entityRole);

        }

        Role role = new Role(this.__prototype);
        role.setAggregate((ArrayList<RequestType>) this.__prototype.getActions());
        this.__service.AddRoleToInMemory(role);

    }
    @Override
    public IRoleBuilder SetAccessLevel(AccessLevel level) {

        this.__prototype.setLevel(level);
        return this;
    }

    @Override
    public IRoleBuilder SetDomain(DomainName domain) {
        
        this.__prototype.setDomain_name(domain);
        return this;
    }

}
