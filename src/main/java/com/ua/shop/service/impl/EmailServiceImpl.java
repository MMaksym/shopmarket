package com.ua.shop.service.impl;

import com.ua.shop.exception.EmailException;
import com.ua.shop.mailmanager.EmailType;
import com.ua.shop.mailmanager.MailConfig;
import com.ua.shop.mailmanager.Message;
import com.ua.shop.mailmanager.MessageConfig;
import com.ua.shop.service.EmailService;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private static Logger logger = Logger.getLogger(EmailServiceImpl.class);

    @Override
    public void send(Message message, MessageConfig config) throws EmailException {

        Email email;

        try {
            if (config.getEmailType() == EmailType.HTML) {
                HtmlEmail htmlEmail = new HtmlEmail();
                htmlEmail.setHtmlMsg(message.getBody());

                email = htmlEmail;
            } else {
                email= new SimpleEmail();
                email.setMsg(message.getBody());
            }

            setMailServer(email);

            email.setSubject(config.getSubject());
            String from = config.getFrom();

            String[] tokens = from.split(" ");

            if (tokens.length > 1) {
                String emailFrom = tokens[tokens.length - 1];
                String nameFrom = from.substring(0, from.length() - emailFrom.length());
                
                email.setFrom(emailFrom, nameFrom);
            } else {
                email.setFrom(from);
            }


            String[] recipients = config.getRecipients();
            for (String recipient : recipients) {
                email.addTo(recipient);
                logger.debug("Sending email to " + recipient);
            }

            email.send();
            logger.debug("Email is successfully sent");
        } catch (org.apache.commons.mail.EmailException e) {
            logger.error("Email is not sent", e);
            throw new EmailException(e);
        }


    }

    private void setMailServer(Email email) {
        email.setHostName(MailConfig.getHost());
        email.setSmtpPort(MailConfig.getPort());
        email.setAuthenticator(new DefaultAuthenticator(MailConfig.getUserName(), new String(MailConfig.getPassword())));
    }


}
