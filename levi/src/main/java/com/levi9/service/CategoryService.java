package com.levi9.service;

import java.util.List;

import com.levi9.model.Category;

public interface CategoryService {

	Category findOne(Long id);
	
	List<Category> findAll();
	
	Category save(Category category);
	
	Category delete(Long id);
	
	
}
