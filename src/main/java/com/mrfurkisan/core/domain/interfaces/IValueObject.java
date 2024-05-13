package com.mrfurkisan.core.domain.interfaces;

public interface IValueObject<TEntity extends IEntity, TValue> extends IBaseValueObject {
    public boolean isEqualValue(IValueObject<TEntity, TValue> valueObject);

    public void UpdateValue(TValue value);

    public TValue GetValue();
}
