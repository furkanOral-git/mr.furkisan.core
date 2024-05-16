package com.mrfurkisan.core.infrastructure.persistence.concrete;

import com.mrfurkisan.core.application.auth.IRoleRepository;
import com.mrfurkisan.core.infrastructure.persistence.BaseJpaRepository;
import com.mrfurkisan.core.security.authorization.Role;

import jakarta.persistence.EntityManager;

public class RoleJpaRepository extends BaseJpaRepository<Role> implements IRoleRepository{

    public RoleJpaRepository(EntityManager manager, Class<Role> type) {
        super(manager, type);
        
    }

}
