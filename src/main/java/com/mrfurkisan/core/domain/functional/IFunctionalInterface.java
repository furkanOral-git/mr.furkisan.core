package com.mrfurkisan.core.domain.functional;



@FunctionalInterface
public interface IFunctionalInterface<TReturn,TParameter> {
    // Java : VarArgs(Object...) - C#'ta params keywordü. Metod içerisine
    // istediğimiz kadar parametre göndermemizi sağlar.
    public TReturn build(TParameter parameter);
}
