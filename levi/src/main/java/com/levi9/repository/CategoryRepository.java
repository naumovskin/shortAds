package com.levi9.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.levi9.model.Ad;
import com.levi9.model.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	Category findOne(Long id);

	List<Category> findAll();

	Category save(Category cat);

	void delete(Long id);
	
	
}
