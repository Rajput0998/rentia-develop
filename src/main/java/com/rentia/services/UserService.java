package com.rentia.services;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.rentia.daos.UserDao;
import com.rentia.models.UserAddress;
import com.rentia.models.User;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public User registerUser(User user)
	{
		try
		{	
			for (UserAddress adr : user.getAdress()) 
			{ 
			    user.addAddress(adr); 
			}
			user.setCreationDate(new Date());
			user.setPassword(this.passwordEncoder.encode(user.getPassword()));
			System.out.println("mukul2" + user.getPassword());
			User l_user = userDao.saveUser(user);
			return l_user;
		}
		catch(Exception e)
		{
		   return null;
		}
		
		
	}
	
	public User updateUser(User user , Long UserId) {
		User l_user = userDao.saveUser(user);
		return l_user;
	}

	public User getUserbyUserName(String userName) {
		User user = userDao.getUserbyUserName(userName);
		return user != null ? user : null;
		
	}
	
	public User getUserbyUserId(Long userId) {
		User user = userDao.getById(userId);
		return user != null ? user : null;
		
	}
	
}
