package com.mrfurkisan.core.application.auth;

import com.mrfurkisan.core.application.repositories.IRepository;
import com.mrfurkisan.core.security.authentication.SecurityTokenEntity;

public interface ITokenRepository extends IRepository<SecurityTokenEntity> {
    
}
