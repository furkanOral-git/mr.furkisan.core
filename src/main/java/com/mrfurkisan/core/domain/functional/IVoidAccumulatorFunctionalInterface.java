package com.mrfurkisan.core.domain.functional;

import com.mrfurkisan.core.domain.interfaces.IEntity;

public interface IVoidAccumulatorFunctionalInterface<TEntity extends IEntity, TReturnType> {
    public void run(TEntity entity, TReturnType acc);
}
