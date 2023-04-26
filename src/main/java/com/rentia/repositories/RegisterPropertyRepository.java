package com.rentia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentia.models.TntProperty;

public interface RegisterPropertyRepository  extends JpaRepository<TntProperty, Long>{

}
