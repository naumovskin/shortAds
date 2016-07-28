package com.naumovskin.service;

import java.util.List;

import com.naumovskin.model.Ad;

public interface AdService {

	
	Ad findOne(Long id);
	
	List<Ad> findAll();
	
	Ad save(Ad ad);
	
	Ad delete(Long id);
}
