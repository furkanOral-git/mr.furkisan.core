package com.mrfurkisan.core.application.repositories;


import com.mrfurkisan.core.domain.interfaces.IEntity;

public interface IInMemoryRepository<TEntity extends IEntity,TId> extends IRepository<TEntity> {
    
    
}
