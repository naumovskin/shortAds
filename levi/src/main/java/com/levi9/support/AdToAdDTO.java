package com.levi9.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.levi9.model.Ad;
import com.levi9.web.dto.AdDTO;
@Component
public class AdToAdDTO implements Converter<Ad, AdDTO> {

	
	
	@Override
	public AdDTO convert(Ad ad) {
		if (ad == null) {
			return null;
		}

		AdDTO dto = new AdDTO ();

		dto.setId(ad.getId());
		dto.setCategoryName(ad.getCategoryName());
		dto.setName(ad.getName());
		dto.setCreated(ad.getCreated());
		dto.setPassed(ad.getPassed());
		dto.setAuthorUsername(ad.getAuthorUsername());
		dto.setCategory(ad.getCategory());

		return dto;
	}

	public List<AdDTO> convert(List<Ad> ads) {
		List<AdDTO> ret = new ArrayList<>();
			for (Ad a : ads) {
				ret.add(convert(a));
			}
		return ret;
	}

}
