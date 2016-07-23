package com.levi9.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.levi9.model.Ad;
import com.levi9.service.AdService;
import com.levi9.web.dto.AdDTO;

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
		ad.setCategoryName(dto.getCategoryName());
		ad.setName(dto.getName());
		ad.setCreated(dto.getCreated());
		ad.setPassed(dto.getPassed());
		ad.setAuthorUsername(dto.getAuthorUsername());
		ad.setCategory(dto.getCategory());
		
		return ad;
	}

	public List<Ad> convert(List<AdDTO> dtoAds) {
		List<Ad> ads = new ArrayList<>();

		for (AdDTO dto : dtoAds) {
			ads.add(convert(dto));
		}

		return ads;
	}

}
