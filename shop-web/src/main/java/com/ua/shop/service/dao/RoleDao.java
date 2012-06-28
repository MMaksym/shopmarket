package com.ua.shop.service.dao;

import com.ua.shop.model.Role;

public interface RoleDao extends GenericDao<Role, Long>{

    Role getByName(String roleName);

    boolean exist(String roleName);

}
