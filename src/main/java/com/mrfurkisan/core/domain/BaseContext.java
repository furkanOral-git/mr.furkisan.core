package com.mrfurkisan.core.domain;

import com.mrfurkisan.core.domain.functional.IVoidFunctionalInterface;
import com.mrfurkisan.core.domain.interfaces.IApplicationContext;
import com.mrfurkisan.core.domain.interfaces.IEntityAggregateRoot;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public abstract class BaseContext<TEntityAggregateRoot extends IEntityAggregateRoot> implements IApplicationContext{
    
    private List<TEntityAggregateRoot> context;
    
    private BaseContext() {
        super();
    }
   
    public void AddToContext(TEntityAggregateRoot entity) {

        this.context.add(entity);

    }

    
    public void ForEach(IVoidFunctionalInterface<TEntityAggregateRoot> callBack) {

        for (TEntityAggregateRoot tEntity : this.context) {

            callBack.build(tEntity);
        }

    }

    
    public void RemoveFromRoot(TEntityAggregateRoot entity) {

        this.context.remove(entity);
    }

    
    public Boolean IsExist(TEntityAggregateRoot entity) {
        return this.context.contains(entity);
    }
}
