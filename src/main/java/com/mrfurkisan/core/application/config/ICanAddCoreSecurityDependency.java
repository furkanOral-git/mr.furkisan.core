package com.mrfurkisan.core.application.config;

import com.mrfurkisan.core.application.auth.*;

//Ability
public interface ICanAddCoreSecurityDependency {

    public ITokenService getTokenService(ITokenRepository repository);

    public IUserService getUserService(IUserRepository repository);

    public IAuthorizationService getAuthorizationService(IRoleRepository repo, IRoleEntityRepository entityRepo);

    public ISecurityCenter getSecurityCenter(ITokenService tokenService, IUserService userService,
            IAuthorizationService authorizationService);

    public ITokenRepository getTokenRepository();

    public IUserRepository getUserRepository();

    public IRoleRepository getRoleRepository();

    public IRoleEntityRepository getRoleEntityRepository();

}
