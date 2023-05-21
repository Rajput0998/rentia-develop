package com.rentia.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rentia.models.Rating;
import com.rentia.repositories.RatingRepository;
import java.util.Optional;

@Component
public class RatingDao {

	@Autowired
	private RatingRepository ratingRepository;

    public List<Rating> getRatingsForProperty(Long propertyId) {
        return ratingRepository.findByPropertyId(propertyId);
    }

    public List<Rating> getRatingsForUser(Long userId) {
        return ratingRepository.findByUserId(userId);
    }

    public Optional<Rating> getRatingForUserAndProperty(Long userId, Long propertyId) {
        return ratingRepository.findByUserIdAndPropertyId(userId, propertyId);
    }

    public Rating saveRating(Rating rating) {
        return ratingRepository.save(rating);
    }

	public void deletePropertyRatingById(Long id) {
		ratingRepository.deleteById(id);
		
	}
	
}
