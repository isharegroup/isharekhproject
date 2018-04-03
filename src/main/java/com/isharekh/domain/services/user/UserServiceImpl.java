package com.isharekh.domain.services.user;

import com.isharekh.domain.models.user_security.Role;
import com.isharekh.domain.models.user_security.SecUser;
import com.isharekh.domain.repositories.user.RoleRepository;
import com.isharekh.domain.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/*
Create By: Ron Rith
Create Date: 1/2/2018
*/
@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public SecUser findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void saveUser(SecUser secUser) {
		secUser.setPassword(bCryptPasswordEncoder.encode(secUser.getPassword()));
        secUser.setActive(1);
        Role userRole = roleRepository.findByRole("ADMIN");
        secUser.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(secUser);
	}

	@Override
	public List<SecUser> findAllUsers() {
		return (List<SecUser>) userRepository.findAll();
	}

	@Override
	public SecUser getUserById(Long id) {
		return (SecUser) userRepository.findOne(id);
	}

	@Override
	public Page<SecUser> findAllPageable(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.delete(id);
	}

	@Override
	public void updateUser(SecUser secUser, Long id) {
		secUser.setId(id);
		userRepository.save(secUser);
	}

	@Override
	@Transactional
	public SecUser findByEmailAndPassword(String email, String password) {
		SecUser secUser = null;
		if ((!email.isEmpty() && email != null) && (!password.isEmpty() && password != null)) {
			secUser = userRepository.findByEmailAndPassword(email,password);
			return secUser;
		}
		return null;
	}


}
