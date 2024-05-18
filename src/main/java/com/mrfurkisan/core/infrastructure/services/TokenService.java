package com.mrfurkisan.core.infrastructure.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

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
        // InMemory database kodları
        if (__repository instanceof TokenInMemoryRepository) {

            TokenInMemoryRepository castedRepo = (TokenInMemoryRepository) this.__repository;
            entity = castedRepo.GetBy((t) -> t.getUnique_id() == token);
        }
        // Jpa database kodları
        if (__repository instanceof TokenJpaRepository) {

            TokenJpaRepository castedRepo = (TokenJpaRepository) this.__repository;

            IJpaFunctionalInterface<SecurityTokenEntity, CriteriaQuery<SecurityTokenEntity>> filter = (
                    CriteriaBuilder builder) -> {

                CriteriaQuery<SecurityTokenEntity> query = builder.createQuery(SecurityTokenEntity.class);
                Root<SecurityTokenEntity> root = query.from(SecurityTokenEntity.class);
                Predicate predicate = builder.equal(root.get("unique_id"), token);

                query.where(predicate);

                return query;
            };
            entity = castedRepo.GetBy(filter);
        }
        return entity;

    }

    @Override
    public SecurityToken CreateToken(int userId, String roleId, String currentMacAddress) {

        String uuid = UUID.randomUUID().toString().replace("-", "");

        var securityTokenEntity = new SecurityTokenEntity();
        securityTokenEntity.setMac_address(currentMacAddress);
        securityTokenEntity.setUnique_id(uuid);
        securityTokenEntity.setRole_id(roleId);
        securityTokenEntity.setUser_id(userId);
        // CQRS kullanımı, veritabanı farklılıklarından dolayı işlemlerin de
        // farklılaşması durumuna çözüm olarak kullanılabilir.
        // Fakat ben şuanda kullanmıcam.

        if (this.__repository instanceof TokenInMemoryRepository) {

            TokenInMemoryRepository castedRepo = (TokenInMemoryRepository) this.__repository;
            castedRepo.Add(securityTokenEntity);
        }
        if (this.__repository instanceof TokenJpaRepository) {

            TokenJpaRepository castedRepo = (TokenJpaRepository) this.__repository;
            castedRepo.Add(securityTokenEntity);
        }

        return new SecurityToken(uuid);
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

                delete.where(builder.equal(table.get("unique_id"), tokenEntity.getUnique_id()));

                return delete;

            };

            castedRepo.DeleteBy(filter);
        }

    }

    @Override
    public void DeleteByUserId(int id) {

        if (this.__repository instanceof TokenInMemoryRepository) {
            TokenInMemoryRepository castedRepo = (TokenInMemoryRepository) this.__repository;
            castedRepo.GetBy((t -> t.getUser_id() == id));
        }
        if (this.__repository instanceof TokenJpaRepository) {

            TokenJpaRepository castedRepo = (TokenJpaRepository) this.__repository;
            IJpaFunctionalInterface<SecurityTokenEntity, CriteriaDelete<SecurityTokenEntity>> filter = (
                    CriteriaBuilder builder) -> {
                CriteriaDelete<SecurityTokenEntity> delete = builder.createCriteriaDelete(SecurityTokenEntity.class);
                Root<SecurityTokenEntity> table = delete.from(SecurityTokenEntity.class);

                delete.where(builder.equal(table.get("user_id"), id));
                return delete;
            };
            castedRepo.DeleteBy(filter);
        }
    }

    @Override
    public SecurityTokenEntity GetEntityByUserId(int id) {
        SecurityTokenEntity entity = null;
        if (this.__repository instanceof TokenInMemoryRepository) {

            TokenInMemoryRepository castedRepo = (TokenInMemoryRepository) this.__repository;
            entity = castedRepo.GetBy((t -> t.getUser_id() == id));
        }
        if (this.__repository instanceof TokenJpaRepository) {

            TokenJpaRepository castedRepo = (TokenJpaRepository) this.__repository;
            IJpaFunctionalInterface<SecurityTokenEntity, CriteriaQuery<SecurityTokenEntity>> filter = (
                    CriteriaBuilder builder) -> {
                CriteriaQuery<SecurityTokenEntity> query = builder.createQuery(SecurityTokenEntity.class);
                Root<SecurityTokenEntity> table = query.from(SecurityTokenEntity.class);

                query.where(builder.equal(table.get("user_id"), id));
                return query;
            };
            entity = castedRepo.GetBy(filter);
        }
        return entity;
    }
    @Override
    public int ValidateTokenAndReturnUserId(String token) {

        var tok = this.GetEntityByTokenId(token);
        if (tok == null) {
            return -1;
        }
        return tok.getUser_id();
    }

}
