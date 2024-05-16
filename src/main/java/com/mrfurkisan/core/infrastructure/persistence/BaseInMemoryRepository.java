package com.mrfurkisan.core.infrastructure.persistence;

import java.util.ArrayList;
import java.util.List;


import com.mrfurkisan.core.domain.functional.IInMemoryFunctionalInterface;
import com.mrfurkisan.core.domain.interfaces.IEntity;

public abstract class BaseInMemoryRepository<TEntity extends IEntity, TId>{

    private final List<TEntity> __repo;

    public BaseInMemoryRepository() {

        this.__repo = new ArrayList<TEntity>();
    }

    
    public void Add(TEntity entity) {

        if (!this.__repo.contains(entity)) {

            this.__repo.add(entity);
        }
    }

    
    public void Update(IInMemoryFunctionalInterface<TEntity> filter,TEntity newEntity) {

        if (!this.__repo.contains(newEntity)) {
            
            for (TEntity tEntity : this.__repo) {

                if (filter.build(tEntity)) {
                    
                    this.__repo.remove(tEntity);
                    this.__repo.add(newEntity);
                }
            }
            
        }
    }

    
    public void Delete(TEntity entity) {

        this.__repo.remove(entity);
    }

    
    public List<TEntity> GetAllBy(IInMemoryFunctionalInterface<TEntity> filter) {

        List<TEntity> results = new ArrayList<TEntity>();

        for (TEntity tEntity : results) {

            if ((Boolean) filter.build(tEntity)) {

                results.add(tEntity);
            }
        }
        return results;

    }

    
    public List<TEntity> GetAll() {

        return this.__repo;
    }
    
    
    public  TEntity GetBy(IInMemoryFunctionalInterface<TEntity> filter) {

        for (TEntity tEntity : __repo) {

            if ((Boolean) filter.build(tEntity)) {
                return tEntity;
            }
        }
        return null;
    }

}
