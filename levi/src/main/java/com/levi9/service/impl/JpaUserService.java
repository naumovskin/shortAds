package com.levi9.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.levi9.model.User;
import com.levi9.repository.UserRepository;
import com.levi9.service.UserService;
@Service
public class JpaUserService implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Override
	public User findOne(Long id) {
		return userRepository.findOne(id);
	}
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}
	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User delete(Long id) {
		User user = userRepository.findOne(id);
		if (user == null) {
			throw new IllegalArgumentException("Removing nonexisting user");
		}
		userRepository.delete(id);
		return user;
	}

	
}
