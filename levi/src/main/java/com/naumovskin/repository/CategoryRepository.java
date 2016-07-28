package com.naumovskin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.naumovskin.model.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	Category findOne(Long id);

	List<Category> findAll();

	Category save(Category cat);

	void delete(Long id);
	
	
}
