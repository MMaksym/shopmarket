package com.ua.shop.service;


import com.ua.shop.model.Attachment;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface AttachmentService {

    Attachment storeFile(Long userId, String fileName, String contentType, byte[] bytes) throws IOException;

    List<Attachment> getAttachments(Long userId);

    Attachment get(Long userId, String name);
    
    InputStream getInputStream(Long userId, String name) throws IOException;

    void removeFile(Long userId, String name) throws IOException;
    
    
    
}
