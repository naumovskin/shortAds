package com.naumovskin.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.naumovskin.model.Ad;
import com.naumovskin.web.dto.AdDTO;
@Component
public class AdToAdDTO implements Converter<Ad, AdDTO> {

	
	
	@Override
	public AdDTO convert(Ad ad) {
		if (ad == null) {
			return null;
		}

		AdDTO dto = new AdDTO ();

		dto.setId(ad.getId());
		dto.setTitle(ad.getTitle());
		dto.setDescription(ad.getDescription());
		dto.setDatePosted(ad.getDatePosted());
		dto.setExpiryDate(ad.getExpiryDate());
		dto.setCategory(ad.getCategory().getName());
		dto.setUser(ad.getUser().getUsername());
		return dto;
	}
	
	public List<AdDTO> convert(List<Ad> adverts){
		List<AdDTO> retVal = new ArrayList<>();
		
		for(Ad a:adverts){
			retVal.add(convert(a));
		}
		
		return retVal;
	}
}
