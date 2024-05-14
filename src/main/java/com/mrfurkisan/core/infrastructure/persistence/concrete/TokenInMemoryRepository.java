package com.mrfurkisan.core.infrastructure.persistence.concrete;

import com.mrfurkisan.core.application.auth.ITokenRepository;
import com.mrfurkisan.core.infrastructure.persistence.BaseInMemoryRepository;
import com.mrfurkisan.core.security.authentication.SecurityTokenEntity;

public class TokenInMemoryRepository extends BaseInMemoryRepository<SecurityTokenEntity,String> implements ITokenRepository {
    
    //Verileri bellekte tuttuğu için singleton patterni uyguladım.
    private static TokenInMemoryRepository __instance;
    private TokenInMemoryRepository() {
        super();
    }
    public static TokenInMemoryRepository GetRepo(){
        
        if(__instance == null){
            __instance = new TokenInMemoryRepository();
        }
        return __instance;
    }
    
}
