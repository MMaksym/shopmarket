package com.ua.shop.service.dao;

import com.ua.shop.model.User;

public interface UserDao extends GenericDao<User, Long>{

    User getByEmail(String email);

    boolean exists(String email);
    

}
