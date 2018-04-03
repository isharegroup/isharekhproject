package com.isharekh.domain.controllers.fronts;

import com.isharekh.domain.models.authors.Author;
import com.isharekh.domain.models.news.News;
import com.isharekh.domain.models.news.NewsType;
import com.isharekh.domain.repositories.author.AuthorRepository;
import com.isharekh.domain.services.news.NewsService;
import com.isharekh.domain.services.newstype.NewsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by : Ron Rith
 * Create Date: 03/30/2018.
 */
@Controller
@RequestMapping(value = "/front")
public class VideosController {
    @Autowired
    private NewsTypeService newsTypeService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private AuthorRepository authorRepository;

    @RequestMapping(value = "/videos",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView getHome() {
        ModelAndView modelAndView = new ModelAndView("/home/home");

        List<NewsType> newsTypes = (List<NewsType>) newsTypeService.getAll();
        List<News> news = (List<News>)  newsService.findAll();

        NewsType menu0 = null;
        NewsType menu1 = null;
        NewsType menu2 = null;
        List<News> newsList1 = new ArrayList<>();
        List<News> newsList2 = new ArrayList<>();

        if (newsTypes != null && news != null) {
            for (NewsType newsType : newsTypes) {
                for (News news1 : news) {
                    if (newsType.getId() == 6) {
                        NewsType newsType1 = new NewsType();
                        newsType1.setId(newsType.getId());
                        newsType1.setDes(newsType.getDes());
                        menu0 = newsType1;
                    }
                    if (newsType.getId() == 8 && news1.getNewsType().getId() == 8) {
                        NewsType newsType1 = new NewsType();
                        newsType1.setId(newsType.getId());
                        newsType1.setDes(newsType.getDes());
                        menu1 = newsType1;

                        if (news1.getName() != null) {
                            News newsItem = new News();
                            newsItem.setId(news1.getId());
                            newsItem.setDec(news1.getDec());
                            newsItem.setName(news1.getName());
                            newsItem.setAuthor(news1.getAuthor());
                            newsItem.setNewsType(menu1);
                            newsList1.add(newsItem);
                        }

                    }

                    if (newsType.getId() == 9 && news1.getNewsType().getId() == 9) {
                        NewsType newsType1 = new NewsType();
                        newsType1.setId(newsType.getId());
                        newsType1.setDes(newsType.getDes());
                        menu2 = newsType1;

                        if (news1.getName() != null) {
                            News newsItem = new News();
                            newsItem.setId(news1.getId());
                            newsItem.setDec(news1.getDec());
                            newsItem.setName(news1.getName());
                            newsItem.setAuthor(news1.getAuthor());
                            newsItem.setNewsType(newsType1);
                            newsList2.add(newsItem);
                        }

                    }
                }
            }
        }
        modelAndView.addObject("newstypes6", menu0);
        modelAndView.addObject("newstypes8", menu1);
        modelAndView.addObject("newsitemtype8", newsList1);
        modelAndView.addObject("newstypes9", menu2);
        modelAndView.addObject("newsitemtype9", newsList2);
        return modelAndView;
    }
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    private String frontVideos() {
        return "/fronts/detail";
    }

    private String getMenuList(List<NewsType> newsTypes) {
        List<NewsType> newsTypeList = getNewsType();
        for (NewsType newsType : newsTypeList) {
            if (newsType.getId() == 8) {
                return newsType.getDes();
            }
            if (newsType.getId() == 9) {
                return newsType.getDes();
            }
        }
        return null;
    }

    private List<NewsType> getNewsType() {
        List<NewsType> newsTypes = (List<NewsType>) newsTypeService.getAll();
        return newsTypes;
    }
}
