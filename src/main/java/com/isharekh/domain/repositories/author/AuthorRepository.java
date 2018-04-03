package com.isharekh.domain.repositories.author;

import com.isharekh.domain.models.authors.Author;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by : Ron Rith
 * Create Date: 01/12/2018.
 */
public interface AuthorRepository extends PagingAndSortingRepository<Author,Long> {
    Author findByEmail(String email);
}
