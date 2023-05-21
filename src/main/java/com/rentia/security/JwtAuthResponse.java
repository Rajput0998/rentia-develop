package com.rentia.security;

import com.rentia.models.User;

import lombok.Data;

@Data
public class JwtAuthResponse {

	private String token;
	
	private User user;
}
