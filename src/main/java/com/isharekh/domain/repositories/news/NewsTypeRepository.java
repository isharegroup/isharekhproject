package com.isharekh.domain.repositories.news;

import com.isharekh.domain.models.news.NewsType;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by : Ron Rith
 * Create Date: 01/12/2018.
 */
public interface NewsTypeRepository extends PagingAndSortingRepository<NewsType,Long> {
    NewsType findByDesEn(String desEn);
}
