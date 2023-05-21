package com.rentia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentia.models.Property;
import com.rentia.models.Rating;
import com.rentia.services.RatingService;

@RestController
@RequestMapping("/api/u1/property-ratings")
public class PropertyRatingController {
    
	@Autowired
	private RatingService ratingService;

	@GetMapping("/{propertyId}/ratings")
    public List<Rating> getProductRatings(@PathVariable Long propertyId) {
        return ratingService.getRatingsForProperty(propertyId);
    }

    @PostMapping("/ratings")
    public ResponseEntity<Rating> saveProductRating(@RequestBody String body) throws Exception {
        try{
			Rating l_rating = ratingService.saveRating(body);
			return new ResponseEntity<Rating>(l_rating, HttpStatus.OK);
         
		}
		catch(Exception ex){
			throw ex;
			
		} 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePropertyRatingById(@PathVariable Long id) {
    	try {
    		ratingService.deletePropertyRatingById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    	}
    	catch(Exception ex){
			throw ex;
			
		} 
    	
    }
}

