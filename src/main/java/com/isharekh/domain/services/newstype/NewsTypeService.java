package com.isharekh.domain.services.newstype;

import com.isharekh.domain.cores.EntityService;
import com.isharekh.domain.models.news.NewsType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/*
Create By: Ron Rith
Create Date: 1/20/2018
*/
public interface NewsTypeService extends EntityService {
    Page<NewsType> findAllPageable(Pageable pageable);
}
