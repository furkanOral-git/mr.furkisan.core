package com.mrfurkisan.core.domain.functional;

import com.mrfurkisan.core.domain.interfaces.IEntity;

@FunctionalInterface
public interface IVoidFunctionalInterface<TEntity extends IEntity> {
    // Java : VarArgs(Object...) - C#'ta params keywordü. Metod içerisine
    // istediğimiz kadar parametre göndermemizi sağlar.
    public void build(Object... args);
}
