package com.ua.shop.service.dao.impl;

import com.ua.shop.model.User;
import com.ua.shop.service.dao.UserDao;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class UserDaoImpl extends JpaDaoImpl<User, Long> implements UserDao{

    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public User getByEmail(String email) {

        Query query = entityManager.createQuery("from User u where u.email = :email");
        query.setParameter("email", email);

        return (User) query.getSingleResult();
    }

    @Override
    public boolean exists(String email) {
        Query query = entityManager.createQuery("select count(u) from User u where u.email = :email");
        query.setParameter("email", email);

        Long count = (Long) query.getSingleResult();

        return count != null && count > 0;
    }
}
