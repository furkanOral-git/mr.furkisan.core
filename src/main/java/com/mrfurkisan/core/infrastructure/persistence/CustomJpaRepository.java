package com.mrfurkisan.core.infrastructure.persistence;

import java.util.List;


import com.mrfurkisan.core.application.repositories.IJpaRepository;
import com.mrfurkisan.core.domain.functional.IPredicateBuilderFunctionalInterface;
import com.mrfurkisan.core.domain.interfaces.IEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public abstract class CustomJpaRepository<TEntity extends IEntity, TId> implements IJpaRepository<TEntity, TId> {

    private final Class<TEntity> __type;
    private final EntityManager __manager;

    public CustomJpaRepository(EntityManager manager ,Class<TEntity> type) {
        super();
        this.__manager = manager;
        this.__type = type;
    }

    public void Add(TEntity entity) {

        if (this.__manager.isOpen()) {

            this.__manager.persist(entity);
        }
    }

    public void Update(TEntity entity) {

        if (this.__manager.isOpen()) {

            this.__manager.merge(entity);
        }
    }

    public void Delete(TEntity entity) {

        if (this.__manager.isOpen()) {

            this.__manager.remove(entity);
        }
    }

    public TEntity GetById(TId id) {

        if (this.__manager.isOpen()) {

            return this.__manager.find(this.__type, id);
        }
        return null;
    }

    public List<TEntity> GetByPredicate(IPredicateBuilderFunctionalInterface<TEntity> pre) {

        if (this.__manager.isOpen()) {

            // Veritabanından sorumlu EntityManager'ın Kıriter oluşturucusu çağrılır.
            // Veritabanına gidecek olan sorgular bu nesne ile sağlanır.
            CriteriaBuilder builder = this.__manager.getCriteriaBuilder();

            // Veritabanındaki TEntity ile eşleşen tablo için sorgu yazmamızı sağlayan obje.
            CriteriaQuery<TEntity> query = builder.createQuery(this.__type);

            // Veritabanındaki tabloyu temsil eden kök obje
            Root<TEntity> table = query.from(this.__type);

            // Veritabanına attığımız sorgu. FunctionalInterface'ler ile sardık ve servis
            // metodları içerisinde sorgumuzu atacağız.
            Predicate predicate = pre.buildPredicate(builder, table);
            // Şart where fonksiyonu ile sorgumuza eklendi.
            query.where(predicate);
            return this.__manager.createQuery(query).getResultList();

        }
        return null;
    }

    public List<TEntity> GetAll() {

        if (this.__manager.isOpen()) {
            CriteriaBuilder builder = this.__manager.getCriteriaBuilder();
            CriteriaQuery<TEntity> query = builder.createQuery(this.__type);
            // SELECT * FROM
            query.select(query.from(this.__type));
            return this.__manager.createQuery(query).getResultList();
        }
        return null;
    }

}
