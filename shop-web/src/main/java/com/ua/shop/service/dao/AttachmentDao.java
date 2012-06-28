package com.ua.shop.service.dao;

import com.ua.shop.model.Attachment;
import com.ua.shop.model.User;

import java.util.List;

public interface AttachmentDao extends GenericDao<Attachment, Long>{

    List<Attachment> getAttachments(User user);

    Attachment get(User user, String name);
    
}
