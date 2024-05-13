package com.mrfurkisan.core.domain;

import java.util.ArrayList;

import com.mrfurkisan.core.domain.functional.IVoidAccumulatorFunctionalInterface;
import com.mrfurkisan.core.domain.functional.IVoidLambdaFunctionalInterface;
import com.mrfurkisan.core.domain.interfaces.IAggregateRoot;
import com.mrfurkisan.core.domain.interfaces.IBaseValueObject;
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
    public void ForEach(IVoidLambdaFunctionalInterface<TEntity> callBack) {

        for (TEntity tEntity : this.__aggregate) {

            callBack.run(tEntity);
        }

    }

    @Override
    public void RemoveFromRoot(TEntity entity) {

        this.__aggregate.remove(entity);
    }

    @Override
    public <TReturnType extends IBaseValueObject> TReturnType Accumulate(
            IVoidAccumulatorFunctionalInterface<TEntity, TReturnType> callBack, TReturnType accumulator) {

        for (TEntity tEntity : this.__aggregate) {
            callBack.run(tEntity, accumulator);
        }
        return accumulator;
    }

}
