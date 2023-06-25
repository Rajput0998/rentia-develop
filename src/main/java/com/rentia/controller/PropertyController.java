package com.rentia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentia.models.Property;
import com.rentia.models.Rating;
import com.rentia.models.TntProperty;
import com.rentia.models.User;
import com.rentia.services.PropertyService;
import com.rentia.services.RatingService;
import com.rentia.services.UserService;

@Controller
public class PropertyController {
	
	@Autowired
    private PropertyService propertyService;
	
	@Autowired
	private RatingService ratingService;
	
	@PostMapping("/api/a1/property/add")
	public ResponseEntity<Property> addProperty(@RequestBody String body ) throws Exception {
		
		try{
			Property l_property = propertyService.addProperty(body);
			return new ResponseEntity<Property>(l_property, HttpStatus.OK);
         
		}
		catch(Exception ex){
			throw ex;
			//return new ResponseEntity<Property>(new Property(), HttpStatus.NOT_ACCEPTABLE);
		} 
	}
	
	@RequestMapping("/api/a1/property/getProperty/{propertyName}")
	public String getProperty(@PathVariable("propertyName") String propertyName) {
		Property l_property = propertyService.getPropertybyPropertyName(propertyName);
        return "space-detail";
    }
	
	/*
	 * @GetMapping("/getProperty/{propertyName}") public ResponseEntity<Property>
	 * getProperty(@PathVariable("propertyName") String propertyName) { Property
	 * l_property = propertyService.getPropertybyPropertyName(propertyName); if
	 * (l_property == null) { return
	 * ResponseEntity.status(HttpStatus.NOT_FOUND).build(); } return new
	 * ResponseEntity<Property>(l_property, HttpStatus.OK); }
	 */
	
	@PutMapping("/api/a1/property/update/{pid}")
	public ResponseEntity<Property> updateProperty(@RequestBody String body, @PathVariable("pid") Long pid ) throws Exception {
		
		try{
			Property l_property = propertyService.updateProperty(body, pid);
			return new ResponseEntity<Property>(l_property, HttpStatus.OK);
         
		}
		catch(Exception ex){
			throw ex;
			//return new ResponseEntity<Property>(new Property(), HttpStatus.NOT_ACCEPTABLE);
		} 
	}
	
	@GetMapping("/api/a1/property/city/{addressID}")
	public ResponseEntity<List<Property>> getProperty(@PathVariable("addressID") Long addressID) {
		List<Property> l_property = propertyService.getPropertybyCity(addressID);
        if (l_property == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return new ResponseEntity<List<Property>>(l_property, HttpStatus.OK);
    }
	

}

