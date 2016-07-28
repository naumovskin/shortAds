package com.naumovskin.model;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name="tbl_category")
public class Category {

	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@OneToMany(mappedBy="category",cascade=CascadeType.REMOVE)
	private List<Ad> ads = new ArrayList<>();
	
	
	
	public void getAd(Ad ad){
        this.ads.add(ad);
        if(ad.getCategory()!=this){
            ad.setCategory(this);
        }
    }
	public void removeAd(Ad ad){
		ad.setCategory(null);
		ads.remove(ad);
	}

	public Category() {
		super();
	}

	public Category(Long id, String name, String description,List<Ad> ads) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.ads = ads;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Ad> getAds() {
		return ads;
	}

	public void setAds(List<Ad> ads) {
		this.ads = ads;
	}
	
	
	
	
}
