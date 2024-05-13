package com.mrfurkisan.core.application.config;

import com.mrfurkisan.core.application.auth.ISecurityCenter;
import com.mrfurkisan.core.application.auth.ITokenService;
import com.mrfurkisan.core.application.auth.IUserService;
import com.mrfurkisan.core.infrastructure.persistence.TokenJpaRepository;
import com.mrfurkisan.core.infrastructure.persistence.UserJpaRepository;

public interface ICanAddCoreSecurityDependency {

    public ITokenService getTokenServiceWithJpa(TokenJpaRepository repository);

    public IUserService getUserServiceWithJpa(UserJpaRepository repository);

    public ISecurityCenter getSecurityCenter(ITokenService tokenService, IUserService userService);

}
