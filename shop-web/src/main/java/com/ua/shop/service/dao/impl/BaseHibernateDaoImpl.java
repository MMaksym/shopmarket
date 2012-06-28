package com.ua.shop.service.dao.impl;

import com.ua.shop.model.BaseModel;
import com.ua.shop.service.dao.GenericDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class BaseHibernateDaoImpl<T extends BaseModel, PK extends Serializable> extends HibernateDaoSupport implements GenericDao<T, PK>{

    private Class<T> persistenceClass;

    public BaseHibernateDaoImpl(Class<T> persistenceClass) {
        this.persistenceClass = persistenceClass;
    }

    @Autowired
    public void setHibernateSessionFactory(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }


    @Override
    public T get(PK id) {
        return getHibernateTemplate().get(persistenceClass, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getAll() {
        DetachedCriteria criteria = DetachedCriteria.forClass(persistenceClass);

        return getHibernateTemplate().findByCriteria(criteria);
    }

    @Override
    public List<T> get(int start, int max) {
        DetachedCriteria criteria = DetachedCriteria.forClass(persistenceClass);

        return getHibernateTemplate().findByCriteria(criteria, start, max);
    }

    //    @Override
//    public T saveOrUpdate(T entity) {
//        Long id = entity.getId();
//
//        if (id == null) {
//            entity.setCreateDate(new Date());
//        }
//
//        getHibernateTemplate().saveOrUpdate(entity);
//        return entity;
//    }

    @Override
    public T save(T entity) {

        entity.setCreateDate(new Date());
        getHibernateTemplate().save(entity);

        return entity;
    }

    @Override
    public T update(T entity) {
        getHibernateTemplate().update(entity);
        return entity;
    }

    @Override
    public void remove(T entity) {
        getHibernateTemplate().delete(entity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void remove(PK id) {
        Session session = getSession();
        T entity = (T) session.get(persistenceClass, id);
        session.delete(entity);
        session.flush();
    }

    @Override
    public Integer count() {

        Session session = getSession();

        Query query = session.createQuery("select count(entity) from " + persistenceClass.getName() + " entity");

        Long count = (Long) query.uniqueResult();

        return count != null ? count.intValue() : 0;
    }

    protected List findByCriteria(DetachedCriteria criteria) {
        return getHibernateTemplate().findByCriteria(criteria);
    }

    protected Object firstObject(List list) {
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }
}
