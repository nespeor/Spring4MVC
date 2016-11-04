package com.nespeor.springmvc.service;

import java.util.List;

import com.nespeor.springmvc.model.House;



public interface HouseService {
	
	House findById(Integer id);
	
	House findByPoblacion(String poblacion);
	
	void saveHouse(House house);
	
	void updateHouse(House house);
	
	void deleteHouseById(Integer id);

	List<House> findAllHouses(); 
	
	void deleteAllHouses();
	
	public boolean isHouseExist(House house);
	
}
