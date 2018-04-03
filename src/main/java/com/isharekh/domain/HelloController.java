package com.isharekh.domain;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
Create By: Ron Rith
Create Date: 2/8/2018
*/
@Controller
public class HelloController {
    @RequestMapping(value = {"/", "/hello"}, method = {RequestMethod.GET,RequestMethod.POST})
    public String getHello(){
        return "hello";
    }
}
