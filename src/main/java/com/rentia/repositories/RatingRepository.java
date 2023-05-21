package com.rentia.repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rentia.models.Rating;



@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    List<Rating> findByPropertyId(Long propertyId);

    List<Rating> findByUserId(Long userId);

    Optional<Rating> findByUserIdAndPropertyId(Long userId, Long propertyId);

    
    @Query("SELECT rat FROM Rating rat WHERE (:rating IS NULL OR rat.rating = :rating)")
    List<Rating> findByRating(@Param("rating") Integer rating);
}
