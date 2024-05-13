package com.mrfurkisan.core.infrastructure.persistence;

import com.mrfurkisan.core.application.auth.ITokenRepository;
import com.mrfurkisan.core.security.authentication.SecurityTokenEntity;

import jakarta.persistence.EntityManager;

public class TokenJpaRepository extends CustomJpaRepository<SecurityTokenEntity, String> implements ITokenRepository {

    public TokenJpaRepository(EntityManager manager, Class<SecurityTokenEntity> type) {
        super(manager, type);

    }

}
