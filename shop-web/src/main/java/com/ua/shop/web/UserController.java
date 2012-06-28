package com.ua.shop.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Author: Lotus
 * Date: 25.02.12
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @RequestMapping(method = RequestMethod.GET)
    public String view() {
        return "user/view";
    }
}
