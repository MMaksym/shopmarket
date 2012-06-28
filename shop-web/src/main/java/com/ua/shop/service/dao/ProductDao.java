package com.ua.shop.service.dao;

import com.ua.shop.model.Category;
import com.ua.shop.model.Product;

import java.util.List;

public interface ProductDao extends GenericDao<Product, Long>{

    List<Product> getByCategory(Category category);

    Product get(String category, String name);

}
