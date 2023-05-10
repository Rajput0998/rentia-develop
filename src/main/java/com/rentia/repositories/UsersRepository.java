package com.rentia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rentia.models.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {

	@Query("select u from User u where u.userName = :userName")
	public User getUserbyUserName(@Param("userName") String userName);
	
	
}

