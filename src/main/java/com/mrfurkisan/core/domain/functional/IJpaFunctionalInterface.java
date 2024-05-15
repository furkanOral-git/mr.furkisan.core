package com.mrfurkisan.core.domain.functional;


import com.mrfurkisan.core.domain.interfaces.IEntity;

import jakarta.persistence.criteria.CriteriaBuilder;



@FunctionalInterface
public  interface  IJpaFunctionalInterface<TEntity extends IEntity,TFunctionalInterfaceReturn> 
extends IFunctionalInterface<TFunctionalInterfaceReturn,CriteriaBuilder> {
    

}
