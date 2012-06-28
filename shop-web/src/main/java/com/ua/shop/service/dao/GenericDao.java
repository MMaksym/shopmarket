package com.ua.shop.service.dao;

import com.ua.shop.model.BaseModel;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T extends BaseModel, PK extends Serializable> {

    T get(PK id);

    List<T> getAll();

    List<T> get(int start, int max);

//    T saveOrUpdate(T entity);

    T save(T entity);

    T update(T entity);

    void remove(T entity);

    void remove(PK id);
    
    Integer count();
    
    
}
