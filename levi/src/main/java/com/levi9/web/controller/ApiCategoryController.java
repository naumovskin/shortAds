package com.levi9.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.levi9.model.Category;
import com.levi9.service.CategoryService;
import com.levi9.support.CategoryDTOToCategory;
import com.levi9.support.CategoryToCategoryDTO;
import com.levi9.web.dto.CategoryDTO;

@RestController
@RequestMapping(value = "/api/categories")
public class ApiCategoryController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CategoryDTOToCategory toCategory;

	@Autowired
	private CategoryToCategoryDTO toDTO;

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<CategoryDTO>> getCategories() {// promenjena paginacija na false


		List<Category> categories = categoryService.findAll();

		return new ResponseEntity<>(toDTO.convert(categories), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<CategoryDTO> getCategory(@PathVariable Long id) {
		Category cat = categoryService.findOne(id);
		if (cat == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(cat), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<CategoryDTO> delete(@PathVariable Long id) {
		Category deleted = categoryService.delete(id);

		return new ResponseEntity<>(toDTO.convert(deleted), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<CategoryDTO> add(@RequestBody CategoryDTO newCategory) {

		Category savedCategory = categoryService.save(toCategory.convert(newCategory));

		return new ResponseEntity<>(toDTO.convert(savedCategory), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<CategoryDTO> edit(@RequestBody CategoryDTO cat, @PathVariable Long id) {

		if (id != cat.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Category persisted = categoryService.save(toCategory.convert(cat));

		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}

}
