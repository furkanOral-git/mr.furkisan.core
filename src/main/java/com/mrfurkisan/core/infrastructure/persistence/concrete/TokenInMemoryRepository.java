package com.mrfurkisan.core.infrastructure.persistence.concrete;

import com.mrfurkisan.core.application.auth.ITokenRepository;
import com.mrfurkisan.core.infrastructure.persistence.BaseInMemoryRepository;
import com.mrfurkisan.core.security.authentication.SecurityTokenEntity;

public class TokenInMemoryRepository extends BaseInMemoryRepository<SecurityTokenEntity,String> implements ITokenRepository {
    
    public TokenInMemoryRepository() {
        super();
    }
   
    
}
