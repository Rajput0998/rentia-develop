package com.rentia.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rentia.models.Address;
import com.rentia.models.Booking;
import com.rentia.models.Rating;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    
	List<Booking> findAllByPropertyId(Long propertyId );
	
	@Query("SELECT b FROM Booking b " +
	           "WHERE (:userId IS NULL OR b.tenant.id = :userId) ")
	List<Booking> findByUserId(Long userId);
	
	@Query("SELECT b FROM Booking b " +
	           "WHERE (:userId IS NULL OR b.tenant.id = :userId) " +
	           "AND (:propertyId IS NULL OR b.property.id = :propertyId) ")
	Optional<Booking> findByUserIdAndPropertyId(Long userId, Long propertyId);
	
	List<Booking> findByStartDateBetween(Date startDate, Date endDate);
	
	@Query("SELECT b FROM Booking b " +
	           "WHERE (:userId IS NULL OR b.tenant.id = :userId) " +
	           "AND (:propertyId IS NULL OR b.property.id = :propertyId) " +
	           "AND (:startDate IS NULL OR b.startDate >= :startDate) " +
	           "AND (:endDate IS NULL OR b.endDate <= :endDate)")
	    List<Booking> findByParameters(@Param("userId") Long userId,
	                                   @Param("propertyId") Long propertyId,
	                                   @Param("startDate") Date startDate,
	                                   @Param("endDate") Date endDate);
}

