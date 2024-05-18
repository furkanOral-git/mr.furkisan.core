package com.mrfurkisan.core.security.authorization;

import com.mrfurkisan.core.domain.interfaces.IEntityAggregateRoot;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Roles")
public class RoleEntity implements IEntityAggregateRoot {
    
    @Id
    private String role_id;
    private DomainName domain_name;
    private AccessLevel access_level;
    
}
