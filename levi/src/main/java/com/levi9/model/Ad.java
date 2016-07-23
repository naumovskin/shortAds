package com.levi9.model;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Id;


@Entity
@Table(name="tbl_ad")
public class Ad {

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="categoryName")
	private String categoryName;
	
	@Column(name="name")
	private String name;
	
	@Column(name="created")
	private java.util.Date created;
	
	@Column(name="passed")
	private java.util.Date passed;	
	
	@Column(name="authorUsername")
	private String authorUsername;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Category category;

	
	public Ad() {
		super();
	}

	public Ad(Long id, String categoryName, String name, Date created, Date passed, String authorUsername,Category category) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.name = name;
		this.created = created;
		this.passed = passed;
		this.authorUsername = authorUsername;
		this.category = category;	}

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
		if(category != null && !category.getAds().contains(this)){
			category.getAds().add(this);
		}
	}
	
	
	
}
