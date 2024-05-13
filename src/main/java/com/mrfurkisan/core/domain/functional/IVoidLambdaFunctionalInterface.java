package com.mrfurkisan.core.domain.functional;

import com.mrfurkisan.core.domain.interfaces.IEntity;

public interface IVoidLambdaFunctionalInterface<TEntity extends IEntity> {
    public void run(TEntity entity);
}
