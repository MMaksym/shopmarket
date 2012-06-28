package com.ua.shop.model;


import javax.persistence.*;
import java.util.Date;

/**
 * Author: Lotus
 * Date: 13.03.12
 */
@Entity
@Table(name = "forgot_hash",
        uniqueConstraints = @UniqueConstraint(name = "user_hash", columnNames = {"user_id", "hash"}))

public class ForgotHash extends BaseModel{
    
    @Column(name = "hash", nullable = false)
    private String hash;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "expire_date", nullable = false)
    private Date expireDate;

    @Column(name = "is_valid", nullable = false)
    private boolean valid = true;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
