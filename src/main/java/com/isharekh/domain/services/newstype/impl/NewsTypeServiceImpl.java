package com.isharekh.domain.services.newstype.impl;

import com.isharekh.domain.models.news.NewsType;
import com.isharekh.domain.repositories.news.NewsTypeRepository;
import com.isharekh.domain.services.newstype.NewsTypeService;
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
@Transactional
public class NewsTypeServiceImpl implements NewsTypeService{
    @Autowired
    private NewsTypeRepository newsTypeRepository;

    @Override
    public Page<NewsType> findAllPageable(Pageable pageable) {
        return newsTypeRepository.findAll(pageable);
    }

    @Override
    public List<NewsType> getAll() {
        return (List<NewsType>) newsTypeRepository.findAll();
    }

    @Override
    public void save(Object o) {
        if (o != null) {
            newsTypeRepository.save((NewsType) o);
        }
    }

    @Override
    public List getAllTShort() {
        return null;
    }

    @Override
    public Object getById(Long id) {
        NewsType newsType = null;
        if (id != null) {
            newsType = (NewsType) newsTypeRepository.findOne(id);
        }
        return newsType;
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            newsTypeRepository.delete(id);
        }
    }

    @Override
    public void update(Object o, Long id) {
        NewsType newsType = (NewsType) o;
        if (o != null && id != null) {
            newsType.setId(id);
            newsTypeRepository.save(newsType);
        }
    }

}
