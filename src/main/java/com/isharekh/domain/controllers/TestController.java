package com.isharekh.domain.controllers;

/*
Create By: Ron Rith
Create Date: 2/10/2018
*/

import com.isharekh.domain.models.Test;
import com.isharekh.domain.services.tests.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/tests")
public class TestController {
    @Autowired
    private TestService testService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Test> getAll() {
        return testService.getAll();
    }
}
