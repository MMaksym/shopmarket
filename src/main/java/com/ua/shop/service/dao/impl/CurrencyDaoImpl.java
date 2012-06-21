package com.ua.shop.service.dao.impl;

import com.ua.shop.model.Currency;
import com.ua.shop.service.dao.CurrencyDao;
import org.springframework.stereotype.Repository;

@Repository
public class CurrencyDaoImpl extends BaseHibernateDaoImpl<Currency, Long> implements CurrencyDao{

    public CurrencyDaoImpl() {
        super(Currency.class);
    }

}
