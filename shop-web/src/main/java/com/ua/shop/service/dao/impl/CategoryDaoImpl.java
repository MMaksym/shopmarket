package com.ua.shop.service.dao.impl;

import com.ua.shop.model.Category;
import com.ua.shop.service.dao.CategoryDao;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDaoImpl extends BaseHibernateDaoImpl<Category, Long> implements CategoryDao{

    public CategoryDaoImpl() {
        super(Category.class);
    }

    @Override
    public Category getByName(String name) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Category.class);

        criteria.add(Restrictions.eq("name", name));

        return (Category) firstObject(findByCriteria(criteria));
    }
}
