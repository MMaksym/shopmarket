package com.ua.shop.service.impl;

import com.ua.shop.service.StoreService;
import com.ua.shop.service.manager.ConfigManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class FileSystemStoreService implements StoreService{

    private static final String DEFAULT_FOLDER_NAME = "default";

    @Autowired
    private ConfigManager configManager;

    @Override
    public void store(Long userId, String name, byte[] bytes) throws IOException{
        
        File userDir = getUserFolder(userId);

        File newFile = new File(userDir, name);
        if (!newFile.exists()) {
            boolean create = newFile.createNewFile();
        }

        BufferedOutputStream bout = null;
        try {
            bout = new BufferedOutputStream(new FileOutputStream(newFile));

            bout.write(bytes);
        } finally {
            if (bout != null) {
                try {
                    bout.close();
                } catch (IOException ignored) {
                }
            }
        }
        
    }

    @Override
    public void remove(Long userId, String name) throws IOException {
        File userDir = getUserFolder(userId);
        File removeFile = new File(userDir, name);

        if (removeFile.exists()) {
            boolean delete = removeFile.delete();
        }
    }

    @Override
    public InputStream getInputStream(Long userId, String name) throws IOException {
        File userFolder = getUserFolder(userId);

        File file = new File(userFolder, name);

        return new BufferedInputStream(new FileInputStream(file));
    }

    private File getUserFolder(Long userId) {
        File  dir = getStoreForler();
        File userDir;
        userDir = new File(dir, userId != null ? String.valueOf(userId) : DEFAULT_FOLDER_NAME);
        if (!userDir.exists()) {
            boolean mkdirs = userDir.mkdirs();
        }
        return userDir;
    }

    private File getStoreForler() {
        File dir;
        dir = new File(configManager.getValue(ConfigManager.Key.STORAGE_DIR.getKey()));
        if (!dir.exists()) {
            boolean mkdirs = dir.mkdirs();
        }
        return dir;
    }
}
