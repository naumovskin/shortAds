package com.naumovskin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naumovskin.model.Ad;
import com.naumovskin.repository.AdRepository;
import com.naumovskin.service.AdService;
@Service
public class JpaAdService implements AdService{

	
	@Autowired
	private AdRepository adRepository;
		
	@Override
	public Ad findOne(Long id) {
		return adRepository.findOne(id);
	}
	@Override
	public List<Ad> findAll() {
		// TODO Auto-generated method stub
		return adRepository.findAll();
	}
	@Override
	public Ad save(Ad ad) {
		// TODO Auto-generated method stub
		return adRepository.save(ad);
	}
	@Override
	public Ad delete(Long id) {
		Ad ad = adRepository.findOne(id);
		if (ad == null) {
			throw new IllegalArgumentException("Removing nonexisting ad");
		}
		adRepository.delete(id);
		return ad;
	}

}
