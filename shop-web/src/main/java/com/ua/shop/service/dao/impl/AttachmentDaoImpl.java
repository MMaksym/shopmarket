package com.ua.shop.service.dao.impl;


import com.ua.shop.model.Attachment;
import com.ua.shop.model.User;
import com.ua.shop.service.dao.AttachmentDao;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AttachmentDaoImpl  extends BaseHibernateDaoImpl<Attachment, Long> implements AttachmentDao{

    public AttachmentDaoImpl() {
        super(Attachment.class);
    }

    @Override
    public List<Attachment> getAttachments(User user) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Attachment.class);


        criteria.add(Restrictions.eq("user", user));

        return findByCriteria(criteria);
    }

    @Override
    public Attachment get(User user, String name) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Attachment.class);

        criteria.add(Restrictions.eq("user", user))
                .add(Restrictions.eq("name", name));

        return (Attachment) firstObject(findByCriteria(criteria));
    }
}
