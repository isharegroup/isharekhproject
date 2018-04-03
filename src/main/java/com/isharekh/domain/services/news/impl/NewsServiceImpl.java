package com.isharekh.domain.services.news.impl;

import com.isharekh.domain.models.news.News;
import com.isharekh.domain.repositories.news.NewsRepository;
import com.isharekh.domain.services.news.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
Create By: Ron Rith
Create Date: 1/20/2018
*/
@Service
public class NewsServiceImpl implements NewsService{
    @Autowired
    private NewsRepository newsRepository;

    @Override
    public Page<News> findAllPageable(Pageable pageable) {
        return newsRepository.findAll(pageable);
    }

    @Override
    public List<News> getByName(String name) {
        return newsRepository.getByNameContaining(name);
    }

    @Override
    public List<News> findAll() {
        return (List<News>) newsRepository.findAll();
    }
}
