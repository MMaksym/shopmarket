package com.ua.shop.mailmanager;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

public final class MailConfig {
    private static final String CONFIG_FILE_PATH = "/mail.properties";
    
    private static Logger logger = Logger.getLogger(MailConfig.class);
    
    private static Properties properties;

    private static final int DEFAULT_PORT = 25;
    
    static {
        properties = new Properties();
        try {
            properties.load(MailConfig.class.getResourceAsStream(CONFIG_FILE_PATH));
        } catch (IOException e) {
            logger.error("Main config properties aren't loaded from file " + CONFIG_FILE_PATH);
        }
    }

    private MailConfig() {}

    public static String getHost() {
        return properties.getProperty("smtp.host");
    }
    
    public static int getPort() {
        String prop = properties.getProperty("smtp.port");
        int port = DEFAULT_PORT;
        try {
            port = Integer.parseInt(prop);
        } catch (NumberFormatException e) {
            logger.error("Incorrect smtp port number " + prop);
        }
        return port;
    }

    public static String getUserName() {
        return properties.getProperty("username");
    }

    public static byte [] getPassword() {
        String pass = properties.getProperty("password");
        return pass.getBytes();
    }
}


