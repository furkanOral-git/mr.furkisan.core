package com.mrfurkisan.core.domain.functional;


@FunctionalInterface
public interface IVoidFunctionalInterface<TParameter> {
    // Java : VarArgs(Object...) - C#'ta params keywordü. Metod içerisine
    // istediğimiz kadar parametre göndermemizi sağlar.
    public void build(TParameter arg);
}
