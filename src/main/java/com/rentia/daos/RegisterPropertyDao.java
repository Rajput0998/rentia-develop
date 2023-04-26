package com.rentia.daos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rentia.models.Property;
import com.rentia.models.TntProperty;
import com.rentia.repositories.RegisterPropertyRepository;

@Component
public class RegisterPropertyDao {
	
	@Autowired
	private RegisterPropertyRepository registerPropertyRepository;
	
	
	public TntProperty saveProperty(TntProperty p_tntProperty) {
		
		TntProperty tntProperty = registerPropertyRepository.save(p_tntProperty);
		return p_tntProperty;
	}
	
	public TntProperty getById(Long id) {
		System.out.println(id);
		TntProperty tntProp = registerPropertyRepository.findById(id).get();
		System.out.println(tntProp);
		return tntProp;
	}
	



}
