package com.rentia.repositories;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rentia.models.Visit;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
	
    List<Visit> findAllByVisitorId(Long visitorId);

    List<Visit> findAllByPropertyId(Long propertyId);

    List<Visit> findByVisitDateBetween(Date startDate, Date endDate);

    @Query("SELECT v FROM Visit v " +
            "WHERE (:userId IS NULL OR v.visitor.id = :userId) " +
            "AND (:propertyId IS NULL OR v.property.id = :propertyId) " +
            "AND (:visitDateTime IS NULL OR v.visitDate >= :visitDate)")
     List<Visit> findByFilters(@Param("userId") Long userId,
                               @Param("propertyId") Long propertyId,
                               @Param("visitDateTime") Date visitDate);
 

    
}

