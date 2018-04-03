package com.isharekh.domain.repositories.facebook;

import com.isharekh.domain.models.user_facebook.SecFBUser;
import org.springframework.data.repository.PagingAndSortingRepository;

/*
Create By: Ron Rith
Create Date: 1/22/2018
*/
public interface UserFBRepository extends PagingAndSortingRepository<SecFBUser,Long>{
}
