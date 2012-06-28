package com.ua.shop.service;

import com.ua.shop.exception.EmailException;
import com.ua.shop.mailmanager.Message;
import com.ua.shop.mailmanager.MessageConfig;

public interface EmailService {

    void send(Message message, MessageConfig config) throws EmailException;

}
