package com.rentia.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentia.daos.AddressDao;
import com.rentia.daos.PropertyDao;
import com.rentia.models.Address;
import com.rentia.models.Amenity;
import com.rentia.models.Property;
import com.rentia.models.PropertyFacility;
import com.rentia.repositories.PropertyRepository;
import com.rentia.utilities.JsonUtil;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class PropertyService {

	
	@Autowired
	private PropertyDao propertyDao;
	
	@Autowired
	private PropertyRepository propertyRepository;
	
	@Autowired
	private AddressDao addressDao;
	
	

	public Property addProperty(String body) throws Exception
	{
		try
		{	
			JsonNode jsonObj = JsonUtil.stringToJson(body);
			String propertyName = jsonObj.get("propertyName").asText();
			String detail = jsonObj.get("detail").asText();
			Double price = Double.parseDouble(jsonObj.get("price").asText());
			int availableRoom = Integer.parseInt(jsonObj.get("availableRoom").asText());
			int totalRoom = Integer.parseInt(jsonObj.get("totalRoom").asText());
			int totalFloors = Integer.parseInt(jsonObj.get("totalFloors").asText());
			JsonNode adrs = jsonObj.get("address");
			Long aid = Long.parseLong(adrs.get("addressId").asText());
			JsonNode propertyFacility = jsonObj.get("propertyFacility");
			PropertyFacility l_propfac = new PropertyFacility();
			System.out.println(aid);
			Long id = (long) 1;
			Address address = addressDao.getById(aid);
			Property property = new Property();
			property.setPrpName(propertyName);
			property.setPrice(price);
			property.setAvlRoom(availableRoom);
			property.setTotalRoom(totalRoom);
			property.setDetail(detail);
			if(availableRoom > 0) {
				property.setAvailabilty(true);
			} else {
				property.setAvailabilty(false);
			}
			
			property.setTotalFloors(totalFloors);
			property.setAddress(address);
			Property l_property = propertyDao.saveProperty(property);
			return l_property;
		}
		catch(Exception e)
		{
		   throw e;
		}	
		
	}
	
	public Property addProperty(Property property) throws Exception
	{
		try
		{	
			System.out.println(property.getAddress().getAdrid());
			Address address = addressDao.getById(property.getAddress().getAdrid());
			property.setAddress(address);
			/*
			 * for (Amenity amenity : property.getPropertyFacility().getAmenities()) {
			 * propertyRepository.saveAmenity(amenity); }
			 */	
			Property l_property = propertyDao.saveProperty(property);
			return l_property;
		}
		catch(Exception e)
		{
		   throw e;
		}	
		
	}

	public Property getPropertybyPropertyName(String prpName) {
		Property property = propertyDao.getPropertyByName(prpName);
		return property != null ? property : null;
		
	}

	public Property updateProperty(String body, Long pid) throws Exception {
		try
		{	
			JsonNode jsonObj = JsonUtil.stringToJson(body);
			String propertyName = jsonObj.get("propertyName").asText();
			Double price = Double.parseDouble(jsonObj.get("price").asText());
			int availableRoom = Integer.parseInt(jsonObj.get("availableRoom").asText());
			int totalRoom = Integer.parseInt(jsonObj.get("totalRoom").asText());
			int totalFloors = Integer.parseInt(jsonObj.get("totalFloors").asText());
			boolean availabilty = jsonObj.get("totalFloors").asBoolean();
			JsonNode adrs = jsonObj.get("address");
			Long aid = Long.parseLong(adrs.get("addressId").asText());
			Long id = (long) 4;
			Address address = addressDao.getById(aid);
			System.out.println("Address MPS" + address);
			Property property = propertyDao.getPropertyById(pid);
			property.setId(property.getId());
			property.setPrpName(propertyName);
			property.setPrice(price);
			property.setAvlRoom(availableRoom);
			property.setTotalRoom(totalRoom);
			if(availableRoom > 0) {
				property.setAvailabilty(true);
			} else {
				property.setAvailabilty(false);
			}
			property.setTotalFloors(totalFloors);
			property.setAddress(address);
			Property l_property = propertyDao.saveProperty(property);
			return l_property;
		}
		catch(Exception e)
		{
		   throw e;
		}	
	}
	
	public Property updateProperty(Property property, Long pid) throws Exception {
		try
		{	System.out.println(property);
			Property l_property = propertyDao.saveProperty(property);
			System.out.println(l_property);
			return l_property;
		}
		catch(Exception e)
		{
		   throw e;
		}	
	}
	
	
	public Property getPropertybyId(Long pid) {
		Property property = propertyDao.getPropertyById(pid);
		return property != null ? property : null;
		
	}

	public List<Property> getPropertybyCity(Long addressID) {
		List<Property> properties = propertyDao.getPropertybyCity(addressID);
		System.out.println(properties);
		return properties != null ? properties : null;
	}
	
	
}
