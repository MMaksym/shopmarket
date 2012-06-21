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
@ContextConfiguration(locations = {"classpath:spring/data.xml", "classpath:spring/service.xml", "classpath:spring/search.xml"})
public class MainManagerTest {

    @Autowired
    private MailManager mailManager;

    @Test
    public void composeEmailTest() {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("userFirstName", "max");
        parameters.put("userLastName", "Mukhanov");

        String body = null;
        try {
            body = mailManager.composeEmail("test", parameters);
            Assert.assertNotNull(body);
            Assert.assertEquals("max Mukhanov", body);
        } catch (TemplateException e) {
            Assert.fail("Email isn't composed");
        }


    }


}
