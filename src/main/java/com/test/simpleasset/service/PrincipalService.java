package com.test.simpleasset.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.test.simpleasset.dao.UserDao;
import com.test.simpleasset.model.User;

@Service
public class PrincipalService{

	@Autowired
	private UserDao userDao;
	
	public User getPrinciple() {
		final long id = Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
		final Optional<User> userOptional = userDao.getById(id); 
		if(userOptional.isPresent()) {
			return userOptional.get();
		}
		throw new RuntimeException("Invalid login");
	}

}
