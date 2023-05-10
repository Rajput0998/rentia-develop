package com.rentia.repositories;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import com.rentia.models.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long>, QueryByExampleExecutor<Address> {
	
			
	List<Address> findAllByCity( String city );
	
	List<Address> findAllByState( String state );
	
	List<Address> findAllByStateAndCity( String state, String city );
	
	List<Address> findAllByStateOrCity( String state, String city );
	
	
	@Query("SELECT a FROM Address a WHERE " +
            "(:city is null OR a.city = :city) " +
            "AND (:state is null OR a.state = :state) ")
	
    List<Address> findByCityAndState(String city, String state);

}