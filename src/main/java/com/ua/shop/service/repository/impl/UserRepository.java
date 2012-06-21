package com.ua.shop.service.repository.impl;

import com.ua.shop.model.User;
import com.ua.shop.service.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User getByEmail(String email);
}
