package com.rentia.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.rentia.repositories.UsersRepository;
import com.rentia.models.User;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UsersRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// loading user from database by username
		User user = this.userRepo.getUserbyUserName(username);
		
		if ( null == user ) {
			throw new UsernameNotFoundException(username);
		}

		return user;
	}

}
