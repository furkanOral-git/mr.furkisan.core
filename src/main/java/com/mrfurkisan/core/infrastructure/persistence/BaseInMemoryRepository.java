package com.mrfurkisan.core.infrastructure.persistence;

import java.util.ArrayList;
import java.util.List;

import com.mrfurkisan.core.application.repositories.IInMemoryRepository;
import com.mrfurkisan.core.domain.BaseEntity;
import com.mrfurkisan.core.domain.functional.IFunctionalInterface;

public abstract class BaseInMemoryRepository<TEntity extends BaseEntity<TId>, TId>
        implements IInMemoryRepository<TEntity, TId> {

    private List<TEntity> __repo;

    public BaseInMemoryRepository() {

        this.__repo = new ArrayList<TEntity>();
    }

    @Override
    public void Add(TEntity entity) {

        if (!this.__repo.contains(entity)) {

            this.__repo.add(entity);
        }
    }

    @Override
    public void Update(TEntity entity) {

        if (!this.__repo.contains(entity)) {
            this.__repo.removeIf((e) -> e.GetId() == entity.GetId());
            this.__repo.add(entity);
        }
    }

    @Override
    public void Delete(TEntity entity) {

        this.__repo.remove(entity);
    }

    @Override
    public <TContext> List<TEntity> GetAllBy(IFunctionalInterface<TEntity, TContext> filter) {

        List<TEntity> results = new ArrayList<TEntity>();

        for (TEntity tEntity : results) {

            if ((Boolean) filter.build(tEntity)) {

                results.add(tEntity);
            }
        }
        return results;

    }

    @Override
    public List<TEntity> GetAll() {

        return this.__repo;
    }

    @Override
    public <TContext> TEntity GetBy(IFunctionalInterface<TEntity, TContext> filter) {

        for (TEntity tEntity : __repo) {

            if ((Boolean) filter.build(tEntity)) {
                return tEntity;
            }
        }
        return null;
    }

}
