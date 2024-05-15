package com.mrfurkisan.core.infrastructure.security;

import com.mrfurkisan.core.contracts.abstracts.RequestTypesEnum;
import com.mrfurkisan.core.security.authorization.DomainName;
import com.mrfurkisan.core.security.authorization.RolePrototype;

class RoleCreator implements IRoleBuilder {

    private IAuthorizationBuilder __builder;
    private static RoleCreator __instance;
    private RolePrototype __prototype;

    private RoleCreator(IAuthorizationBuilder builder) {
        super();
        this.__builder = builder;
    }

    public static IRoleBuilder GetInstance(IAuthorizationBuilder builder) {

        if (__instance == null) {

            __instance = new RoleCreator(builder);
        }
        return __instance;
    }

    public void SetProto(RolePrototype prototype) {
        this.__prototype = prototype;
    }

    public RolePrototype GetProto() {
        return this.__prototype;
    }

    @Override
    public IRoleBuilder AddDomain(DomainName domain) {

        this.__prototype.AddDomain(domain);
        return this;
    }

    @Override
    public IRoleBuilder AddAction(RequestTypesEnum action) {

        this.__prototype.AddAction(action);
        return this;
    }

    @Override
    public IRoleBuilder AddAllActions() {


        this.__prototype.AddAction(RequestTypesEnum.DELETE);
        this.__prototype.AddAction(RequestTypesEnum.GET);
        this.__prototype.AddAction(RequestTypesEnum.POST);
        this.__prototype.AddAction(RequestTypesEnum.PUT);
        return this;
    }

    @Override
    public IAuthorizationBuilder BuildRole() {

        var manager = (AuthorizationManager) __builder;
        manager.Create(this.__prototype);
        return manager;
    }
}
