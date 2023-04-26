package com.rentia.services;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.rentia.daos.PropertyDao;
import com.rentia.daos.RegisterPropertyDao;
import com.rentia.daos.UserDao;
import com.rentia.models.Property;
import com.rentia.models.TntProperty;
import com.rentia.models.User;
import com.rentia.utilities.JsonUtil;

@Service
public class RegisterPropertyService {
	
	
	@Autowired
	UserDao userDao;

	@Autowired
	PropertyDao propertyDao;
	
	@Autowired
	RegisterPropertyDao  registerPropertyDao;
	
	public TntProperty registerProperty(String body) throws Exception {
		
		try {

			JsonNode jsonObj = JsonUtil.stringToJson(body);

			Long uid = Long.parseLong(jsonObj.get("userId").asText());
			Long oid = Long.parseLong(jsonObj.get("ownerId").asText());
			Long pid = Long.parseLong(jsonObj.get("propertyId").asText());
			System.out.println(pid);

			// System.out.println(teams);
			User tenant = userDao.getById(uid);
			User owner = userDao.getById(oid);
			Property property = propertyDao.getById(pid);
			System.out.println(property.getPrpName());
			TntProperty tntproperty = new TntProperty();
			System.out.println(tntproperty);
			tntproperty.setTenant(tenant);
			tntproperty.setOwner(owner);
			System.out.println(tntproperty);
			tntproperty.setProperty(property);
			tntproperty.setCreationDate(new Date());

			 System.out.println(tntproperty);
			TntProperty regProperty = registerPropertyDao.saveProperty(tntproperty);
			return regProperty;

		} catch (Exception e) {
			throw new Exception();
		}
	}
	
    public TntProperty updateRegProperty(String body, Long regId) throws Exception {
		
		try {

			JsonNode jsonObj = JsonUtil.stringToJson(body);

			Long uid = Long.parseLong(jsonObj.get("userId").asText());
			Long oid = Long.parseLong(jsonObj.get("ownerId").asText());
			Long pid = Long.parseLong(jsonObj.get("propertyId").asText());
			System.out.println(pid);

			User tenant = userDao.getById(uid);
			User owner = userDao.getById(oid);
			Property property = propertyDao.getById(pid);
			TntProperty tntProperty = registerPropertyDao.getById(regId);

			System.out.println(tntProperty);
			tntProperty.setTenant(tenant);
			tntProperty.setOwner(owner);
			System.out.println(tntProperty);
			tntProperty.setProperty(property);

			System.out.println(tntProperty);
			TntProperty  updatedProperty = registerPropertyDao.saveProperty(tntProperty);
			return updatedProperty;

		} catch (Exception e) {
			throw new Exception();
		}
	}
    
    public TntProperty getregTntProperty(Long regId) {
    	TntProperty tntProperty = registerPropertyDao.getById(regId);
		return tntProperty != null ? tntProperty : null;
		
	}
    

}
