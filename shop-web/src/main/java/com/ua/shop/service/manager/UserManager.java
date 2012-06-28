package com.ua.shop.service.manager;

import com.ua.shop.exception.EmailException;
import com.ua.shop.exception.TemplateException;
import com.ua.shop.mailmanager.EmailType;
import com.ua.shop.mailmanager.MailManager;
import com.ua.shop.mailmanager.Message;
import com.ua.shop.mailmanager.MessageConfig;
import com.ua.shop.model.ForgotHash;
import com.ua.shop.model.User;
import com.ua.shop.service.EmailService;
import com.ua.shop.service.UserService;
import com.ua.shop.service.dao.ForgotHashDao;
import com.ua.shop.web.form.ChangePasswordForm;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Author: Lotus
 * Date: 13.03.12
 */

@Service
public class UserManager {

    private static Logger logger = Logger.getLogger(UserManager.class);

    private static final int EXPIRE_TIME_LIMIT = 1000 * 60 * 60 * 24; //1 day

    private static final String FORGOT_PASSWORD_TEMPLATE = "forgot_password";
    private static final String FORGOT_PASSWORD_SUBJECT = "Change your password";

    @Autowired
    private UserService userService;

    @Autowired
    private ForgotHashDao forgotHashDao;
    
    @Autowired
    private MailManager mailManager;

    @Autowired
    private EmailService emailService;

    public void forgetPasswordRequest(String email, String serverUrl) throws TemplateException, EmailException {
        logger.debug("Forgot password request for user with email " + email);
        User user = userService.getUserByEmail(email);

        if (user == null) {
            logger.error("User with email " + email + " isn't found");
            return;
        }
        
        ForgotHash forgotHash = new ForgotHash();
        String hash = UUID.randomUUID().toString();
        logger.debug(hash + " hash generated for user with email " + email);
        Date currentDate = new Date();
        forgotHash.setExpireDate(new Date(currentDate.getTime() + EXPIRE_TIME_LIMIT));
        forgotHash.setHash(hash);
        forgotHash.setUserId(user.getId());

        forgotHashDao.save(forgotHash);

        String forgotUrl = serverUrl + "?email=" + email + "&hash=" + hash;
        
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("url", forgotUrl);

        String body = mailManager.composeEmail(FORGOT_PASSWORD_TEMPLATE, parameters);

        sendForgotPasswordEmail(body, email);
        
        logger.debug("Forgot password email is successfully sent to " + email);
    }


    public boolean changeForgotPassword(String email, String newPassword, String hash) {
        User user = userService.getUserByEmail(email);

        if (user == null) {
            logger.error("User with email " + email + " isn't found");
            return false;
        }

        ForgotHash forgotHash = forgotHashDao.get(hash, user.getId());
        Date currentDate = getCurrentDate();
        if (forgotHash.isValid() && currentDate.compareTo(forgotHash.getExpireDate()) <= 0) {
            userService.updatePassword(user.getId(), newPassword);
            forgotHash.setValid(false);
            forgotHashDao.update(forgotHash);
            return true;
        } else {
            return false;
        }
    }

    private void sendForgotPasswordEmail(String body, String email) throws EmailException {
        Message message = new Message(body);
        MessageConfig config = new MessageConfig();
        config.setEmailType(EmailType.HTML);
        //TODO change email from
        config.setFrom("mukhanov_max@mail.ru");
        config.setSubject(FORGOT_PASSWORD_SUBJECT);
        config.setRecipients(new String[]{email});

        emailService.send(message, config);
    }

    public boolean isValidForgotPasswordRequest(ChangePasswordForm form) {
        boolean isValid = false;

        User user = userService.getUserByEmail(form.getEmail());

        if (user != null) {
            ForgotHash forgotHash = forgotHashDao.get(form.getHash(), user.getId());

            isValid = forgotHash.isValid() && forgotHash.getExpireDate().compareTo(getCurrentDate()) > 0;
        }

        return isValid;
    }


    private Date getCurrentDate() {
        return new Date();
    }

}
