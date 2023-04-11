package com.rentia.daos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rentia.models.User;
import com.rentia.repositories.UsersRepository;


@Component
public class UserDao {

   @Autowired
   private UsersRepository usersRepository;
	
   public User registerUser(User user)
	{	
	   return usersRepository.save(user);
	}

   	public User getUserbyUserName(String userName) {
	     User user = usersRepository.getUserbyUserName(userName);
	     return user;
   	}
}
