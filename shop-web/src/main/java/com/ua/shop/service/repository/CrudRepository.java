package com.ua.shop.service.repository;

import org.springframework.data.repository.Repository;

import java.io.Serializable;

public interface CrudRepository<T, ID extends Serializable> extends Repository<T, ID> {

    T save(T entity);

    T findOne(ID primaryKey);

    Iterable<T> findAll();

    Long count();

    void delete(T entity);

    boolean exists(ID primaryKey);


}