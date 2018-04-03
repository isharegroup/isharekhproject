package com.isharekh.domain.controllers.videos;

import com.isharekh.domain.models.news.News;
import com.isharekh.domain.repositories.news.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/*
Create By: Ron Rith
Create Date: 2/8/2018
*/
@Controller
public class VideoController {
    @Autowired
    private NewsRepository newsRepository;

    @RequestMapping(value = "/videos_main", method = RequestMethod.GET)
    public ModelAndView getIndex() {
        List<News> newsList = (List<News>) newsRepository.findAll();
        return new ModelAndView("videos_main", "newses", newsList);
    }
}
