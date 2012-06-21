package com.ua.shop.service.impl;

import com.ua.shop.model.Attachment;
import com.ua.shop.model.User;
import com.ua.shop.service.AttachmentService;
import com.ua.shop.service.StoreService;
import com.ua.shop.service.UserService;
import com.ua.shop.service.dao.AttachmentDao;
import com.ua.shop.service.dao.UserDao;
import com.ua.shop.util.FileNameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Service
public class AttachmentServiceImpl implements AttachmentService{
    
    private static Logger logger = Logger.getLogger(AttachmentServiceImpl.class);

    @Autowired
    private AttachmentDao attachmentDao;

    @Autowired
    private StoreService storeService;

    @Autowired
    private UserDao userDao;

    @Override
    public Attachment storeFile(Long userId, String fileName, String contentType, byte[] bytes) throws IOException {

        User user = userDao.get(userId);
        String title = FileNameUtils.getFileName(fileName);
        String extension = FileNameUtils.getExtension(fileName);
        String name = UUID.randomUUID().toString();

        storeService.store(userId, name, bytes);

        Attachment attachment = new Attachment();
        attachment.setName(name);
        attachment.setTitle(title);
        attachment.setExtension(extension);
        attachment.setSize(bytes.length);
        attachment.setUser(user);
        attachment.setContentType(contentType);

        try {
            attachmentDao.save(attachment);
            
            logger.debug("File " + title + " for user with id " + userId + "successfully saved");
        } catch (Throwable e) {
            logger.error("File " + title + " isn't saved in db --> rollback it from file system");
            storeService.remove(userId, name);
            logger.debug("File " + title + " is successfully rolled back from file system");
        }

        return attachment;
    }

    @Override
    public List<Attachment> getAttachments(Long userId) {
        User user = userDao.get(userId);
        return attachmentDao.getAttachments(user);
    }

    @Override
    public Attachment get(Long userId, String name) {
        User user = userDao.get(userId);
        return attachmentDao.get(user, name);
    }

    @Override
    public InputStream getInputStream(Long userId, String name) throws IOException {
        return storeService.getInputStream(userId, name);
    }

    @Override
    public void removeFile(Long userId, String name) throws IOException {
        //TODO: write logs
        User user = userDao.get(userId);

        storeService.remove(userId, name);

        Attachment attachment = attachmentDao.get(user, name);
        attachmentDao.remove(attachment);

    }
}
