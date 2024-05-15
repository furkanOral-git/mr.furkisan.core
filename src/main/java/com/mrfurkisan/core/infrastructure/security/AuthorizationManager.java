package com.mrfurkisan.core.infrastructure.security;

import java.util.List;

import com.mrfurkisan.core.domain.functional.IInMemoryFunctionalInterface;
import com.mrfurkisan.core.infrastructure.persistence.concrete.RoleInMemoryRepository;
import com.mrfurkisan.core.security.authorization.AccessLevel;
import com.mrfurkisan.core.security.authorization.Role;
import com.mrfurkisan.core.security.authorization.RolePrototype;

class AuthorizationManager implements IAuthorizationBuilder {

    private RoleInMemoryRepository __roleRepo;

    private static AuthorizationManager __instance;

    private AuthorizationManager() {

        this.__roleRepo = RoleInMemoryRepository.GetRepo();
    }

    public static AuthorizationManager GetInstance() {

        if (__instance == null) {
            __instance = new AuthorizationManager();
        }
        return __instance;
    }

    @Override
    public IRoleBuilder RoleBuilder(AccessLevel level) {

        var creator = (RoleCreator) RoleCreator.GetInstance(this);
        creator.SetProto(new RolePrototype(level));
        return creator;
    }

    public Role GetBy(IInMemoryFunctionalInterface<Role> filter) {

        return this.__roleRepo.GetBy(filter);
    }

    public List<Role> GetAll() {

        return this.__roleRepo.GetAll();

    }

    public List<Role> GetAllBy(IInMemoryFunctionalInterface<Role> filter) {
        return this.__roleRepo.GetAllBy(filter);
    }

    public void Update(Role role) {
        this.__roleRepo.Update(role);
    }

    public void Delete(Role role) {

        this.__roleRepo.Delete(role);
    }

    public void Create(RolePrototype protoType) {
        
        var id = (int)Math.floor(Math.random() * 4 / 3);
        protoType.SetId(id);
        this.__roleRepo.Add(new Role(protoType));
    }

}
