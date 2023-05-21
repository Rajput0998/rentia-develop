package com.rentia.daos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rentia.models.User;
import com.rentia.repositories.UsersRepository;


@Component
public class UserDao {

	@Autowired
	private UsersRepository usersRepository;

	public User saveUser(User user) {
		System.out.println("mukul3" + user.getAdress().size());
		User l_user = usersRepository.save(user);
		System.out.println("mukul4" + l_user.getAdress().size());
		return l_user;
	}

	public User getUserbyUserName(String userName) {
		User user = usersRepository.getUserbyUserName(userName);
		return user;
	}

	public User getById(Long id) {
		return usersRepository.findById(id).get();
	}

	
}
