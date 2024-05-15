package com.mrfurkisan.core.application.repositories;

import java.util.List;

import com.mrfurkisan.core.domain.interfaces.IEntity;

public interface IRepository<TEntity extends IEntity, TFunctional> {

    public void Add(TEntity entity);

    public void Update(TEntity entity);

    public void Delete(TEntity entity);

    public <TFunctionalReturn> TEntity GetBy(TFunctional filter);

    public <TFunctionalReturn> List<TEntity> GetAllBy(TFunctional filter);

    public List<TEntity> GetAll();

}
