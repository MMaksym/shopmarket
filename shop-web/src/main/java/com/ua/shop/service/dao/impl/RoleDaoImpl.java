package com.ua.shop.service.dao.impl;

import com.ua.shop.model.Role;
import com.ua.shop.service.dao.RoleDao;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends BaseHibernateDaoImpl<Role, Long> implements RoleDao{

    public RoleDaoImpl() {
        super(Role.class);
    }

    @Override
    public Role getByName(String roleName) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Role.class);

        criteria.add(Restrictions.eq("name", roleName));

        return (Role) firstObject(findByCriteria(criteria));
    }

    @Override
    public boolean exist(String roleName) {
        Query query = getSession().createQuery("select count(*) from Role r where r.name = :roleName");

        query.setString("roleName", roleName);

        Integer count = (Integer) query.uniqueResult();

        return count != null && count > 0;
    }
}
