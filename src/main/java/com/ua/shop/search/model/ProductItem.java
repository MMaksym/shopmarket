package com.ua.shop.search.model;

import com.ua.shop.model.Category;
import com.ua.shop.model.Product;
import org.apache.solr.client.solrj.beans.Field;

public class ProductItem {

    @Field
    private String id;

    @Field("cat_name")
    private String catName;
    @Field("cat_description")
    private String catDescription;

    @Field("prod_name")
    private String prodName;
    @Field("prod_description")
    private String prodDescription;
    @Field("prod_price")
    private Float price;
    @Field("currency")
    private String currency;

    public ProductItem() {
    }

    public ProductItem(Product product) {
        id = String.valueOf(product.getId());

        Category category = product.getCategory();
        catName = category.getName();
        catDescription = category.getDescription();

        prodName = product.getName();
        prodDescription = product.getDescription();
        price = product.getPrice().floatValue();
        currency = product.getCurrency().getName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatDescription() {
        return catDescription;
    }

    public void setCatDescription(String catDescription) {
        this.catDescription = catDescription;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdDescription() {
        return prodDescription;
    }

    public void setProdDescription(String prodDescription) {
        this.prodDescription = prodDescription;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
