package com.mrfurkisan.core.domain.functional;



import com.mrfurkisan.core.domain.interfaces.IEntity;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

//Java interfacelerde functional interfaceler C#'taki LambdaExpression'ların karşılığı olarak kullanılabiliyor
//Fakat C# taki Func<TEntity,TRoot,Predicate> tipini biz burada oluşturduk. Artık methoda lambda expression gönderebileceğiz.
//Sorgularımız daha kontrollü ve karmaşık hale getirilebilir.
@FunctionalInterface
public interface IPredicateBuilderFunctionalInterface<TEntity extends IEntity> {
    Predicate buildPredicate(CriteriaBuilder builder, Root<TEntity> table);
}
