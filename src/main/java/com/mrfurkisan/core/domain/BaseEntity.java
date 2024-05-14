package com.mrfurkisan.core.domain;

import com.mrfurkisan.core.domain.interfaces.IEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BaseEntity<TId> implements IEntity {
    
    public abstract TId GetId();
}
