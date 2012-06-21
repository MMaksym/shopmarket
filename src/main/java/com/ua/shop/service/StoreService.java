package com.ua.shop.service;

import java.io.IOException;
import java.io.InputStream;

public interface StoreService {

    void store(Long userId, String name, byte[] bytes) throws IOException;

    void remove(Long userId, String name) throws IOException;

    InputStream getInputStream(Long userId, String name) throws IOException;
    
}
