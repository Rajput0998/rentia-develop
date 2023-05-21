package com.rentia.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.rentia.daos.PropertyDao;
import com.rentia.daos.RatingDao;
import com.rentia.daos.UserDao;
import com.rentia.models.Property;
import com.rentia.models.Rating;
import com.rentia.models.TntProperty;
import com.rentia.models.User;
import com.rentia.utilities.JsonUtil;

@Service
public class RatingService {

	@Autowired
	private RatingDao ratingDao;
	
	@Autowired
	UserDao userDao;

	@Autowired
	PropertyDao propertyDao;

    public List<Rating> getRatingsForProperty(Long propertyId) {
        return ratingDao.getRatingsForProperty(propertyId);
    }

    public List<Rating> getRatingsForUser(Long userId) {
        return ratingDao.getRatingsForUser(userId);
    }

    public Optional<Rating> getRatingForUserAndProperty(Long userId, Long propertyId) {
        return ratingDao.getRatingForUserAndProperty(userId, propertyId);
    }

    public Rating saveRating(String body) throws Exception {
    	
    	try {
    		JsonNode jsonObj = JsonUtil.stringToJson(body);

    		Long uid = Long.parseLong(jsonObj.get("userId").asText());
    		Long pid = Long.parseLong(jsonObj.get("propertyId").asText());
    		String review = jsonObj.get("review").asText();
			int rating = Integer.parseInt(jsonObj.get("rating").asText());
    		System.out.println(pid);

    		// System.out.println(teams);
    		User tenant = userDao.getById(uid);
    		Property property = propertyDao.getById(pid);
    		System.out.println(property.getPrpName());
    		Rating l_rating = new Rating();
    		l_rating.setUser(tenant);
    		l_rating.setProperty(property);
    		l_rating.setRating(rating);
    		l_rating.setReview(review);
        	
            return ratingDao.saveRating(l_rating);
    	} catch (Exception ex) {
    		throw ex;
    	}
    	
    	
    }

	public void deletePropertyRatingById(Long id) {
		ratingDao.deletePropertyRatingById(id);
		
	}

}

