package com.isharekh.domain.controllers.news.controller;

import com.isharekh.domain.models.news.News;
import com.isharekh.domain.repositories.news.NewsRepository;
import com.isharekh.routs.MainURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/*
Create By: Ron Rith
Create Date: 1/26/2018
*/
@Controller
@RequestMapping(MainURL.NEWSES)
public class NewsController {
    @Autowired
    private NewsRepository newsRepository;

    @RequestMapping(method = RequestMethod.GET)
    private ModelAndView getNews() {
        List<News> newsList = (List<News>) newsRepository.findAll();
        return new ModelAndView("/pages/news/newses", "newses", newsList);
    }
}
