package com.mrfurkisan.core.infrastructure.services;


import com.mrfurkisan.core.application.auth.ITokenRepository;
import com.mrfurkisan.core.application.auth.ITokenService;
import com.mrfurkisan.core.contracts.abstracts.BaseDataResponse;
import com.mrfurkisan.core.contracts.abstracts.BaseResponse;
import com.mrfurkisan.core.security.authentication.SecurityToken;
import com.mrfurkisan.core.security.authentication.SecurityTokenEntity;
import com.mrfurkisan.core.security.authentication.User;

public final class TokenService<TRepository extends ITokenRepository> implements ITokenService {
    
    private TRepository __repository;

    public TokenService(TRepository repository) {
        super();
        this.__repository = repository;
    }
    @Override
    public BaseDataResponse<SecurityToken> CreateToken(User user) {
        
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'CreateToken'");
    }

    @Override
    public BaseResponse DeleteToken(SecurityTokenEntity tokenEntity) {
        
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'DeleteToken'");
    }

    @Override
    public BaseResponse ValidateToken(SecurityToken token) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ValidateToken'");
    }

   

    

}
