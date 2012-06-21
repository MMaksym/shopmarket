package com.ua.shop.service.impl;

import com.ua.shop.model.Category;
import com.ua.shop.model.Currency;
import com.ua.shop.model.Product;
import com.ua.shop.search.model.ProductItem;
import com.ua.shop.service.ProductService;
import com.ua.shop.service.dao.CategoryDao;
import com.ua.shop.service.dao.CurrencyDao;
import com.ua.shop.service.dao.ProductDao;
import com.ua.shop.service.search.ProductIndexer;
import com.ua.shop.web.form.ProductForm;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * Author: Lotus
 * Date: 11.03.12
 */
@Service
public class ProductServiceImpl implements ProductService{
    
    private static Logger logger = Logger.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductDao productDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private CurrencyDao currencyDao;

    @Autowired
    private ProductIndexer indexer;

    @Transactional
    @Override
    public void saveProduct(ProductForm productForm) {

        Product product = productForm.convertToProduct();

        Category category = categoryDao.getByName(productForm.getCategoryName());
        Currency currency = currencyDao.get(productForm.getCurrencyId());
        
        product.setCategory(category);
        product.setCurrency(currency);

        if (product.getId() == null) {
            productDao.save(product);
        } else {
            productDao.update(product);
        }

        ProductItem productItem = new ProductItem(product);

        try {
            indexer.index(productItem);
        } catch (IOException e) {
            logger.error(e.getMessage());
        } catch (SolrServerException e) {
            logger.error(e.getMessage());
        }


    }
}
