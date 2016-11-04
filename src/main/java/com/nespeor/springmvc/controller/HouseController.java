package com.nespeor.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nespeor.springmvc.model.House;
import com.nespeor.springmvc.service.HouseService;

@RestController
public class HouseController {

	@Autowired
    HouseService houseService;  //Service which will do all data retrieval/manipulation work
 
    @RequestMapping(value = "/house/", method = RequestMethod.GET)
    public ResponseEntity<List<House>> listAllHouses() {
        List<House> houses = houseService.findAllHouses();
        if(houses.isEmpty()){
            return new ResponseEntity<List<House>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<House>>(houses, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/house/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<House> getHouse(@PathVariable("id") int id) {
        System.out.println("Fetching House with id " + id);
        House house = houseService.findById(id);
        if (house == null) {
            System.out.println("House with id " + id + " not found");
            return new ResponseEntity<House>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<House>(house, HttpStatus.OK);
    }
    
}
