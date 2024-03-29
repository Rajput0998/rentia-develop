package com.rentia.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rentia.models.Property;
import com.rentia.models.User;
import com.rentia.repositories.PropertyRepository;
import com.rentia.repositories.UsersRepository;

@Component
public class PropertyDao {
	
	@Autowired
	private PropertyRepository propertyRepository;
	
	public Property getById(Long id) {
		System.out.println(id);
		Property p = propertyRepository.getOne(id);
		System.out.println(p);
		return p;
	}
	
	public Property saveProperty(Property property) {
		return propertyRepository.save(property);
	}

	public Property getPropertyByName(String prpName) {
		// TODO Auto-generated method stub
		return propertyRepository.findByPrpName(prpName);
	}

	public Property getPropertyById(Long pid) {
		// TODO Auto-generated method stub
		return propertyRepository.findById(pid).get();
	}

	public List<Property> getPropertybyCity(Long addressID) {
		List<Property> properties = propertyRepository.findPropertyByAddressId(addressID);
		System.out.println(properties);
		return properties != null ? properties : null;
	}
	
	public List<Property> getPropertybyCity(String city) {
		List<Property> properties = propertyRepository.findPropertyByCity(city);
		System.out.println(properties);
		return properties != null ? properties : null;
	}


}
