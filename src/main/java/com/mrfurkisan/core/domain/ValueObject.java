package com.mrfurkisan.core.domain;

import com.mrfurkisan.core.domain.interfaces.IEntity;
import com.mrfurkisan.core.domain.interfaces.IValueObject;

public abstract class ValueObject<TEntity extends IEntity, TValue> implements IValueObject<TEntity, TValue> {

    private TValue __value;

    public ValueObject(TValue value) {
        super();
        this.__value = value;
    }

    @Override
    public boolean isEqualValue(IValueObject<TEntity, TValue> valueObject) {

        if (this.__value == valueObject.GetValue()) {
            return true;
        }
        return false;
    }

    @Override
    public void UpdateValue(TValue value) {
        this.__value = value;
    }

    @Override
    public TValue GetValue() {
        return this.__value;
    }

}
