package com.rentia.daos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rentia.models.Property;
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


}
