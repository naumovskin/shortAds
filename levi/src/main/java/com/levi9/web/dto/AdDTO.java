package com.levi9.web.dto;

import com.levi9.model.Category;

public class AdDTO {

	
	private Long id;
	
	private String categoryName;
	
	private String name;
	
	private java.util.Date created;
	
	private java.util.Date passed;	
	
	private String authorUsername;
	
	private Category category;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public java.util.Date getCreated() {
		return created;
	}

	public void setCreated(java.util.Date created) {
		this.created = created;
	}

	public java.util.Date getPassed() {
		return passed;
	}

	public void setPassed(java.util.Date passed) {
		this.passed = passed;
	}

	public String getAuthorUsername() {
		return authorUsername;
	}

	public void setAuthorUsername(String authorUsername) {
		this.authorUsername = authorUsername;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	
	
}
