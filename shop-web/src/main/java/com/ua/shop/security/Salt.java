package com.ua.shop.security;

import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Author: Lotus
 * Date: 21.03.12
 */
public class Salt{

    private static final String STATIC_SALT = "kadSRsHSDL@FSDF";

    public static String getSalt(String password) {

        return STATIC_SALT + password.length();
    }
}
