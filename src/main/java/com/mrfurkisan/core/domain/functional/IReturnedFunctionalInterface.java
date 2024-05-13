package com.mrfurkisan.core.domain.functional;

import com.mrfurkisan.core.domain.interfaces.IEntity;

public interface IReturnedFunctionalInterface<TEntity extends IEntity, TReturnType> {
    public TReturnType run(TEntity entity);
}
