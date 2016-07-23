package com.levi9.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.levi9.model.Category;
import com.levi9.repository.CategoryRepository;
import com.levi9.service.CategoryService;
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

}
		