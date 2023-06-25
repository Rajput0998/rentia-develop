package com.rentia.services;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.rentia.daos.UserDao;
import com.rentia.models.UserAddress;
import com.rentia.models.IntUser;
import com.rentia.models.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User registerUser(User user) {
		try {
			for (UserAddress adr : user.getAdress()) {
				user.addAddress(adr);
			}
			user.setCreationDate(new Date());
			user.setPassword(this.passwordEncoder.encode(user.getPassword()));
			System.out.println("mukul2" + user.getPassword());
			User l_user = userDao.saveUser(user);
			return l_user;
		} catch (Exception e) {
			return null;
		}

	}

	public User updateUserProfile(User user, Long UserId) {
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

	public User updateUser(User user, Long userId) {
		User l_user = userDao.getById(userId);
		if (null != l_user) {
			l_user.setUserName(user.getUsername());
			l_user.setName(user.getName());
			l_user.setEmail(user.getEmail());
			l_user.setAcardl(user.getAcardl());
			l_user.setJobId(user.getJobId());
			l_user.setGender(user.getGender());
			l_user.setMobNum(user.getMobNum());
			l_user.setRole(user.getRole());
			l_user.setAadharImage(user.getAadharImage());
			l_user.setSelfImage(user.getSelfImage());
			l_user.setIsAccountNonExpired(user.getIsAccountNonExpired());
			l_user.setIsAccountNonlocked(user.getIsAccountNonlocked());
			l_user.setIsCredentialsNonExpired(user.getIsCredentialsNonExpired());
			User updateUser = userDao.saveUser(l_user);
			return updateUser;

		}
		return null;
	}

	public Boolean changePassword(String userName, String newPassword, String oldPassword) {
		User currentUser = userDao.getUserbyUserName(userName);
		if( null != oldPassword) {
			if (this.passwordEncoder.matches(oldPassword, currentUser.getPassword())) {
				currentUser.setPassword(this.passwordEncoder.encode(newPassword));
				this.userDao.saveUser(currentUser);
				return true;

			} else {
				return false;
			}	
		} else {
			currentUser.setPassword(this.passwordEncoder.encode(newPassword));
			this.userDao.saveUser(currentUser);
			return true;
		}
		
	}

	public Boolean verfiyOtp(int otp, int myOtp, String email) {
		if (otp == myOtp) {
			User user = userDao.getUserByEmail(email);

			if (user == null) {
				return false;
			} else {
				return true;
			}
		

		} else {
			return false;
		}
	}

	public User getUserByEmail(String email) {
		User user = userDao.getUserByEmail(email);
		return user != null ? user : null;
	}
	

	public void deleteUser(Long userId) throws Exception {
		User user = this.userDao.getById(userId);
		if( null == user ) {
			throw new Exception("User not found");
		}
		this.userDao.deleteUser(user);

	}

	public IntUser registerInterestedUser(IntUser user) {
		try {
			user.setCreationDate(new Date());
			IntUser l_user = userDao.saveUser(user);
			return l_user;
		} catch (Exception e) {
			return null;
		}
	}

}
