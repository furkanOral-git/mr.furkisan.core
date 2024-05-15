package com.mrfurkisan.core.infrastructure.security;

import com.mrfurkisan.core.security.authorization.AccessLevel;

public interface IAuthorizationBuilder {

    public IRoleBuilder RoleBuilder(AccessLevel level);
   
}
