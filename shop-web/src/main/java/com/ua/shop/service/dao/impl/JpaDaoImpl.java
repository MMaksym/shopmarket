package com.ua.shop.service.dao.impl;

import com.ua.shop.model.BaseModel;
import com.ua.shop.service.dao.GenericDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

public class JpaDaoImpl<T extends BaseModel, PK extends Serializable> implements GenericDao <T, PK>{

    private Class<T> persistenceClass;

    @PersistenceContext
    protected EntityManager entityManager;

    public JpaDaoImpl(Class<T> persistenceClass) {
        this.persistenceClass = persistenceClass;
    }

    @Override
    public T get(PK id) {
        return entityManager.find(persistenceClass, id);
    }

    @Override
    public List<T> getAll() {
        Query query = entityManager.createQuery("from " + persistenceClass.getName());
        return query.getResultList();
    }

    @Override
    public List<T> get(int start, int max) {
        return null;
    }

    @Override
    public T save(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public T update(T entity) {
        return save(entity);
    }

    @Override
    public void remove(T entity) {
        entityManager.remove(entity);
    }

    @Override
    public void remove(PK id) {
        T reference = entityManager.getReference(persistenceClass, id);
        entityManager.remove(id);
    }

    @Override
    public Integer count() {

        Query query = entityManager.createQuery("select count(u) from " + persistenceClass.getName() + " u");
        Long count = (Long) query.getSingleResult();

        return count != null ? count.intValue() : 0;
    }
}
