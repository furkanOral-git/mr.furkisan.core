package com.mrfurkisan.core.domain;

import java.util.ArrayList;


import com.mrfurkisan.core.domain.functional.IVoidFunctionalInterface;

import com.mrfurkisan.core.domain.interfaces.IAggregateRoot;

import com.mrfurkisan.core.domain.interfaces.IEntity;

public abstract class AggregateRoot<TEntity extends IEntity, TId> implements IAggregateRoot<TEntity, TId> {

    private ArrayList<TEntity> __aggregate;
    private TId __id;

    public AggregateRoot(TId id) {
        super();
        this.__aggregate = new ArrayList<TEntity>();
        this.__id = id;
    }

    @Override
    public void AddToRoot(TEntity entity) {

        this.__aggregate.add(entity);

    }

    @Override
    public void ForEach(IVoidFunctionalInterface<TEntity> callBack) {

        for (TEntity tEntity : this.__aggregate) {

            callBack.build(tEntity);
        }

    }

    @Override
    public void RemoveFromRoot(TEntity entity) {

        this.__aggregate.remove(entity);
    }

    

}
