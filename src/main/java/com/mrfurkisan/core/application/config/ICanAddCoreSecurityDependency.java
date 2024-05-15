package com.mrfurkisan.core.application.config;


import com.mrfurkisan.core.application.auth.ISecurityCenter;
import com.mrfurkisan.core.application.auth.ITokenRepository;
import com.mrfurkisan.core.application.auth.ITokenService;
import com.mrfurkisan.core.application.auth.IUserRepository;
import com.mrfurkisan.core.application.auth.IUserService;





public interface ICanAddCoreSecurityDependency {

    public ITokenService getTokenService(ITokenRepository repository);

    public IUserService getUserService(IUserRepository repository);

    public ISecurityCenter getSecurityCenter(ITokenService tokenService, IUserService userService);

    public ITokenRepository getTokenRepository();

    public IUserRepository getUserRepository();

    
}
