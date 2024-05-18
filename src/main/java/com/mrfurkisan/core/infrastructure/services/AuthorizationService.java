package com.mrfurkisan.core.infrastructure.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mrfurkisan.core.application.auth.IAuthorizationService;
import com.mrfurkisan.core.application.auth.IRoleEntityRepository;
import com.mrfurkisan.core.application.auth.IRoleRepository;
import com.mrfurkisan.core.contracts.abstracts.RequestType;
import com.mrfurkisan.core.domain.functional.IJpaFunctionalInterface;
import com.mrfurkisan.core.infrastructure.persistence.concrete.RoleEntityJpaRepository;
import com.mrfurkisan.core.infrastructure.persistence.concrete.RoleInMemoryRepository;
import com.mrfurkisan.core.infrastructure.security.IRoleBuilder;
import com.mrfurkisan.core.infrastructure.security.RoleBuilder;
import com.mrfurkisan.core.security.authorization.AccessLevel;
import com.mrfurkisan.core.security.authorization.DomainName;
import com.mrfurkisan.core.security.authorization.Role;
import com.mrfurkisan.core.security.authorization.RoleEntity;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;


public class AuthorizationService implements IAuthorizationService {

    private RoleEntityJpaRepository __entityRepo;
    private RoleInMemoryRepository __roleRepo;
    
    public AuthorizationService(IRoleEntityRepository entityRepo, IRoleRepository roleRepo) {

        this.__entityRepo = (RoleEntityJpaRepository) entityRepo;
        this.__roleRepo = (RoleInMemoryRepository) roleRepo;
    }

    @Override
    public void AddRoleEntityToDb(RoleEntity role) {
        this.__entityRepo.Add(role);
    }

    public RoleEntity GetById(String id) {

        IJpaFunctionalInterface<RoleEntity, CriteriaQuery<RoleEntity>> filter = (
                CriteriaBuilder builder) -> {

            CriteriaQuery<RoleEntity> query = builder.createQuery(RoleEntity.class);
            Root<RoleEntity> root = query.from(RoleEntity.class);
            Predicate predicate = builder.equal(root.get("role_id"), id);

            query.where(predicate);

            return query;
        };
        return this.__entityRepo.GetBy(filter);

    }

    @Override
    public List<RoleEntity> GetAllRoleEntityByDomain(DomainName name) {

        IJpaFunctionalInterface<RoleEntity, CriteriaQuery<RoleEntity>> filter = (
                CriteriaBuilder builder) -> {

            CriteriaQuery<RoleEntity> query = builder.createQuery(RoleEntity.class);
            Root<RoleEntity> root = query.from(RoleEntity.class);
            Predicate predicate = builder.equal(root.get("domain_name"), name);

            query.where(predicate);

            return query;
        };
        return this.__entityRepo.GetAllBy(filter);
    }

    @Override
    public RoleEntity GetRoleEntityById(String id) {

        IJpaFunctionalInterface<RoleEntity, CriteriaQuery<RoleEntity>> filter = (
                CriteriaBuilder builder) -> {

            CriteriaQuery<RoleEntity> query = builder.createQuery(RoleEntity.class);
            Root<RoleEntity> root = query.from(RoleEntity.class);
            Predicate predicate = builder.equal(root.get("role_id"), id);

            query.where(predicate);

            return query;
        };
        return this.__entityRepo.GetBy(filter);
    }

    @Override
    public List<RoleEntity> GetAllRoleEntity() {

        return this.__entityRepo.GetAll();
    }
    @Override
    public RoleEntity GetRoleEntityByDomain(DomainName name) {

        IJpaFunctionalInterface<RoleEntity, CriteriaQuery<RoleEntity>> filter = (
                CriteriaBuilder builder) -> {

            CriteriaQuery<RoleEntity> query = builder.createQuery(RoleEntity.class);
            Root<RoleEntity> root = query.from(RoleEntity.class);
            Predicate predicate = builder.equal(root.get("domain_name"), name);

            query.where(predicate);

            return query;
        };
        return this.__entityRepo.GetBy(filter);

    }

    @Override
    public IRoleBuilder GetRoleBuilder() {
        return new RoleBuilder(this);
    }

    @Override
    public void UpdateRoleEntityDomain(String id, DomainName name) {

        IJpaFunctionalInterface<RoleEntity, CriteriaUpdate<RoleEntity>> filter = (CriteriaBuilder builder) -> {

            CriteriaUpdate<RoleEntity> update = builder.createCriteriaUpdate(RoleEntity.class);
            Root<RoleEntity> table = update.from(RoleEntity.class);

            update.set(table.get("domain_name"), name);

            return update;
        };
        this.__entityRepo.UpdateBy(filter);

    }

    @Override
    public void UpdateRoleEntityAccess(String id, AccessLevel name) {

        IJpaFunctionalInterface<RoleEntity, CriteriaUpdate<RoleEntity>> filter = (CriteriaBuilder builder) -> {

            CriteriaUpdate<RoleEntity> update = builder.createCriteriaUpdate(RoleEntity.class);
            Root<RoleEntity> table = update.from(RoleEntity.class);

            update.set(table.get("access_level"), name);

            return update;
        };
        this.__entityRepo.UpdateBy(filter);

    }

    @Override
    public void DeleteRoleEntity(String id) {

        IJpaFunctionalInterface<RoleEntity, CriteriaDelete<RoleEntity>> filter = (CriteriaBuilder builder) -> {

            CriteriaDelete<RoleEntity> delete = builder.createCriteriaDelete(RoleEntity.class);
            Root<RoleEntity> table = delete.from(RoleEntity.class);

            delete.where(builder.equal(table.get("role_id"), id));
            return delete;
        };
        this.__entityRepo.DeleteBy(filter);
    }

    @Override
    public void AddPermissionToRole(Role role, RequestType requestType) {

        Role aggregateRole = this.__roleRepo.GetBy((r) -> r.getId().equals(role.getId()));
        aggregateRole.AddToRoot(requestType);

    }

    @Override
    public void RemovePermissionFromRole(Role role, RequestType requestType) {

        Role aggregateRole = this.__roleRepo.GetBy((r) -> r.getId().equals(role.getId()));
        aggregateRole.RemoveFromRoot(requestType);

    }

    @Override
    public void AddRoleToInMemory(Role role) {
        this.__roleRepo.Add(role);
    }

    @Override
    public List<Role> GetAllRoleByAccessLevel(AccessLevel level) {

        return this.__roleRepo.GetAllBy((r) -> r.getAccess_level().equals(level));
    }

    @Override
    public List<Role> GetAllRoleByDomain(DomainName name) {

        return this.__roleRepo.GetAllBy((r) -> r.getDomain_name().equals(name));
    }

    @Override
    public Role GetRoleById(String id) {

        return this.__roleRepo.GetBy((r) -> r.getId().equals(id));
    }

    @Override
    public Role GetRoleByDomain(DomainName name) {

        return this.__roleRepo.GetBy((r) -> r.getDomain_name().equals(name));
    }

}
