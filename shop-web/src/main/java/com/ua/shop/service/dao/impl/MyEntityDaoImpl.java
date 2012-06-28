package com.ua.shop.service.dao.impl;

import com.ua.shop.model.MyEntity;
import com.ua.shop.service.dao.MyEntityDao;
import org.springframework.stereotype.Repository;

@Repository
public class MyEntityDaoImpl extends JpaDaoImpl<MyEntity, Long>  implements MyEntityDao{

    public MyEntityDaoImpl() {
        super(MyEntity.class);
    }
}
