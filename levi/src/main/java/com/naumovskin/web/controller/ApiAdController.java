package com.naumovskin.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.naumovskin.model.Ad;
import com.naumovskin.service.AdService;
import com.naumovskin.support.AdDTOToAd;
import com.naumovskin.support.AdToAdDTO;
import com.naumovskin.web.dto.AdDTO;

@RestController
@RequestMapping(value = "/api/ads")
public class ApiAdController {

	@Autowired
	private AdService adService;

	@Autowired
	private AdDTOToAd toAd;

	@Autowired
	private AdToAdDTO toDTO;

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<AdDTO>> getAds() {// promenjena paginacija na false


		List<Ad> ads = adService.findAll();

		return new ResponseEntity<>(toDTO.convert(ads), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<AdDTO> getAd(@PathVariable Long id) {
		Ad ad = adService.findOne(id);
		if (ad == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(ad), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<AdDTO> delete(@PathVariable Long id) {
		Ad deleted = adService.delete(id);

		return new ResponseEntity<>(toDTO.convert(deleted), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<AdDTO> add(@RequestBody AdDTO newAd) {

		
		java.util.Date created = new java.util.Date();
 	   // timestamp for 2 weeks after posting in unix time
 	   long expired = created.getTime() + 1296000L * 1000;
 	   
 	   Date expiredDate = new Date(expired);
 	   newAd.setExpiryDate(expiredDate);
 	   newAd.setDatePosted(created);
		
		
		
		
		Ad savedAd = adService.save(toAd.convert(newAd));

		return new ResponseEntity<>(toDTO.convert(savedAd), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<AdDTO> edit(@RequestBody AdDTO ad, @PathVariable Long id) {

		if (id != ad.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Ad persisted = adService.save(toAd.convert(ad));

		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}

}
