package com.levi9.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.levi9.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findOne(Long id);
	
	List<User> findAll();
	
	User save(User user);
	
}
