package com.isharekh.domain.services.news;

import com.isharekh.domain.models.news.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/*
Create By: Ron Rith
Create Date: 1/20/2018
*/
public interface NewsService {
    Page<News> findAllPageable(Pageable pageable);

    List<News> getByName(String title);

    List<News> findAll();
}
