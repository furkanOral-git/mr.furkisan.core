package com.mrfurkisan.core.infrastructure.services;

import java.util.UUID;

import com.mrfurkisan.core.application.auth.ITokenRepository;
import com.mrfurkisan.core.application.auth.ITokenService;
import com.mrfurkisan.core.domain.functional.IJpaFunctionalInterface;
import com.mrfurkisan.core.infrastructure.persistence.concrete.*;
import com.mrfurkisan.core.security.authentication.*;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public final class TokenService<TRepository extends ITokenRepository> implements ITokenService {

    private TRepository __repository;

    public TokenService(TRepository repository) {
        super();
        this.__repository = repository;
    }

    @Override
    public SecurityTokenEntity GetEntityByTokenId(String token) {

        SecurityTokenEntity entity = null;
        if (__repository instanceof TokenInMemoryRepository) {

            TokenInMemoryRepository castedRepo = (TokenInMemoryRepository) this.__repository;
            entity = castedRepo.GetBy((t) -> t.GetId() == token);
        }
        if (__repository instanceof TokenJpaRepository) {

            TokenJpaRepository castedRepo = (TokenJpaRepository) this.__repository;

            IJpaFunctionalInterface<SecurityTokenEntity, CriteriaQuery<SecurityTokenEntity>> filter = (
                    CriteriaBuilder builder) -> {

                CriteriaQuery<SecurityTokenEntity> query = builder.createQuery(SecurityTokenEntity.class);
                Root<SecurityTokenEntity> root = query.from(SecurityTokenEntity.class);
                Predicate predicate = builder.equal(root.get("id"), token);

                query.where(predicate);

                return query;
            };
            entity = castedRepo.GetBy(filter);
        }
        return entity;

    }

    @Override
    public SecurityToken CreateToken(User user) {

        String rondomId = UUID.randomUUID().toString().replace("-", "");
        String macAdressRondom = UUID.randomUUID().toString().replace("-", "");
        var securityTokenEntity = new SecurityTokenEntity(rondomId, macAdressRondom, user.GetRoleId(),
                (int) user.GetId());
        // CQRS soyutlamasıyla veritabanı farklılıklarından dolayı, işlemlerin de
        // farklılaşmasına çözüm olarak kullanılabilir.
        // Fakat ben şuanda kullanmıcam.

        if (this.__repository instanceof TokenInMemoryRepository) {

            TokenInMemoryRepository castedRepo = (TokenInMemoryRepository) this.__repository;
            castedRepo.Add(securityTokenEntity);
        }
        if (this.__repository instanceof TokenJpaRepository) {
            TokenJpaRepository castedRepo = (TokenJpaRepository) this.__repository;
            castedRepo.Add(securityTokenEntity);
        }

        return new SecurityToken(rondomId);
    }

    @Override
    public void DeleteToken(SecurityTokenEntity tokenEntity) {

        if (this.__repository instanceof TokenInMemoryRepository) {
            TokenInMemoryRepository castedRepo = (TokenInMemoryRepository) this.__repository;
            castedRepo.Delete(tokenEntity);
        }
        if (this.__repository instanceof TokenJpaRepository) {
            TokenJpaRepository castedRepo = (TokenJpaRepository) this.__repository;

            IJpaFunctionalInterface<SecurityTokenEntity, CriteriaDelete<SecurityTokenEntity>> filter = (
                    CriteriaBuilder builder) -> {

                CriteriaDelete<SecurityTokenEntity> delete = builder.createCriteriaDelete(SecurityTokenEntity.class);

                Root<SecurityTokenEntity> table = delete.from(SecurityTokenEntity.class);

                delete.where(builder.equal(table.get("id"), tokenEntity.GetId()));
                
                return delete;

            };

            castedRepo.Delete(filter);
        }

    }

}
