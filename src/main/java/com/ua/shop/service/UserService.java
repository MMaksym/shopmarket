package com.ua.shop.service;

import com.ua.shop.model.Role;
import com.ua.shop.model.User;
import com.ua.shop.model.enumeration.Gender;

import java.util.Date;
import java.util.List;

/**
 * Author: Lotus
 * Date: 25.02.12
 */
public interface UserService {

    public User getUser(Long id);

    public User getUserByEmail(String email);

    public List<User> getUsers(int start, int length);

    public boolean existUser(String email);

    public User addUser(String firstName, String lastName, String email, String password, Date dateOfBirth, Gender gender,
                         Role role);

    public User updateUser(Long userId, String firstName, String lastName, Date dateOfBirth, Gender gender);

    public void updatePassword(Long userId, String password);




    
}
