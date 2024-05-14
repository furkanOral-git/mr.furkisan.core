package com.mrfurkisan.core.application.repositories;

import java.util.List;

import com.mrfurkisan.core.domain.functional.IFunctionalInterface;
import com.mrfurkisan.core.domain.interfaces.IEntity;

public interface IRepository<TEntity extends IEntity> {

    public void Add(TEntity entity);

    public void Update(TEntity entity);

    public void Delete(TEntity entity);

    public <TContext> TEntity GetBy(IFunctionalInterface<TEntity, TContext> filter);

    public <TContext> List<TEntity> GetAllBy(IFunctionalInterface<TEntity, TContext> filter);

    public List<TEntity> GetAll();

}
