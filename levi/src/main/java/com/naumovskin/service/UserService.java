package com.naumovskin.service;

import java.util.List;

import com.naumovskin.model.User;

public interface UserService {

	User findOne(Long id);
	
	List<User> findAll();
	
	User  save(User user );
	
	User  delete(Long id);
	
	User findByUsername(String username);
	
	
}
