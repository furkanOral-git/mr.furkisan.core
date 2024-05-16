package com.mrfurkisan.core.security.authorization;

import java.util.List;

import com.mrfurkisan.core.contracts.abstracts.RequestTypesEnum;
import com.mrfurkisan.core.domain.interfaces.IEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public final class Role implements IEntity {

    @Id
    private String role_id;
    
    private List<DomainName> domains;
    private List<RequestTypesEnum> actions;
    private AccessLevel access_level;
    

    public Role(RolePrototype proto) {

        super();
        this.domains = proto.GetDomains();
        this.actions = proto.GetActions();
        this.access_level = proto.getLevel();
        this.role_id = proto.getId();
    }

    public boolean IsExistDomain(DomainName name) {

        return this.domains.contains(name);

    }

    public AccessLevel getLevel() {
        return this.access_level;
    }

    public boolean CanYouDo(RequestTypesEnum action) {

        return this.actions.contains(action);
    }

}
