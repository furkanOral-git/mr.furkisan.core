package com.mrfurkisan.core.application.config;

import org.springframework.beans.factory.annotation.Autowired;

import com.mrfurkisan.core.application.auth.*;


//Ability
public interface ICanAddCoreSecurityDependency {

    public ITokenService getTokenService(ITokenRepository repository);

    public IUserService getUserService(IUserRepository repository);

    public IAuthorizationService getAuthorizationService(@Autowired IRoleRepository repo) ;

    public ISecurityCenter getSecurityCenter(@Autowired ITokenService tokenService, @Autowired IUserService userService,
            @Autowired IAuthorizationService authorizationService);

    public ITokenRepository getTokenRepository();

    public IUserRepository getUserRepository();

    public IRoleRepository getRoleRepository();

    

}
