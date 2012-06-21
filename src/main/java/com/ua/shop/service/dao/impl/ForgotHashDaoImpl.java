package com.ua.shop.service.dao.impl;

import com.ua.shop.model.ForgotHash;
import com.ua.shop.service.dao.ForgotHashDao;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * Author: Lotus
 * Date: 13.03.12
 */
@Repository
public class ForgotHashDaoImpl extends BaseHibernateDaoImpl<ForgotHash, Long> implements ForgotHashDao{

    public ForgotHashDaoImpl() {
        super(ForgotHash.class);
    }

    @Override
    public ForgotHash get(String hash, Long userId) {
        DetachedCriteria criteria = DetachedCriteria.forClass(ForgotHash.class);

        criteria.add(Restrictions.eq("hash", hash))
                .add(Restrictions.eq("userId", userId));

        return (ForgotHash) firstObject(findByCriteria(criteria));
    }
}
