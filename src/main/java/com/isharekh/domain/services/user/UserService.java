package com.isharekh.domain.services.user;

import com.isharekh.domain.models.user_security.SecUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/*
Create By: Ron Rith
Create Date: 1/2/2018
*/
public interface UserService {
	SecUser findUserByEmail(String email);

	void saveUser(SecUser secUser);

	List<SecUser> findAllUsers();

	SecUser getUserById(Long id);

	Page<SecUser> findAllPageable(Pageable pageable);

	void deleteUser(Long id);

	void updateUser(SecUser secUser, Long id);

	SecUser findByEmailAndPassword(String email, String password);

}
