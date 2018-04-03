package com.isharekh.domain.controllers.news.controller;

import com.isharekh.domain.models.news.NewsType;
import com.isharekh.domain.models.videos.Video;
import com.isharekh.domain.services.newstype.NewsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by : Ron Rith
 * Create Date: 03/21/2018.
 */
@Controller
@RequestMapping(value = "/newstype")
public class NewstypeController {
    @Autowired
    private NewsTypeService newsTypeService;

    @RequestMapping(method = RequestMethod.GET)
    private ModelAndView getIndexNewsType(){
        ModelAndView modelAndView = new ModelAndView("/admin/newstype/index");

        List<NewsType> newsTypes = (List<NewsType>) newsTypeService.getAll();
        modelAndView.addObject("newstype",newsTypes);

        return modelAndView;
    }
    @RequestMapping(value = "/create",method = RequestMethod.GET)
    private ModelAndView createNewstype(ModelMap model){
        ModelAndView modelAndView = new ModelAndView("/admin/newstype/create");
        model.addAttribute("newstype",new NewsType());
        return modelAndView;
    }
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    private String saveNewstype(@Valid NewsType newsType){
        if (newsType != null) {
            newsTypeService.save(newsType);
            return "redirect:" + "/newstype";
        }
        return "redirect:" + "/newstype/create";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    private String deleteNewsType(@PathVariable("id") Long id) {
        if (id != null) {
            newsTypeService.delete(id);
        }
        return "redirect:" + "/newstype";
    }

    @RequestMapping(value = "/edit/{id}")
    private ModelAndView editNewstype(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("/admin/newstype/edit");
        if (id != null) {
            NewsType newsType = (NewsType) newsTypeService.getById(id);
            modelAndView.addObject("newstype", newsType);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    private String updateNewstype(@Valid NewsType newsType,@PathVariable("id") Long id){
        if (id != null && newsType != null) {
            newsTypeService.update(newsType, id);
            return "redirect:" + "/newstype";
        }
        return "redirect:" + "/newstype/edit/" + id;
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    private ModelAndView detailNewstype(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("/admin/newstype/detail");
        if (id != null) {
            NewsType newsType = (NewsType) newsTypeService.getById(id);
            modelAndView.addObject("newstype", newsType);
        }
        return modelAndView;
    }
}
