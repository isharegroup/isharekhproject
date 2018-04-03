package com.isharekh.domain.repositories.news;

import com.isharekh.domain.models.news.News;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by : Ron Rith
 * Create Date: 01/12/2018.
 */
@Repository
public interface NewsRepository extends PagingAndSortingRepository<News,Long> {
  //@Query(value="SELECT *FROM news WHERE name LIKE %?1%",nativeQuery=true)
  List<News> getByNameContaining(String name);
}
