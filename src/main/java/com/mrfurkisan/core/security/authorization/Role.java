package com.mrfurkisan.core.security.authorization;


import com.mrfurkisan.core.contracts.abstracts.RequestType;
import com.mrfurkisan.core.domain.AggregateRoot;
import com.mrfurkisan.core.domain.interfaces.IEntityAggregateRoot;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class Role extends AggregateRoot<RequestType, String> implements IEntityAggregateRoot {

    private AccessLevel access_level;
    private DomainName domain_name;

    public Role(RolePrototype proto) {

        super(proto.getId());
        this.access_level = proto.getLevel();
        this.domain_name = proto.getDomain_name();

    }

    public Role(RoleEntity entity) {

        super(entity.getRole_id());
        this.access_level = entity.getAccess_level();
        this.domain_name = entity.getDomain_name();

    }

    @Override
    public Boolean IsExist(RequestType entity) {
        return this.getAggregate().contains(entity);
    }

}
