package com.mrfurkisan.core.infrastructure.persistence.concrete;

import com.mrfurkisan.core.application.auth.IRoleEntityRepository;
import com.mrfurkisan.core.infrastructure.persistence.BaseJpaRepository;
import com.mrfurkisan.core.security.authorization.RoleEntity;

import jakarta.persistence.EntityManager;

public class RoleEntityJpaRepository extends BaseJpaRepository<RoleEntity> implements IRoleEntityRepository{

    public RoleEntityJpaRepository(EntityManager manager, Class<RoleEntity> type) {
        super(manager, type);
        
    }

}
