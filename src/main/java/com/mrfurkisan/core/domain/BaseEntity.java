package com.mrfurkisan.core.domain;

import java.io.Serializable;

import com.mrfurkisan.core.domain.interfaces.IEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BaseEntity<TId> implements IEntity, Serializable {
    
    @Id
    @GeneratedValue
    public abstract TId GetId();
}
