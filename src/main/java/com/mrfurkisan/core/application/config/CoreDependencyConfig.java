package com.mrfurkisan.core.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mrfurkisan.core.application.auth.ISecurityCenter;
import com.mrfurkisan.core.application.auth.ITokenService;
import com.mrfurkisan.core.application.auth.IUserService;
import com.mrfurkisan.core.infrastructure.security.CoreSecurityCenter;

@Configuration
public abstract class CoreDependencyConfig implements ICanAddCoreSecurityDependency {

    // Güvenlik merkezi için soyutlama ile injection eklendi.
    @Bean
    public ISecurityCenter getSecurityCenter(ITokenService tokenService, IUserService userService) {
        return new CoreSecurityCenter(tokenService, userService);
    }

}
