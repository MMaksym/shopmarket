package com.ua.shop.mailmanager;

import com.ua.shop.exception.TemplateException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/data.xml", "classpath:spring/service.xml"})
public class MainManagerTest {

    private static final String TEMPLATE_NAME = "test";

    @Autowired
    private MailManager mailManager;

    @Test
    public void composeEmail() {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("userFirstName", "max");
        parameters.put("userLastName", "Mukhanov");

        String body = null;
        try {
            body = mailManager.composeEmail(TEMPLATE_NAME, parameters);
            Assert.assertNotNull(body);
            Assert.assertEquals("max Mukhanov", body);
        } catch (TemplateException e) {
            Assert.fail("Email isn't composed");
        }
    }

    @Test
    public void nullEmailParameters() {
        Map<String, Object> parameters = new HashMap<String, Object>();

        try {
            String result = mailManager.composeEmail(TEMPLATE_NAME, parameters);
            Assert.assertEquals("$userFirstName $userLastName", result);
        } catch (TemplateException e) {
            Assert.fail("Email isn't composed");
        }
    }


}
