package com.levi9.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.levi9.model.Category;
import com.levi9.service.CategoryService;
import com.levi9.web.dto.CategoryDTO;

@Component
public class CategoryDTOToCategory implements Converter<CategoryDTO, Category>{

	@Autowired
	CategoryService categoryService;

	@Override
	public Category convert(CategoryDTO dto) {
		Category c = new Category();

		if (dto.getId() != null) {
			c = categoryService.findOne(dto.getId());

			if (c == null) {
				throw new IllegalStateException("Tried to " + "modify a non-existant activity");
			}
		}

		c.setId(dto.getId());
		c.setName(dto.getName());
		c.setDescription(dto.getDescription());
		
		return c;
	}

	public List<Category> convert(List<CategoryDTO> dtoCategories) {
		List<Category> categories = new ArrayList<>();

		for (CategoryDTO dto : dtoCategories) {
			categories.add(convert(dto));
		}

		return categories;
	}

}
