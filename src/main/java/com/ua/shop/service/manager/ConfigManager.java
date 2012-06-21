package com.ua.shop.service.manager;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Service
public class ConfigManager {

    private static Logger logger = Logger.getLogger(ConfigManager.class);
    
    private static final Properties properties;
    
    static {
        properties = new Properties();

        InputStream inputStream = ConfigManager.class.getResourceAsStream("/configuration.properties");

        try {
            properties.load(inputStream);
        } catch (IOException e) {
            logger.error("Problems with load configuration properties");
        }
    }
    

    public String getValue(String key) {
        return properties.getProperty(key);
    }
    
    public static enum Key{
        STORAGE_DIR("storage.dir"),
        TWITPIC_USERNAME("twitpic.username"),
        TWITPIC_PASSWORD("twitpic.password");

        private String key;

        private Key(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }
    }
    
}
