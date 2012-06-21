package com.ua.shop.web.servlet;

import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class HomeServlet extends HttpServlet{

    private static Logger logger = Logger.getLogger(HomeServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        if (logger.isDebugEnabled()) {
            Enumeration params = config.getInitParameterNames();
            String s = "";
            while (params.hasMoreElements()) {
                s += " " +  params.nextElement();
            }
            logger.debug(s);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("sss");
    }
}
