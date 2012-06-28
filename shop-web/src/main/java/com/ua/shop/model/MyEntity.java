package com.ua.shop.model;

import javax.persistence.Entity;

@Entity
public class MyEntity extends BaseModel{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
