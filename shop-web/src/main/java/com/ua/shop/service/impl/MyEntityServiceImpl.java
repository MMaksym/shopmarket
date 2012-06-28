package com.ua.shop.service.impl;

import com.ua.shop.model.MyEntity;
import com.ua.shop.service.MyEntityService;
import com.ua.shop.service.dao.MyEntityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyEntityServiceImpl implements MyEntityService {

    @Autowired
    private MyEntityDao myEntityDao;


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void test() {

        MyEntity entity = new MyEntity();

        entity.setName("One");

        myEntityDao.save(entity);

        Integer count = myEntityDao.count();
        System.out.println(count);

    }


}
