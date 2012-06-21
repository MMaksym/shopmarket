package com.ua.shop.service.impl;

import com.ua.shop.model.Category;
import com.ua.shop.service.CategoryService;
import com.ua.shop.service.dao.CategoryDao;
import com.ua.shop.util.NameConverterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public Category getCategory(Long id) {
        Category category = categoryDao.get(id);
        return category;
    }

    @Override
    public Category getCategory(String name) {
        Category category = categoryDao.getByName(name);
        return category;
    }

    @Override
    public Category saveOrUpdateCategory(Category category) {
        if (category.getId() == null) {
            categoryDao.save(category);
        } else {
            categoryDao.update(category);
        }
        return category;
    }

    @Override
    public void removeCategory(Category category) {
         categoryDao.remove(category);
    }

    @Override
    public void removeCategory(Long id) {
        categoryDao.remove(id);
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> all = categoryDao.getAll();
        return all;
    }

    @Override
    public List<Category> getCategories(int start, int length) {
        List<Category> categories = categoryDao.get(start, length);
        return categories;
    }

    @Override
    public boolean exists(String name) {
        boolean exists = categoryDao.getByName(name) != null;
        return exists;
    }

    @Override
    public Integer count() {
        return categoryDao.count();
    }
}
