package com.mrfurkisan.core.application.repositories;

import java.util.List;

import com.mrfurkisan.core.domain.functional.IPredicateBuilderFunctionalInterface;
import com.mrfurkisan.core.domain.interfaces.IEntity;

public interface IJpaRepository<TEntity extends IEntity, TId> extends IRepository<TEntity> {

    public void Add(TEntity entity);

    public void Update(TEntity entity);

    public void Delete(TEntity entity);

    public TEntity GetById(TId id);

    public List<TEntity> GetByPredicate(IPredicateBuilderFunctionalInterface<TEntity> predicate);

    public List<TEntity> GetAll();

}
