package com.isharekh.domain.repositories;

import com.isharekh.domain.models.Test;
import org.springframework.data.repository.PagingAndSortingRepository;

/*
Create By: Ron Rith
Create Date: 2/10/2018
*/
public interface TestRepository extends PagingAndSortingRepository<Test,Long> {
}
