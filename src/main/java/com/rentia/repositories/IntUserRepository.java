package com.rentia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rentia.models.IntUser;

@Repository
public interface IntUserRepository extends JpaRepository<IntUser, Long> {


	@Query("select u from User u where u.email = :email")
	public IntUser getUserByEmail(@Param("email") String email);
	
	
}

