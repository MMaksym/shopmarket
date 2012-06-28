package com.ua.shop.mailmanager;

import com.ua.shop.exception.TemplateException;
import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.Map;

@Service
public class MailManager{

    private static Logger logger = Logger.getLogger(MailManager.class);

    @Autowired
    private VelocityEngine velocityEngine;

    public String composeEmail(String templateName, Map<String, Object> parameters) throws TemplateException{
        logger.debug("Start compose email with template name - " + templateName);
        StringWriter writer = new StringWriter();

        try {
            Template template = velocityEngine.getTemplate(templateName + ".vm");
            Context context = new VelocityContext(parameters);

            template.merge(context, writer);
        } catch (Exception e) {
            String cause = "Template " + templateName + " compose failed";
            logger.error(cause, e);
            throw new TemplateException(cause, e);
        }

        String body = writer.toString();

        logger.debug("Merged template \'" + templateName +"\'\r\n" + body);

        return body;
    }


}
