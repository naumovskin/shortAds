package com.naumovskin.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.naumovskin.model.Category;
import com.naumovskin.web.dto.CategoryDTO;
@Component
public class CategoryToCategoryDTO  implements Converter<Category, CategoryDTO>{

	@Override
	public CategoryDTO convert(Category category) {
		if (category == null) {
			return null;
		}

		CategoryDTO dto = new CategoryDTO ();

		dto.setId(category.getId());
		dto.setName(category.getName());
		dto.setDescription(category.getDescription());

		return dto;
	}

	public List<CategoryDTO> convert(List<Category> categories) {
		List<CategoryDTO> ret = new ArrayList<>();
			for (Category c : categories) {
				ret.add(convert(c));
			}
		return ret;
	}


}
