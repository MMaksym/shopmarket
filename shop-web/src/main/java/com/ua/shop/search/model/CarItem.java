package com.ua.shop.search.model;

import org.apache.solr.client.solrj.beans.Field;

import java.math.BigDecimal;

public class CarItem {

    @Field()
    private String id;
    @Field()
    private String make;
    @Field()
    private String model;
    @Field()
    private Integer year;
    @Field()
    private Float price;
    @Field("engine_size")
    private Integer engineSize;
    @Field()
    private Integer mileage;
    @Field()
    private String colour;
    @Field()
    private Boolean damaged;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(Integer engineSize) {
        this.engineSize = engineSize;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public Boolean getDamaged() {
        return damaged;
    }

    public void setDamaged(Boolean damaged) {
        this.damaged = damaged;
    }
}
