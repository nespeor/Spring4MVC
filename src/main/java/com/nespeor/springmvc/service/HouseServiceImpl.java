package com.nespeor.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nespeor.springmvc.model.House;
import com.nespeor.springmvc.model.HouseDAO;

@Service("houseService")
public class HouseServiceImpl implements HouseService{
	
	@Autowired
	private HouseDAO houseDao;

	public List<House> findAllHouses() {
		return houseDao.findAll();
	}
	
	public House findById(Integer id) {
		return houseDao.findById(id);
	}
	
	public House findByPoblacion(String poblacion) {
		
		List<House> houses = houseDao.findByPoblacion(poblacion);
		for(House house : houses){
			if(house.getPoblacion().equalsIgnoreCase(poblacion)){
				return house;
			}
		}
		return null;
	}
	
	public void saveHouse(House house) {
		house.setId(houseDao.generateId());
		houseDao.create(house);
	}

	public void updateHouse(House house) {
		houseDao.update(house);
	}

	public void deleteHouseById(Integer id) {
		houseDao.remove(id);
	}

	public boolean isHouseExist(House house) {
		List<House> houses = houseDao.findByPoblacion(house.getPoblacion());
		return houses.size()>0;
	}
	
	public void deleteAllHouses(){
		//houses.clear();
	}

}
