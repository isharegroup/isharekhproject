package com.isharekh.domain.controllers.home;

import com.isharekh.domain.models.news.NewsType;
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
 * Create Date: 02/24/2018.
 */
@Controller
@RequestMapping(value = "/home")
public class HomeController {
    @Autowired
    private NewsTypeService newsTypeService;

    private static Long i = 0L, j = 0L, k = 0L, l = 0L, m = 0L, n = 0L, o = 0L, p = 0L, q = 0L, r = 0L, s = 0L, t = 0L;

    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView getHome() {
        ModelAndView modelAndView = new ModelAndView("/home/home");
        List<NewsType> newsTypes = (List<NewsType>) newsTypeService.getAll();

        /*NewsType newsTypePHS = new NewsType();
        NewsType newsTypeHP = new NewsType();
        NewsType newsTypeNoP = new NewsType();
        NewsType newsTypePNS = new NewsType();
        NewsType newsTypeIS = new NewsType();

        List<NewsType> newsTypesPHS = new ArrayList<>();
        List<NewsType> newsTypesHP = new ArrayList<>();
        List<NewsType> newsTypesNoP = new ArrayList<>();
        List<NewsType> newsTypesPNS = new ArrayList<>();
        List<NewsType> newsTypesIS = new ArrayList<>();

        for(NewsType newsType: newsTypes){
            //get news type no parent
            if (newsType.getNewsType() == null) {
                newsTypeNoP = newsType;
                newsTypesNoP.add(newsTypeNoP);
            }else {
                //get news type has parent
                newsTypeHP = newsType;
                newsTypesHP.add(newsTypeHP);
            }
        }
        //loop news type has parent
        for (NewsType newsTypehp : newsTypesHP) {
            for (NewsType newsTypenp : newsTypesNoP) {
                if ((newsTypehp.getNewsType().getId() == newsTypenp.getId()) *//*&& (newsTypehp.getId() != newsTypenp.getId())*//*) {
                    getItoratorMenu(newsTypenp);
                    //parent menu
                    newsTypePHS = newsTypenp;
                    newsTypesPHS.add(newsTypePHS);
                    //sub menu
                    newsTypeIS = newsTypehp;
                    newsTypesIS.add(newsTypeIS);
                } else {
                    newsTypePNS = newsTypenp;
                    newsTypesPNS.add(newsTypePNS);
                }
            }
        }*/

        modelAndView.addObject("newstypes",newsTypes);
        /*modelAndView.addObject("newsTypesPHS",newsTypesPHS);
        modelAndView.addObject("newsTypesPNS",newsTypesPNS);
        modelAndView.addObject("newsTypesIS",newsTypesIS);*/

        return modelAndView;
    }
    private void getItoratorMenu(NewsType newsTypehp){
        if (i == 0L || i == newsTypehp.getId()) {
            i = newsTypehp.getId();
        } else if (i != 0L || (j == 0L || j == newsTypehp.getId())) {
            j = newsTypehp.getId();
        } else if (j != 0L || (k == 0L || k == newsTypehp.getId())) {
            k = newsTypehp.getId();
        } else if (k != 0L || (l == 0L || l == newsTypehp.getId())) {
            l = newsTypehp.getId();
        } else if (l != 0L || (m == 0L || m == newsTypehp.getId())) {
            m = newsTypehp.getId();
        } else if (m != 0L || (n == 0L || n == newsTypehp.getId())) {
            n = newsTypehp.getId();
        } else if (n != 0L || (o == 0L || o == newsTypehp.getId())) {
            o = newsTypehp.getId();
        } else if (o != 0L || (p == 0L || p == newsTypehp.getId())) {
            p = newsTypehp.getId();
        } else if (p != 0L || (q == 0L || q == newsTypehp.getId())) {
            q = newsTypehp.getId();
        } else if (q != 0L || (r == 0L || r == newsTypehp.getId())) {
            r = newsTypehp.getId();
        } else if (r != 0L || (s == 0L || s == newsTypehp.getId())) {
            s = newsTypehp.getId();
        } else if (s != 0L || (t == 0L || t == newsTypehp.getId())) {
            t = newsTypehp.getId();
        }
    }

}
