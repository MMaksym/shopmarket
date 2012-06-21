package com.ua.shop.service.dao;

import com.ua.shop.model.ForgotHash;

/**
 * Author: Lotus
 * Date: 13.03.12
 */
public interface ForgotHashDao extends GenericDao<ForgotHash, Long>{

    ForgotHash get(String hash, Long userId);

}
