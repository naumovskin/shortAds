package com.levi9.service;

import java.util.List;

import com.levi9.model.User;

public interface UserService {

	User findOne(Long id);
	
	List<User> findAll();
	
	User  save(User user );
	
	User  delete(Long id);
	
	
}
