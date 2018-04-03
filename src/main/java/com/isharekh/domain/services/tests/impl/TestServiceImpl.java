package com.isharekh.domain.services.tests.impl;

import com.isharekh.domain.models.Test;
import com.isharekh.domain.repositories.TestRepository;
import com.isharekh.domain.services.tests.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
Create By: Ron Rith
Create Date: 2/10/2018
*/
@Service
@Transactional
public class TestServiceImpl implements TestService {
    @Autowired
    private TestRepository testRepository;

    @Override
    public List<Test> getAll() {
        List<Test> tests = (List<Test>) testRepository.findAll();
        if (tests == null || tests.size() < 5) {
            for (int i = 0; i < 5; i++) {
                Test test = new Test();
                test.setName("Hello Test " + i);
                testRepository.save(test);
            }
        }
        return tests;
    }
}
