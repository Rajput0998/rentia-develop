package com.rentia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rentia.models.User;

public interface UsersRepository extends JpaRepository<User, Long> {

	@Query("select u from User u where u.userName = :userName")
	public User getUserbyUserName(@Param("userName") String userName);
	
	
}

