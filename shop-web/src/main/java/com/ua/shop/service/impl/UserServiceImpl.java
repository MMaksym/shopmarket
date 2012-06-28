package com.ua.shop.service.impl;

import com.ua.shop.model.SecurityUser;
import com.ua.shop.model.Role;
import com.ua.shop.model.User;
import com.ua.shop.model.constants.RoleConstants;
import com.ua.shop.model.enumeration.Gender;
import com.ua.shop.security.Salt;
import com.ua.shop.service.UserService;
import com.ua.shop.service.dao.RoleDao;
import com.ua.shop.service.dao.UserDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Author: Lotus
 * Date: 25.02.12
 */
@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private static Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User getUser(Long id) {

        if (logger.isDebugEnabled()) {
            logger.debug("Get user by id " + id);
        }

        User user = null;

        if (id > 0) {
            user = userDao.get(id);
            if (logger.isDebugEnabled()) {
                logger.debug(user != null ?
                        "User with id " + id + " is successfully found" :
                        "User with id " + id + " is not found in DB");
            }
        } else {
            logger.warn("Id" + id + " is not valid");
        }

        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        if (logger.isDebugEnabled()) {
            logger.debug("Get user by email " + email);
        }

        User user = userDao.getByEmail(email);
        if (logger.isDebugEnabled()) {
            logger.debug(user != null ?
                    "User with email " + email + " is successfully found" :
                    "User with email " + email + " is not found in DB");
        }

        return user;   
    }

    @Override
    public List<User> getUsers(int start, int length) {
        return null;
    }

    @Override
    public boolean existUser(String email) {

        if (logger.isDebugEnabled()) {
            logger.debug("Get user by email " + email);
        }

        boolean exists= userDao.exists(email);
        if (logger.isDebugEnabled()) {
            logger.debug("User with email " + (exists ? " exists" : " doesn't exist"));
        }

        return exists;
    }

    @Override
    public User addUser(String firstName, String lastName, String email, String password, Date dateOfBirth,
                        Gender gender, Role role) {

        Object salt = Salt.getSalt(password);
        String passwordEncoded = passwordEncoder.encodePassword(password, salt);

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(passwordEncoded);
        user.setGender(gender);
        user.setDateOfBirth(dateOfBirth);


        if (role != null) {
            user.setRole(role);
        } else {
            Role userRole = roleDao.getByName(RoleConstants.USER);

            if (userRole != null) {
                user.setRole(userRole);
            }
        }

        userDao.save(user);

        return user;
    }

    @Override
    public User updateUser(Long userId, String firstName, String lastName, Date dateOfBirth, Gender gender) {
        return null;
    }

    @Override
    public void updatePassword(Long userId, String password) {
        logger.debug("Get user by id " + userId);
        User user = userDao.get(userId);

        if (user == null) {
            logger.error("User with id " + userId + " is not found in DB");
            return;
        }

        user.setPassword(password);

        userDao.update(user);

        logger.debug("Password is successfully changed for user with id " + userId );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("Load user by name [" + username + "]");

        User user = userDao.getByEmail(username);

        if (user == null) {
            logger.error("User with name " + username + " is not found in DB");
            throw new UsernameNotFoundException("User with name " + username + " doesn't exist");
        }

        return new SecurityUser(user);
    }
}
