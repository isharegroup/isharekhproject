package com.isharekh.domain.repositories.user;

import com.isharekh.domain.models.user_security.SecUser;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/*
Create By: Ron Rith
Create Date: 1/2/2018
*/
@Repository("userRepository")
public interface UserRepository extends PagingAndSortingRepository<SecUser, Long> {
	SecUser findByEmail(String email);
	SecUser findByEmailAndPassword(String email, String password);
}
