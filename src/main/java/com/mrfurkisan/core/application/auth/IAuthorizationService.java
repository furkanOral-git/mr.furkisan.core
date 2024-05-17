package com.mrfurkisan.core.application.auth;

import java.util.List;

import com.mrfurkisan.core.application.IApplicationService;
import com.mrfurkisan.core.contracts.abstracts.RequestType;
import com.mrfurkisan.core.infrastructure.security.IRoleBuilder;
import com.mrfurkisan.core.security.authorization.AccessLevel;
import com.mrfurkisan.core.security.authorization.DomainName;
import com.mrfurkisan.core.security.authorization.Role;
import com.mrfurkisan.core.security.authorization.RoleEntity;

public interface IAuthorizationService extends IApplicationService {

    public IRoleBuilder GetRoleBuilder();

    public void AddRoleEntityToDb(RoleEntity role);

    public void AddRoleToInMemory(Role role);

    public List<RoleEntity> GetAllRoleEntityByDomain(DomainName name);

    public RoleEntity GetRoleEntityById(String id);

    public RoleEntity GetRoleEntityByDomain(DomainName name);

    public List<RoleEntity> GetAllRoleEntity();

    public void UpdateRoleEntityDomain(String id, DomainName name);

    public void UpdateRoleEntityAccess(String id, AccessLevel name);

    public void DeleteRoleEntity(String id);

    public void AddPermissionToRole(Role role, RequestType requestType);

    public void RemovePermissionFromRole(Role role, RequestType requestType);

    public List<Role> GetAllRoleByAccessLevel(AccessLevel level);

    public List<Role> GetAllRoleByDomain(DomainName name);

    public Role GetRoleById(String id);

    public Role GetRoleByDomain(DomainName name);

}
