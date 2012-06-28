package com.ua.shop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class UserAuthenticationManager implements AuthenticationManager{

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        UserDetails userDetails = userDetailsService.loadUserByUsername(auth.getName());

        Object credentials = auth.getCredentials();

        String password = credentials.toString();
        String passwordEncoded = passwordEncoder.encodePassword(password, Salt.getSalt(password));

        if (passwordEncoded.equals(userDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(userDetails, credentials, userDetails.getAuthorities());
        }

        throw new BadCredentialsException("Bad Credentials");
    }
}
