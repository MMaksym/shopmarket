package com.ua.shop;


import com.ua.shop.model.MyEntity;
import com.ua.shop.service.MyEntityService;
import com.ua.shop.service.dao.MyEntityDao;
import com.ua.shop.service.dao.impl.MyEntityDaoImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Author: Lotus
 * Date: 03.02.12
 */
public class Main {

    private static ApplicationContext context;

    static {
        context = new ClassPathXmlApplicationContext(
                "classpath:spring/service.xml",
                "classpath:spring/data.xml");
    }

    public static void main(String args[]) {


        MyEntityService service = context.getBean(MyEntityService.class);

        service.test();


        //askfdjasdf


    }
}