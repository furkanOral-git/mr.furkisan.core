package com.mrfurkisan.core.domain;

import java.util.List;

import com.mrfurkisan.core.domain.functional.IVoidFunctionalInterface;

import com.mrfurkisan.core.domain.interfaces.IAggregateRoot;
import com.mrfurkisan.core.domain.interfaces.IAggregateRootItem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AggregateRoot<TItem extends IAggregateRootItem, TId>
        implements IAggregateRoot<TItem, TId> {

    private List<TItem> aggregate;
    private TId id;

    public AggregateRoot() {

        super();

    }

    @Override
    public void AddToRoot(TItem entity) {

        this.aggregate.add(entity);

    }

    @Override
    public void ForEach(IVoidFunctionalInterface<TItem> callBack) {

        for (TItem tEntity : this.aggregate) {

            callBack.build(tEntity);
        }

    }

    @Override
    public void RemoveFromRoot(TItem entity) {

        this.aggregate.remove(entity);
    }

    @Override
    public Boolean IsExist(TItem entity) {
        return this.aggregate.contains(entity);
    }

}
