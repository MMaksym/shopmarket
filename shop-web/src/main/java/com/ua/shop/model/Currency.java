package com.ua.shop.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "currency")
@AttributeOverride(name = "id", column = @Column(name = "currency_id"))
public class Currency extends BaseModel{

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "short_description", length = 100)
    private String shortDescription;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Currency");
        sb.append("{name='").append(name).append('\'');
        sb.append(", shortDescription='").append(shortDescription).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
