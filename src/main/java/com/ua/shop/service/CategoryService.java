package com.ua.shop.service;

import com.ua.shop.model.Category;

import java.util.List;

public interface CategoryService {

    Category getCategory(Long id);

    Category getCategory(String name);

    Category saveOrUpdateCategory(Category category);

    void removeCategory(Category category);

    void removeCategory(Long id);

    List<Category> getAllCategories();

    List<Category> getCategories(int start, int length);

    boolean exists(String name);

    Integer count();

}
