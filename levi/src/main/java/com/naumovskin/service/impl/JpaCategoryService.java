package com.naumovskin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naumovskin.model.Category;
import com.naumovskin.repository.CategoryRepository;
import com.naumovskin.service.CategoryService;
@Service
public class JpaCategoryService implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override	
	public Category findOne(Long id) {
		return categoryRepository.findOne(id);
	}
	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}
	@Override
	public Category save(Category category) {
		return categoryRepository.save(category);
	}
	@Override
	public Category delete(Long id) {
		Category category = categoryRepository.findOne(id);
		if (category == null) {
			throw new IllegalArgumentException("Removing nonexisting category");
		}
		categoryRepository.delete(id);
		return category;
	}
//	@Override
//	public List<Category> findAllOrderByCategoryNameAsc() {
		
//		return categoryRepository.findAllOrderByCategoryNameAsc();
//	}

}
		