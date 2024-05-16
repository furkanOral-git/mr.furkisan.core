package com.mrfurkisan.core.infrastructure.persistence.concrete;

import com.mrfurkisan.core.application.auth.IRoleRepository;
import com.mrfurkisan.core.infrastructure.persistence.BaseInMemoryRepository;
import com.mrfurkisan.core.security.authorization.Role;

public class RoleInMemoryRepository extends BaseInMemoryRepository<Role, String> implements IRoleRepository{

    public RoleInMemoryRepository() {
        super();
    }
    
}
