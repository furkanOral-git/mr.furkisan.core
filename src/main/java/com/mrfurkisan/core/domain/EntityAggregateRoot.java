package com.mrfurkisan.core.domain;

import com.mrfurkisan.core.domain.functional.IVoidFunctionalInterface;
import com.mrfurkisan.core.domain.interfaces.IAggregateRootItem;
import com.mrfurkisan.core.domain.interfaces.IEntityAggregateRoot;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public abstract class EntityAggregateRoot<TEntityAggregateRoot extends IEntityAggregateRoot, TItem extends IAggregateRootItem>
        implements IEntityAggregateRoot {

    private final TEntityAggregateRoot entity;
    private List<TItem> aggregate;

    public EntityAggregateRoot(TEntityAggregateRoot entity) {

        super();
        this.entity = entity;
    }

    public void AddToRoot(TItem entity) {

        this.aggregate.add(entity);

    }

    public void ForEach(IVoidFunctionalInterface<TItem> callBack) {

        for (TItem tEntity : this.aggregate) {

            callBack.build(tEntity);
        }

    }

    public void RemoveFromRoot(TItem entity) {

        this.aggregate.remove(entity);
    }

    public Boolean IsExist(TItem entity) {
        return this.aggregate.contains(entity);
    }

}
