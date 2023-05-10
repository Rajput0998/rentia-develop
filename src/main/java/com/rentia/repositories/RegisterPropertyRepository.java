package com.rentia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rentia.models.TntProperty;

@Repository
public interface RegisterPropertyRepository  extends JpaRepository<TntProperty, Long>{

}
