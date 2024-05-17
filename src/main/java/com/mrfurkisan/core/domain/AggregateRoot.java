package com.mrfurkisan.core.domain;

import java.util.ArrayList;


import com.mrfurkisan.core.domain.functional.IVoidFunctionalInterface;

import com.mrfurkisan.core.domain.interfaces.IAggregateRoot;
import com.mrfurkisan.core.domain.interfaces.IAggregateRootItem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AggregateRoot<TItem extends IAggregateRootItem, TId> implements IAggregateRoot<TItem, TId> {

    private ArrayList<TItem> aggregate;
    private final TId id;

    public AggregateRoot(TId id) {
        
        super();
        this.aggregate = new ArrayList<TItem>();
        this.id = id;
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

    

}
