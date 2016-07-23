package com.levi9.service;

import java.util.List;

import com.levi9.model.Ad;

public interface AdService {

	
	Ad findOne(Long id);
	
	List<Ad> findAll();
	
	Ad save(Ad ad);
	
	Ad delete(Long id);
}
