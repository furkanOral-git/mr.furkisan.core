package com.mrfurkisan.core.domain.functional;

import com.mrfurkisan.core.domain.interfaces.IEntity;

@FunctionalInterface
public interface IFunctionalInterface<TEntity extends IEntity,TReturn> {
    //Java : VarArgs(Object...) - C#'ta params keywordü. Metod içerisine istediğimiz kadar parametre göndermemizi sağlar.
    public TReturn build(Object... args);
}
