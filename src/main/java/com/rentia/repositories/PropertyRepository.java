package com.rentia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rentia.models.Property;
import com.rentia.models.User;

public interface PropertyRepository extends JpaRepository<Property, Long> {
	
	Property findByPrpName(String prpName);
	
	
	@Query("SELECT p FROM Property p " +
	           "WHERE (:addressID IS NULL OR p.address.adrid = :addressID) ")
	List<Property> findByAddressId(Long addressID); 

}