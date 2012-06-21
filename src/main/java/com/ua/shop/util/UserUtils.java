package com.ua.shop.util;

import com.ua.shop.model.SecurityUser;
import com.ua.shop.model.User;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;

public class UserUtils {

    public static User getUser(HttpServletRequest request) {

        SecurityContext context = SecurityContextHolder.getContext();

        if (context != null) {
            Object principal = context.getAuthentication().getPrincipal();

            if (principal != null && principal instanceof SecurityUser) {
                SecurityUser securityUser = (SecurityUser) principal;
                return securityUser.getUser();
            }
        }

        return null;
    }

}
