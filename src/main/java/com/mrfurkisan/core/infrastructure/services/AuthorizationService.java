package com.mrfurkisan.core.infrastructure.services;

import java.util.List;

import com.mrfurkisan.core.application.auth.IAuthorizationService;
import com.mrfurkisan.core.application.auth.IRoleRepository;
import com.mrfurkisan.core.domain.functional.IInMemoryFunctionalInterface;
import com.mrfurkisan.core.infrastructure.persistence.concrete.RoleInMemoryRepository;
import com.mrfurkisan.core.infrastructure.security.IRoleBuilder;
import com.mrfurkisan.core.infrastructure.security.RoleBuilder;
import com.mrfurkisan.core.security.authorization.Role;

public class AuthorizationService implements IAuthorizationService {

    private RoleInMemoryRepository __roleRepo;

    public AuthorizationService(IRoleRepository repo) {

        this.__roleRepo = (RoleInMemoryRepository) repo;
        

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

        IInMemoryFunctionalInterface<Role> filter = (r) -> {
            return r.getRole_id().equals(role.getRole_id());
        };

        this.__roleRepo.Update(filter, role);
    }

    public void Delete(Role role) {

        this.__roleRepo.Delete(role);
    }

    public void Add(Role role) {

        this.__roleRepo.Add(role);
    }

    @Override
    public IRoleBuilder GetRoleBuilder() {
        return new RoleBuilder(this);
    }
    
}
