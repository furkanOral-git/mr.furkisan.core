package com.mrfurkisan.core.infrastructure.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mrfurkisan.core.domain.functional.IJpaFunctionalInterface;
import com.mrfurkisan.core.domain.interfaces.IEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BaseJpaRepository<TEntity extends IEntity> {

    private final Class<TEntity> __type;
    private final EntityManager __manager;
    // Veritabanından sorumlu EntityManager'ın Kıriter oluşturucusu çağrılır.
    // Veritabanına gidecek olan sorgular bu nesne ile sağlanır.

    public BaseJpaRepository(EntityManager manager, Class<TEntity> type) {
        super();
        this.__manager = manager;
        this.__type = type;
    }
    //@Transactional herhangi bir işlemin kesilmesi durumunda değişiklikleri geri almaya yarayan bir bağlam.
    //EntityManager'ı direkt kullanırken bunu eklemek zorunlu kılınıyor!
    @Transactional
    public void Add(TEntity entity) {

       this.__manager.persist(entity);
    }

    public int Update(IJpaFunctionalInterface<TEntity, CriteriaUpdate<TEntity>> filter) {

        var builder = this.__manager.getCriteriaBuilder();
        CriteriaUpdate<TEntity> update = filter.build(builder);
        return this.__manager.createQuery(update).executeUpdate();
    }

    public int Delete(IJpaFunctionalInterface<TEntity, CriteriaDelete<TEntity>> filter) {

        var builder = this.__manager.getCriteriaBuilder();
        CriteriaDelete<TEntity> delete = filter.build(builder);
        return this.__manager.createQuery(delete).executeUpdate();
    }

    public List<TEntity> GetAllBy(IJpaFunctionalInterface<TEntity, CriteriaQuery<TEntity>> filter) {

        // Criteria Api'nin henüz veritabanına sorguyu yollamadan önce oluşturduğu sorgu
        // nesnesi.
        // Sorguyu yolladığı nesne ise TypedQuery<TEntity> oluyor.
        var builder = this.__manager.getCriteriaBuilder();
        CriteriaQuery<TEntity> query = filter.build(builder);
        return this.__manager.createQuery(query).getResultList();

    }

    public List<TEntity> GetAll() {

        var builder = this.__manager.getCriteriaBuilder();
        CriteriaQuery<TEntity> query = builder.createQuery(this.__type);
        // SELECT * FROM
        query.select(query.from(this.__type));
        return this.__manager.createQuery(query).getResultList();

    }

    public TEntity GetBy(IJpaFunctionalInterface<TEntity, CriteriaQuery<TEntity>> filter) {

        var builder = this.__manager.getCriteriaBuilder();
        CriteriaQuery<TEntity> query = filter.build(builder);
        TEntity entity = null;
        try {

            entity = this.__manager.createQuery(query).getSingleResult();
            

        } catch (Exception e) {
            
            if(e instanceof NoResultException){
                
                return entity;
            }
            if(e instanceof NonUniqueResultException){
                return entity;
            }
            
        }
        return entity;
        
    }

}
