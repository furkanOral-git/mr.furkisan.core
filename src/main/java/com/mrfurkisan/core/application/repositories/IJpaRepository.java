package com.mrfurkisan.core.application.repositories;




import com.mrfurkisan.core.domain.functional.IJpaFunctionalInterface;
import com.mrfurkisan.core.domain.interfaces.IEntity;

public interface IJpaRepository<TEntity extends IEntity, TId> 
    extends IRepository<TEntity,IJpaFunctionalInterface<TEntity,?>> {

   
}
