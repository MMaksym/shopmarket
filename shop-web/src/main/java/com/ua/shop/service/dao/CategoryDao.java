package com.ua.shop.service.dao;

import com.ua.shop.model.Category;

public interface CategoryDao extends GenericDao<Category, Long>{

    Category getByName(String name);

}
