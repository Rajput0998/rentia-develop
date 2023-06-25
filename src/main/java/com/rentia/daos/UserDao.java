package com.rentia.daos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rentia.models.IntUser;
import com.rentia.models.User;
import com.rentia.repositories.IntUserRepository;
import com.rentia.repositories.UsersRepository;


@Component
public class UserDao {

	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private IntUserRepository intUsersRepository;

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

	public User getUserByEmail(String email) {
		User user = usersRepository.getUserByEmail(email);
		return user;
	}

	public void deleteUser(User user) {
		usersRepository.delete(user);
		
	}

	public IntUser saveUser(IntUser user) {
		IntUser l_user = intUsersRepository.save(user);
		return l_user;
	}

	
}
