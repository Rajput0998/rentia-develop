package com.rentia.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.rentia.daos.PropertyDao;
import com.rentia.daos.UserDao;
import com.rentia.daos.VisitDao;
import com.rentia.models.Booking;
import com.rentia.models.Property;
import com.rentia.models.User;
import com.rentia.models.Visit;
import com.rentia.utilities.JsonUtil;

@Service
public class VisitService {

    @Autowired
    VisitDao visitDao;
    
    @Autowired
    PropertyDao propertyDao;
    
    @Autowired
    UserDao userDao;
    
    public Visit createVisit(String visitBody) throws Exception {
    	try {
    		Visit visit = new Visit();
        	User user = new User();
        	Property property = new Property();
        	String pattern = "yyyy-MM-dd";
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        	JsonNode jsonObj = JsonUtil.stringToJson(visitBody);
        	String userName = jsonObj.get("userName").asText();
        	String propertyName = jsonObj.get("propertyName").asText();
        	Boolean vistingFlag = Boolean.parseBoolean(jsonObj.get("isVisiting").asText());
            Date visitDate = sdf.parse(jsonObj.get("visitDate").asText());
        	user = userDao.getUserbyUserName(userName);
    		property = propertyDao.getPropertyByName(propertyName);
    		if(null == user || null == property ) {
    			throw new Exception("User or Property is not found");
    		} else {
    			visit.setProperty(property);
    			visit.setVisitor(user);
        		visit.setVisitDate(visitDate);
        		visit.setIsVisting(vistingFlag);
        		return visitDao.createVisit(visit);
    		}	
            
    	} catch (Exception ex) {
    		throw ex;
    	}
    }


    public Visit getVisitById(Long visitId) {
        return visitDao.getVisitById(visitId);
    }

   
    public Visit updateVisit(Long visitId, String visitBody) throws Exception {
        Visit visit = visitDao.getVisitById(visitId);
        if (visit != null) {
        	try {
            	User user = new User();
            	Property property = new Property();
            	String pattern = "yyyy-MM-dd";
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            	JsonNode jsonObj = JsonUtil.stringToJson(visitBody);
            	String userName = jsonObj.get("userName").asText();
            	String propertyName = jsonObj.get("propertyName").asText();
            	Boolean vistingFlag = Boolean.parseBoolean(jsonObj.get("isVisiting").asText());
                Date visitDate = sdf.parse(jsonObj.get("visitDate").asText());
            	user = userDao.getUserbyUserName(userName);
        		property = propertyDao.getPropertyByName(propertyName);
        		if(null == user || null == property  ) {
        			throw new Exception("User or Property is not found");
        		} else {
        			visit.setProperty(property);
        			visit.setVisitor(user);
            		visit.setVisitDate(visitDate);
            		visit.setIsVisting(vistingFlag);
            		return visitDao.createVisit(visit);
        		}	
                
        	} catch (Exception ex) {
        		throw ex;
        	}
        }
        return null;
    }

    public void deleteVisit(Long visitId) {
    	visitDao.deleteVisit(visitId);
    }

    public List<Visit> getVisitsByUserId(Long userId) {
        return visitDao.getVisitsByUserId(userId);
    }

    public List<Visit> getVisitsByPgId(Long pgId) {
        return visitDao.getVisitsByPgId(pgId);
    }

    public List<Visit> getVisitsByDateRange(Date startDate, Date endDate) {
        return visitDao.getVisitsByDateRange(startDate, endDate);
    }
    
    public List<Visit> fetchVistingDetails(String userName, String propertyName, Date visitDate) throws Exception {
		
		User user = userDao.getUserbyUserName(userName);
		Property property = propertyDao.getPropertyByName(propertyName);
		List<Visit> vistingList = null;
		if(null == user || null == property ) {
			throw new Exception("User or Property is not found");
		} else {
			vistingList =  visitDao.getVistingDetailsByfilter(user.getId(), property.getId(), visitDate);
		}
		return vistingList;
		
	}
}

