package com.ua.shop.service.dao.impl;

import com.ua.shop.model.Category;
import com.ua.shop.model.Product;
import com.ua.shop.service.dao.ProductDao;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDaoImpl extends BaseHibernateDaoImpl<Product, Long> implements ProductDao{

    public ProductDaoImpl() {
        super(Product.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Product> getByCategory(Category category) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);

        criteria.add(Restrictions.eq("category", category));

        return findByCriteria(criteria);
    }

    @Override
    public Product get(String category, String name) {
        Query query = getSession().createQuery("FROM Product p WHERE p.name= :name AND p.category.name = :category");

        query.setString("name", name);
        query.setString("category", category);

        return (Product) query.uniqueResult();
    }
}
