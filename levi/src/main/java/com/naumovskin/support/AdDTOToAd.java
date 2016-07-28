package com.naumovskin.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.naumovskin.model.Ad;
import com.naumovskin.service.AdService;
import com.naumovskin.web.dto.AdDTO;

@Component
public class AdDTOToAd implements Converter<AdDTO, Ad> {

	@Autowired
	AdService adService;

	@Override
	public Ad convert(AdDTO dto) {
		Ad ad = new Ad();

		if (dto.getId() != null) {
			ad = adService.findOne(dto.getId());

			if (ad == null) {
				throw new IllegalStateException("Tried to " + "modify a non-existant activity");
			}
		}

		ad.setId(dto.getId());
		ad.setTitle(dto.getTitle());
		ad.setDescription(dto.getDescription());
		ad.setDatePosted(dto.getDatePosted());
		ad.setExpiryDate(dto.getExpiryDate());
//		advert.setCategory(dto.getCategory());
		
		
		
		return ad;
	}
	
	public List<Ad> convert (List<AdDTO> dtoAdverts){
		List<Ad> adverts = new ArrayList<>();
		
		for(AdDTO dto : dtoAdverts){
			adverts.add(convert(dto));
		}
		
		return adverts;
	}

}
