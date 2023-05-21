package com.rentia.daos;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rentia.models.Visit;
import com.rentia.repositories.BookingRepository;
import com.rentia.repositories.VisitRepository;

@Component
public class VisitDao {
	
	@Autowired
    private VisitRepository visitRepository;
	
	public Visit createVisit(Visit visit) {
        return visitRepository.save(visit);
    }
	
	public Visit updateVisit(Visit visit) {
        return visitRepository.save(visit);
    }

	
	public void deleteVisit(Long visitId) {
        visitRepository.deleteById(visitId);
    }
	
	
    public List<Visit> getAllVisits() {
        return visitRepository.findAll();
    }

    
    public Visit getVisitById(Long visitId) {
        return visitRepository.findById(visitId).get();
    }
    

    public List<Visit> getVisitsByUserId(Long userId) {
        return visitRepository.findAllByVisitorId(userId);
    }

    public List<Visit> getVisitsByPgId(Long pgId) {
        return visitRepository.findAllByPropertyId(pgId);
    }

    public List<Visit> getVisitsByDateRange(Date startDateTime, Date endDateTime) {
        return visitRepository.findByVisitDateBetween(startDateTime, endDateTime);
    }

	public List<Visit> getVistingDetailsByfilter(Long userId, Long pgId, Date visitDate) {
		
		return visitRepository.findByFilters(userId, pgId, visitDate);
	}
}

