package com.naumovskin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naumovskin.model.User;
import com.naumovskin.repository.UserRepository;
import com.naumovskin.service.UserService;
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
	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	
}
