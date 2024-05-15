package com.mrfurkisan.core.domain.functional;

import com.mrfurkisan.core.domain.interfaces.IEntity;

@FunctionalInterface
public interface IInMemoryFunctionalInterface<TEntity extends IEntity> extends IFunctionalInterface<Boolean,TEntity>{
    
}
