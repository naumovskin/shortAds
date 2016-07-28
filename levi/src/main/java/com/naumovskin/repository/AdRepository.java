package com.naumovskin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.naumovskin.model.Ad;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {

	Ad findOne(Long id);

	List<Ad> findAll();

	Ad save(Ad ad);

	void delete(Long id);

}
