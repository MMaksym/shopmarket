package com.ua.shop.web.form;

import com.ua.shop.model.Product;

import java.math.BigDecimal;

/**
 * Author: Lotus
 * Date: 11.03.12
 */
public class ProductForm {
    
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long currencyId;
    private String categoryName;

    public ProductForm() {
    }

    public ProductForm(Product product) {
        if (product != null) {
            id = product.getId();
            name = product.getName();
            description = product.getDescription();
            price = product.getPrice();
            currencyId = product.getCurrency().getId();
            categoryName = product.getCategory().getName();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Product convertToProduct() {
        Product product = new Product();
//        product.setId(id);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);

        return product;
    }
    
}
