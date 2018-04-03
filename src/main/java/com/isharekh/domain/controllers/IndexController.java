package com.isharekh.domain.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
Create By: Ron Rith
Create Date: 2/15/2018
*/
@Controller
public class IndexController {
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    private String goIndex() {
        return "index";
    }
}
