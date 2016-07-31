package com.naumovskin.service;

import java.util.List;

import com.naumovskin.model.Category;

public interface CategoryService {

	Category findOne(Long id);
	
	List<Category> findAll();
	
	Category save(Category category);
	
	Category delete(Long id);
	
//	List<Category> findAllOrderByCategoryNameAsc();
	
	
}
